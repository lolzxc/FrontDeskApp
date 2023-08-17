package org.FrontDeskApp.Area;

import org.FrontDeskApp.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDaoImpl implements AreaDao{

    private static final String
            SELECT_BY_ID = "SELECT id, size_of_area, used_capacity, available_capacity, " +
            "total_capacity FROM areas WHERE ID = ?";

    private static final String SELECT_ALL_AREAS = "SELECT * FROM areas";

    private static final String UPDATE_AREA = "UPDATE areas set available_capacity = ?, " +
            "used_capacity = ? WHERE id = ?";
    @Override
    public Area get(int id) throws SQLException {
        Connection conn = Database.getConnection();
        Area area = null;

        PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int oid = rs.getInt("id");
            String sizeOfArea = rs.getString("size_of_area");
            int used_capacity = rs.getInt("used_capacity");
            int available_capacity = rs.getInt("available_capacity");
            int total_capacity = rs.getInt("total_capacity");
            area = new Area(oid, sizeOfArea, used_capacity, available_capacity, total_capacity);
        }

        Database.closeConnection(conn);
        return area;
    }

    @Override
    public List<Area> getAll() throws SQLException {
        List<Area> areas = new ArrayList<>();

        Connection conn = Database.getConnection();

        PreparedStatement ps = conn.prepareStatement(SELECT_ALL_AREAS);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int oid = rs.getInt("id");
            String sizeOfArea = rs.getString("size_of_area");
            int used_capacity = rs.getInt("used_capacity");
            int available_capacity = rs.getInt("available_capacity");
            int total_capacity = rs.getInt("total_capacity");
            areas.add(new Area(oid, sizeOfArea, used_capacity, available_capacity, total_capacity));
        }

        Database.closeConnection(conn);
        return areas;
    }
    @Override
    public int insert(Area area) throws SQLException {
        return 0;
    }
    @Override
    public void update(Area area) throws SQLException {
        Connection conn = Database.getConnection();

        PreparedStatement ps = conn.prepareStatement(UPDATE_AREA);

        ps.setInt(1, area.getAvailableCapacity());
        ps.setInt(2, area.getUsedCapacity());
        ps.setInt(3, area.getId());

        ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(conn);
    }
}
