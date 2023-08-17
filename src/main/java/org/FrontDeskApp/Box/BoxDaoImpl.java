package org.FrontDeskApp.Box;

import org.FrontDeskApp.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoxDaoImpl implements BoxDao{

    private static final String
            SELECT_BY_ID = "SELECT id, customer_id, type_of_box, stored_at, retrieved_at " +
            "FROM boxes WHERE ID = ?";

    private static final String SELECT_ALL_BOXES = "SELECT * FROM boxes";

    private static final String
            INSERT_BOX = "INSERT INTO boxes (customer_id, type_of_box, stored_at, retrieved_at) VALUES"
            + " (?, ?, ?, ?);";

    private static final String UPDATE_BOX = "UPDATE boxes set retrieved_at = ? " +
            "WHERE id = ?";

    @Override
    public Box get(int id) throws SQLException {
        Connection conn = Database.getConnection();
        Box box = null;

        PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int oid = rs.getInt("id");
            int customerId = rs.getInt("customer_id");
            String typeOfBox = rs.getString("type_of_box");
            String storedAt = rs.getString("stored_at");
            String retrievedAt = rs.getString("retrieved_at");
            box = new Box(oid, customerId, typeOfBox, storedAt, retrievedAt);
        }

        Database.closeConnection(conn);
        return box;
    }

    @Override
    public List<Box> getAll() throws SQLException {
        List<Box> boxes = new ArrayList<>();

        Connection conn = Database.getConnection();

        PreparedStatement ps = conn.prepareStatement(SELECT_ALL_BOXES);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int oid = rs.getInt("id");
            int customerId = rs.getInt("customer_id");
            String typeOfBox = rs.getString("type_of_box");
            String storedAt = rs.getString("stored_at");
            String retrievedAt = rs.getString("retrieved_at");
            boxes.add(new Box(oid, customerId, typeOfBox, storedAt, retrievedAt));
        }

        Database.closeConnection(conn);
        return boxes;
    }

    @Override
    public int insert(Box box) throws SQLException {
        Connection conn = Database.getConnection();

        PreparedStatement ps = conn.prepareStatement(INSERT_BOX, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, box.getCustomerId());
        ps.setString(2, box.getTypeOfBox());
        ps.setString(3, box.getStoredAt());
        ps.setString(4, box.getRetrievedAt());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        int generatedKey = 0;

        if(rs.next()) {
            generatedKey = rs.getInt(1);
        }

        Database.closePreparedStatement(ps);
        Database.closeConnection(conn);

        return generatedKey;
    }

    @Override
    public void update(Box box) throws SQLException {
        Connection conn = Database.getConnection();

        PreparedStatement ps = conn.prepareStatement(UPDATE_BOX);

        ps.setString(1, box.getRetrievedAt());
        ps.setInt(2, box.getId());

        ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(conn);
    }

    public List<String> getBox(int id) throws SQLException {
        Connection conn = Database.getConnection();

        List<String> customer = new ArrayList<>();

        String sql = "SELECT c.first_name, c.last_name, b.id " +
                "FROM customers AS c " +
                "JOIN boxes AS b " +
                "WHERE b.id = ? AND b.retrieved_at IS NULL";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String boxId = rs.getString("id");
            customer.add(firstName);
            customer.add(lastName);
            customer.add(boxId);
        }
        return customer;
    }
}
