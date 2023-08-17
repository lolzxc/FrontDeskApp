package org.FrontDeskApp;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    T get(int id) throws SQLException;

    List<T> getAll() throws  SQLException;

    int insert(T t) throws SQLException;

    void update(T t) throws SQLException;

}
