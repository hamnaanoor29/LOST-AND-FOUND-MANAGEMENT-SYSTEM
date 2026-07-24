package com.lostandfoundsystem;

public class FoundItem extends Item {

    private String status; // Status field for UI badges ("Pending" / "Matched")

    // Default constructor with "Pending" status
    public FoundItem(String name, String location, String time,
                     String description, String phone) {
        super(name, location, time, description, phone);
        this.status = "Pending";
    }

    // Overloaded constructor if status is provided explicitly
    public FoundItem(String name, String location, String time,
                     String description, String phone, String status) {
        super(name, location, time, description, phone);
        this.status = (status != null && !status.isEmpty()) ? status : "Pending";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Location: " + getLocation());
        System.out.println("Time: " + getTime());
        System.out.println("Description: " + getDescription());
        System.out.println("Phone: " + getPhone());
        System.out.println("Type: " + getType() + " Item");
        System.out.println("Status: " + getStatus());
    }

    @Override
    public String getType() {
        return "Found";
    }
}
