package com.ticketing.model;

public class Reservation {
    private String customerName;
    private Event event;
    private Seat seat;

    public Reservation(String customerName, Event event, Seat seat) {
        this.customerName = customerName;
        this.event = event;
        this.seat = seat;
    }
    public String getCustomerName() {
        return customerName;
    }
    public Event getEvent() {
        return event;
    }
    public Seat getSeat() {
        return seat;
    }
    @Override
    public String toString() {
        return "Customer : " + customerName + "\n" +
                "Event    : " + event.getTitle() + "\n" +
                "Venue    : " + event.getVenue().getName() + "\n" +
                "Seat     : " + seat;
    }
    }


