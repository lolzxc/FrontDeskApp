package org.FrontDeskApp.Customer;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    public void handleCustomerRegistration(Customer customer) throws SQLException {
        customerService.createCustomer(customer);
    }
    public boolean inputValidation(String str) {
        Pattern pattern = Pattern.compile(".*\\d.*");
        Matcher matcher = pattern.matcher(str);
        return !str.isEmpty() && matcher.matches();
    }
}
