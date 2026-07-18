package com.ticketing.model;
import java.util.ArrayList;


public class Event {

    private String title;
    private Venue venue;
    private ArrayList<Seat> seats = new ArrayList<>();

    public Event(String title, Venue venue) {
        this.title = title;
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public Venue getVenue() {
        return venue;
    }
    public ArrayList<Seat> getSeats() {
        return seats;
    }
}