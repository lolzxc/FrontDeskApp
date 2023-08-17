package org.FrontDeskApp.Box;

import java.sql.SQLException;
import java.util.List;

public class BoxService {

    public BoxDao boxDao;

    public void insertBox(Box box) throws SQLException {
        boxDao = new BoxDaoImpl();
        int generatedKey = boxDao.insert(box);
        box.setId(generatedKey);
    }
    public List<String> getBox(int id) throws SQLException {
        boxDao = new BoxDaoImpl();
        return boxDao.getBox(id);
    }
    public void updateBox(Box box) throws SQLException {
        boxDao = new BoxDaoImpl();
        boxDao.update(box);
    }
}
