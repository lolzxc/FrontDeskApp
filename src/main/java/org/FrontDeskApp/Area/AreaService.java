package org.FrontDeskApp.Area;

import java.sql.SQLException;
import java.util.List;

public class AreaService {
    public AreaDao areaDao;
    public List<Area> getAllArea() throws SQLException {
        areaDao = new AreaDaoImpl();
        return areaDao.getAll();
    }
    public Area get(int id) throws SQLException {
        areaDao = new AreaDaoImpl();
        return areaDao.get(id);
    }
    public void update(Area area) throws SQLException {
        areaDao = new AreaDaoImpl();
        areaDao.update(area);
    }
}
