package com.ticketing.model;

public class Venue {

    private String name;
    private String address;
    private String timezone;

    public Venue(String name, String address, String timezone) {
        this.name = name;
        this.address = address;
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTimezone() {
        return timezone;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}