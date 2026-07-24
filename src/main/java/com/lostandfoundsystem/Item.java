package com.lostandfoundsystem;

public abstract class Item {

    private String name;
    private String location;
    private String time;
    private String description;
    private String phone;
    private String status; // Theme Badges Support ("Pending" / "Matched")

    public Item(String name, String location) {
        setName(name);
        setLocation(location);
        this.status = "Pending";
    }

    public Item(String name, String location, String time,
                String description, String phone) {
        this(name, location);
        setTime(time);
        setDescription(description);
        setPhone(phone);
        this.status = "Pending";
    }

    // Abstract Methods
    public abstract void displayInfo();

    public abstract String getType();

    // Getters & Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        } else {
            this.status = "Pending";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || !name.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Invalid Name! Only letters and spaces are allowed.");
        }
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            this.location = "Unknown";
        } else {
            this.location = location;
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Time!");
        }
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Description!");
        }
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty() || !phone.matches("\\d{11}")) {
            throw new IllegalArgumentException("Invalid Phone Number! Must be 11 digits.");
        }
        this.phone = phone;
    }
}
