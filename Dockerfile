FROM openjdk:8u151-jre-alpine3.7
VOLUME /tmp
COPY target/customer-1.0.0.jar customer.jar
ENTRYPOINT ["java", "-jar", "customer.jar"]
