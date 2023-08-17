package org.FrontDeskApp.Area;

import java.sql.SQLException;
import java.util.List;

public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    public List<Area> getAllArea() throws SQLException {
        return areaService.getAllArea();
    }
}
