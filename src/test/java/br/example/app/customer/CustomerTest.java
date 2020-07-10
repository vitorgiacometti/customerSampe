package br.example.app.customer;

import br.example.app.CustomerApplication;
import br.example.app.customer.domain.Address;
import br.example.app.customer.domain.Customer;
import br.example.app.customer.dto.CustomerDTOIn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerApplication.class)
@WebAppConfiguration
public class CustomerTest {

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private String baseUrl = "/v1/customer/";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private CustomerDTOIn caseCustomerDTO;
    private Customer caseCustomer;

    public static String toJsonCustomer(CustomerDTOIn customer){

        return "{\n" +
                "  \"adress\": {\n" +
                "    \"city\": \"" + customer.getAddress().getCity()+ "\",\n" +
                "    \"numberHome\": "+ customer.getAddress().getNumberHome()+",\n" +
                "    \"street\": \"" + customer.getAddress().getStreet()+"\"\n" +
                "  },\n" +
                "  \"cpf\": \""+customer.getCpf()+"\",\n" +
                "  \"dateOfBirth\": \""+ sdf.format(customer.getDateOfBirth())+"\",\n" +
                "  \"name\": \""+customer.getName()+"\"\n" +
                "}";
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.customerRepository.deleteAllInBatch();
        this.caseCustomerDTO = new CustomerDTOIn("Ramon", "21121345", new Date(),
                new Address("Andrade neves", 25, "Campinas"));
        this.caseCustomer = Customer.customerBuilder()
                .name("Jose")
                .cpf("21212121345")
                .dateOfBirth(new Date())
                .adress(new Address("Andrade neves", 26, "São Paulo"))
                .build();
    }

    @Test
    public void createCustomer() throws Exception {
        String json = toJsonCustomer(caseCustomerDTO);
        System.out.println(json);
        this.mockMvc.perform(post(baseUrl).contentType(contentType).content(json)).andExpect(status().isCreated());
    }

    @Test
    public void listCustomerOnly() throws Exception{
        Customer customer = customerRepository.save(caseCustomer);
        mockMvc.perform(get(baseUrl).accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void listCustomerByName()throws Exception{
        String textExpression = caseCustomer.getName();
        Customer customer = customerRepository.save(caseCustomer);
        mockMvc.perform(get(baseUrl).param("name", textExpression))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    public void removeCustomer() throws Exception {
        Customer customer = customerRepository.save(caseCustomer);
        mockMvc.perform(delete(baseUrl + customer.getId())).andExpect(status().isNoContent());
    }

    @Test
    public void customerNotFound() throws Exception {
        mockMvc.perform(get(baseUrl + 9999)).andExpect(status().isBadRequest());
    }



}
