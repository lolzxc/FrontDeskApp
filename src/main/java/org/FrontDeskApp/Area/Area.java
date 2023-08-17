package org.FrontDeskApp.Area;

public class Area {

    private int id;

    public String sizeOfArea;
    private int totalCapacity;
    private int availableCapacity;

    private int usedCapacity;

    public String getSizeOfArea() {
        return sizeOfArea;
    }

    public void setSizeOfArea(String sizeOfArea) {
        this.sizeOfArea = sizeOfArea;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Area() {}

    public Area(int availableCapacity, int usedCapacity,  int id) {
        this.id = id;
        this.usedCapacity = usedCapacity;
        this.availableCapacity = availableCapacity;
    }
    public Area(int id, String sizeOfArea, int usedCapacity, int availableCapacity, int totalCapacity) {
        this.id = id;
        this.sizeOfArea = sizeOfArea;
        this.usedCapacity = usedCapacity;
        this.availableCapacity = availableCapacity;
        this.totalCapacity = totalCapacity;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", sizeOfArea='" + sizeOfArea + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", availableCapacity=" + availableCapacity +
                ", usedCapacity=" + usedCapacity +
                '}';
    }
}
