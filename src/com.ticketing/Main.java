package com.ticketing;

import com.ticketing.model.Event;
import com.ticketing.service.TicketService;
import com.ticketing.storage.StorageService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TicketService ticketService = new TicketService();
        StorageService storageService = new StorageService();
        storageService.testLoad();

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("      TICKETING SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Create Venue");
            System.out.println("2. List Venues");
            System.out.println("3. create Event");
            System.out.println("4. List Events");
            System.out.println("5. Add Seats to Event");
            System.out.println("6. List Seats ");
            System.out.println("7. Make Reservation");
            System.out.println("8. List Reservations");
            System.out.println("9. Cancel Reservation");
            System.out.println("10.Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    ticketService.createVenue(scanner);
                    break;
                case 2:
                    ticketService.listVenues();
                    break;
                case 3:
                    ticketService.createEvent(scanner);
                    break;
                case 4:
                    ticketService.listEvents();
                    break;
                case 5:
                    ticketService.addSeat(scanner);
                    break;
                case 6:
                    ticketService.listsSeats(scanner);
                    break;
                case 7:
                    ticketService.makeReservation(scanner);
                    break;
                case 8:
                    ticketService.listReservations();
                    break;
                case 9:
                    ticketService.cancelReservation(scanner);
                    break;
                case 10:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();

        }
    }
