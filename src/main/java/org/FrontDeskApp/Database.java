package org.FrontDeskApp;

import java.sql.*;

public class Database {
    private static final String jdbcURL = "jdbc:h2:./db";
    private Database() {}
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(jdbcURL);

            String createTableCustomers = "CREATE TABLE IF NOT EXISTS customers" +
                    "(id INT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VarChar(255)," +
                    "last_name VarChar(255)," +
                    "phone_number VarChar(255))";

            String createTableAreas = "CREATE TABLE IF NOT EXISTS areas" +
                    "(id INT PRIMARY KEY," +
                    "size_of_area VarChar(255)," +
                    "used_capacity INT," +
                    "available_capacity INT," +
                    "total_capacity INT)";

            // I based the total capacity of each area by counting the spaces in that area.
            String insertArea = "INSERT INTO areas" +
                    "(id, size_of_area, used_capacity, available_capacity, total_capacity) " +
                    "SELECT c.id, c.sz, c.uc, c.ac, c.tc " +
                    "FROM (SELECT 0 as id, 'small' as sz, 0 as uc, 46 as ac, 46 as tc UNION ALL " +
                    "SELECT 1 as id, 'medium' as sz, 0 as uc, 14 as ac, 14 as tc UNION ALL " +
                    "SELECT 2 as id, 'large' as sz, 0 as uc, 12 as ac, 12 as tc ) c " +
                    "WHERE NOT EXISTS (SELECT * FROM areas)";

            String createTableBoxes = "CREATE TABLE IF NOT EXISTS boxes" +
                    "(id INT AUTO_INCREMENT PRIMARY KEY," +
                    "customer_id INT," +
                    "FOREIGN KEY (customer_id) references customers(id)," +
                    "type_of_box VarChar(255)," +
                    "stored_at TIMESTAMP," +
                    "retrieved_at TIMESTAMP)";

            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableCustomers);
            statement.executeUpdate(createTableAreas);
            statement.executeUpdate(insertArea);
            statement.executeUpdate(createTableBoxes);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }
}
