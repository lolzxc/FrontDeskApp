package org.FrontDeskApp.Customer;

import java.sql.SQLException;

public class CustomerService {
    public CustomerDao customerDao;
    public void createCustomer(Customer customer) throws SQLException {
        customerDao = new CustomerDaoImpl();
        int generatedKey = customerDao.insert(customer);
        customer.setId(generatedKey);
    }
}
