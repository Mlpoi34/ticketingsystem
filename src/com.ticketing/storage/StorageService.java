package com.ticketing.storage;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.ticketing.model.Seat;

import com.ticketing.model.Event;
import com.ticketing.model.Venue;
import java.util.ArrayList;

public class StorageService {

    public void save() {
        System.out.println("Saving data...");
    }

    public void load() {
        System.out.println("Loading data...");
    }

    public void saveVenues(ArrayList<Venue> venues) {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();
            FileWriter writer = new FileWriter("data\\venues.txt");

            for (Venue venue : venues) {

                writer.write(
                        venue.getName() + "|" +
                                venue.getAddress() + "|" +
                                venue.getTimezone() + "\n"
                );
            }
            writer.close();
            System.out.println("Venue saved successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the venue");
        }


    }

    public void testLoad() {
        try {
            File file = new File("data\\venues.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public ArrayList<Venue> loadVenues() {
        ArrayList<Venue> venues = new ArrayList<>();
        try {
            File file = new File("data\\venues.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    Venue venue = new Venue(
                            parts[0],
                            parts[1],
                            parts[2]
                    );
                    venues.add(venue);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return venues;
    }


    public void saveEvents(ArrayList<Event> events) {
        try {
            FileWriter writer = new FileWriter("data\\events.txt");
            System.out.println("number of events: " + events.size());
            for (Event event : events) {
                System.out.println("saving:" + event.getTitle());
                writer.write(
                        event.getTitle() + "|" +
                                event.getVenue().getName() + "\n"
                );

            }
            writer.close();
            System.out.println("Events saved successfully");

        } catch (IOException e) {
            System.out.println("error saving events");
        }
    }

    public ArrayList<Event> loadEvents(ArrayList<Venue> venues) {
        ArrayList<Event> events = new ArrayList<>();
        try {
            File file = new File("data\\events.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String title = parts[0];
                    String venueName = parts[1];
                    Venue venue = venues.stream()
                            .filter(v -> v.getName().equals(venueName))
                            .findFirst()
                            .orElse(null);
                    if (venue != null) {
                        Event event = new Event(title, venue);
                        events.add(event);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return events;
    }

    public void saveSeats(ArrayList<Event> events) {

        try {

            FileWriter writer = new FileWriter("data\\seats.txt");

            for (Event event : events) {

                for (Seat seat : event.getSeats()) {

                    writer.write(
                            event.getTitle() + "|" +
                                    seat.getSection() + "|" +
                                    seat.getRow() + "|" +
                                    seat.getNumber() + "|" +
                                    seat.isReserved() + "\n"
                    );

                }

            }

            writer.close();

            System.out.println("Seats saved successfully!");

        } catch (IOException e) {

            System.out.println("Error saving seats.");

        }

    }
    public void loadSeats(ArrayList<Event> events) {

        try {

            File file = new File("data\\seats.txt");

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                String[] parts = line.split("\\|");

                if (parts.length == 5) {

                    String eventTitle = parts[0];
                    String section = parts[1];
                    String row = parts[2];
                    int number = Integer.parseInt(parts[3]);
                    boolean reserved = Boolean.parseBoolean(parts[4]);

                    for (Event event : events) {

                        if (event.getTitle().equals(eventTitle)) {

                            Seat seat = new Seat(section, row, number);

                            seat.setReserved(reserved);

                            event.getSeats().add(seat);

                            break;
                        }

                    }

                }

            }

            reader.close();

            System.out.println("Seats loaded successfully!");

        } catch (FileNotFoundException e) {

            System.out.println("No seats file found.");

        }

    }

}