package com.lostandfoundsystem;

public class LostItem extends Item {

    // Default constructor with "Pending" status
    public LostItem(String name, String location, String time,
                    String description, String phone) {
        super(name, location, time, description, phone);
    }

    // Overloaded constructor if status needs to be set explicitly
    public LostItem(String name, String location, String time,
                    String description, String phone, String status) {
        super(name, location, time, description, phone);
        setStatus(status);
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
        return "Lost";
    }
}