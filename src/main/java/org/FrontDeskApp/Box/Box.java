package org.FrontDeskApp.Box;

public class Box {

    private int id;
    private int customerId;
    private String typeOfBox;
    private String storedAt;
    private String retrievedAt;

    public Box() {}

    public Box(String retrievedAt, int id) {
        this.retrievedAt = retrievedAt;
        this.id = id;
    }
    public Box(int customerId, String typeOfBox, String storedAt, String retrievedAt) {
        this.customerId = customerId;
        this.typeOfBox = typeOfBox;
        this.storedAt = storedAt;
        this.retrievedAt = retrievedAt;
    }
    public Box(int id, int customerId, String typeOfBox, String storedAt, String retrievedAt) {
        this.id = id;
        this.customerId = customerId;
        this.typeOfBox = typeOfBox;
        this.storedAt = storedAt;
        this.retrievedAt = retrievedAt;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", typeOfBox='" + typeOfBox + '\'' +
                ", storedAt='" + storedAt + '\'' +
                ", retrievedAt='" + retrievedAt + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTypeOfBox() {
        return typeOfBox;
    }

    public void setTypeOfBox(String typeOfBox) {
        this.typeOfBox = typeOfBox;
    }

    public String getStoredAt() {
        return storedAt;
    }

    public void setStoredAt(String storedAt) {
        this.storedAt = storedAt;
    }

    public String getRetrievedAt() {
        return retrievedAt;
    }

    public void setRetrievedAt(String retrievedAt) {
        this.retrievedAt = retrievedAt;
    }
}
