package com.ticketing.service;

import com.ticketing.model.Event;
import com.ticketing.model.Seat;
import com.ticketing.model.Venue;
import com.ticketing.model.Reservation;
import com.ticketing.storage.StorageService;


import java.util.ArrayList;
import java.util.Scanner;

public class TicketService {

  private StorageService storageService = new StorageService();

  private ArrayList<Venue> venues = storageService.loadVenues();
    private ArrayList<Event> events = storageService.loadEvents(venues);
    private ArrayList<Seat> seats = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();


    public void createVenue(Scanner scanner) {

        System.out.print("Enter venue name: ");
        String name = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter timezone: ");
        String timezone = scanner.nextLine();

        Venue venue = new Venue(name, address, timezone);

        venues.add(venue);

        System.out.println();
        storageService.saveVenues(venues);
        System.out.println("Venue created successfully!");
        System.out.println(venue);
        System.out.println("Total venues: " + venues.size());
    }

    public void listVenues() {

        if (venues.isEmpty()) {
            System.out.println("No venues found.");
            return;
        }
        System.out.println();
        System.out.println("===========VENUES===========");

        for (Venue venue1 : venues) {
            System.out.println(venue1);
        }
        System.out.println("===========================");
    }

    public void createEvent(Scanner scanner) {

        if (venues.isEmpty()) {
            System.out.println("Please create a venue first");
            return;
        }
        System.out.print("Enter event title: ");
        String title = scanner.nextLine();
        System.out.println("DEBUG Title = [" + title + "]");

        System.out.println();
        System.out.println("Choose a venue:");

        for (int i = 0; i < venues.size(); i++) {
            System.out.println((i + 1) + ", " + venues.get(i).getName());
        }
        System.out.println("Choice: ");
        int venueChoice = scanner.nextInt();
        scanner.nextLine();

        Venue selectedVenue = venues.get(venueChoice - 1);
        Event event = new Event(title, selectedVenue);
        events.add(event);
        storageService.saveEvents(events);
        System.out.println();
        System.out.println("Event created successfully!");
    }


        public void listEvents() {

            if (events.isEmpty()) {
                System.out.println("No events found.");
                return;
            }

            System.out.println();
            System.out.println("========== EVENTS ==========");

            for (Event event : events) {
                System.out.println(
                        event.getTitle() + " - " + event.getVenue().getName()
                );
            }

            System.out.println("============================");
        }

        public void addSeat(Scanner scanner) {
            if (events.isEmpty()) {
                System.out.println("please create an event first");
                return;
            }
            System.out.println("\nChoose an event:");
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i).getTitle());
            }
            System.out.println("Choice: ");
            int eventChoice = scanner.nextInt();
            scanner.nextLine();
            Event selectedEvent = events.get(eventChoice - 1);
            System.out.println("Section: ");
            String section = scanner.nextLine();
            System.out.println("Row: ");
            String row = scanner.nextLine();
            System.out.println("Seat number: ");
            int number = scanner.nextInt();
            scanner.nextLine();
            Seat selectedSeat = new Seat(section, row, number);
            selectedEvent.getSeats().add(selectedSeat);
            System.out.println();
            System.out.println("Seat added successfully!");
        }
        public void listsSeats(Scanner scanner) {
            if (events.isEmpty()) {
                System.out.println("no events found.");
                return;
            }
            System.out.println("\nChoose an event:");
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i).getTitle());
            }
            System.out.print("Choice: ");
            int eventChoice = scanner.nextInt();
            scanner.nextLine();
            Event event = events.get(eventChoice - 1);
            if (event.getSeats().isEmpty()) {
                System.out.println("No seats found.");
                return;

            }
            System.out.println("\nSection: " + event.getTitle());
            for (Seat seat : event.getSeats()) {
                System.out.println(seat);
            }

        }
    public void makeReservation(Scanner scanner) {
        if (events.isEmpty()) {
            System.out.println("Please create an event first.");
            return;
        }
        System.out.println("Choose an event:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).getTitle());
        }
        System.out.print("Choice: ");
        int eventChoice = scanner.nextInt();
        scanner.nextLine();
        Event event = events.get(eventChoice - 1);
        if (event.getSeats().isEmpty()) {
            System.out.println("this event has no seats.");
            return;
        }
        System.out.println("\nChoose a seat:");
        for (int i = 0; i < event.getSeats().size(); i++) {
            System.out.println((i + 1) + ". " + event.getSeats().get(i));
        }
        System.out.print("Choice: ");
        int seatChoice = scanner.nextInt();
        scanner.nextLine();
        Seat seat = event.getSeats().get(seatChoice - 1);
        if (seat.isReserved()) {
            System.out.println("This seat is already reserved.");
            return;
        }
        System.out.print("customer name: ");
        String customerName = scanner.nextLine();
        Reservation reservation = new Reservation(customerName, event, seat);
        reservations.add(reservation);
        seat.setReserved(true);
        System.out.println("Reservation made successfully!");
    }

    public void listReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

            System.out.println();
            System.out.println("========== RESERVATIONS ==========");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
            System.out.println("===================================");
        }

    public void cancelReservation(Scanner scanner) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        System.out.println("\nReservation:");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println(i + 1 + ". " + reservations.get(i));
        }
        System.out.print("Choice reservations to cancel: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Reservation reservation = reservations.get(choice - 1);
        reservation.getSeat().setReserved(false);
        reservations.remove(choice - 1);
        System.out.println("Reservation canceled successfully!");


        }
    }

