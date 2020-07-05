package br.example.app.customer.exception;

public class CustomException extends RuntimeException {

     public CustomException(Long id) {
                   super("Customer not found : " + id);
        }

}
