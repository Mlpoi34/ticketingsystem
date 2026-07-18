package com.ticketing.model;

public class Seat {

    private String section;
    private String row;
    private int number;
    private boolean reserved = false;

    public Seat(String section, String row, int number) {
        this.section = section;
        this.row = row;
        this.number = number;
    }

    public String getSection() {
        return section;
    }

    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }
    public boolean isReserved() {
        return reserved;
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return section + " " + row + "-" + number;
    }
}