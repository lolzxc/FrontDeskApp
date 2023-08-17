package org.FrontDeskApp.Customer;

import org.FrontDeskApp.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private static final String
            SELECT_BY_ID = "SELECT id, first_name, last_name, phone_number FROM customers WHERE id = ?";
    private static final String
            SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";

    private static final String
            INSERT_CUSTOMER = "INSERT INTO customers (first_name, last_name, phone_number) VALUES"
                + " (?, ?, ?);";
    @Override
    public Customer get(int id) throws SQLException {
        Connection conn = Database.getConnection();
        Customer customer = null;

        PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int oid = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String phoneNumber = rs.getString("phone_number");
            customer = new Customer(oid, firstName, lastName, phoneNumber);
        }
        Database.closeConnection(conn);
        return customer;
    }
    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        Connection conn = Database.getConnection();

        PreparedStatement ps = conn.prepareStatement(SELECT_ALL_CUSTOMERS);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int oid = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String phoneNumber = rs.getString("phone_number");
            customers.add(new Customer(oid, firstName, lastName, phoneNumber));
        }

        Database.closeConnection(conn);
        return customers;
    }

    @Override
    public int insert(Customer customer) throws SQLException {
        Connection conn = Database.getConnection();

        PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER,
                Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getPhoneNumber());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }

        Database.closePreparedStatement(ps);
        Database.closeConnection(conn);

        return generatedKey;
    }
    @Override
    public void update(Customer customer) throws SQLException {
    }

}
