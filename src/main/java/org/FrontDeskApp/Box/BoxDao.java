package org.FrontDeskApp.Box;

import org.FrontDeskApp.Dao;

import java.sql.SQLException;
import java.util.List;

public interface BoxDao extends Dao<Box> {
    List<String> getBox(int id) throws SQLException;
}
