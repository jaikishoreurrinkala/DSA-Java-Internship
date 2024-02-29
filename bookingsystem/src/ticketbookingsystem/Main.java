package ticketbookingsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BusTicketBookingSystem bookingSystem = new BusTicketBookingSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("========== Bus Ticket Booking System ==========");
            System.out.println("1. Admin Mode");
            System.out.println("2. User Mode");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        adminMode(bookingSystem, scanner);
                        break;
                    case 2:
                        userMode(bookingSystem, scanner);
                        break;
                    case 3:
                        System.out.println("Have A Nice Journey :). Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
                choice = -1; // Set an invalid choice to continue the loop
            }

            System.out.println();
        } while (choice != 3);

        scanner.close();
    }

    private static void adminMode(BusTicketBookingSystem bookingSystem, Scanner scanner) {
        // Admin Mode
        System.out.println();
        System.out.println("Hello Admin! Welcome!!!!");
        String adminPassword = "admin123"; // You can set your admin password here
        System.out.print("Enter the admin password: ");
        String enteredPassword = scanner.next();

        if (enteredPassword.equals(adminPassword)) {
            int adminChoice;
            do {
                System.out.println("========== Admin Mode ==========");
                System.out.println("1. Add Bus Details");
                System.out.println("2. Delete Bus Details");
                System.out.println("3. Update Bus Details");
                System.out.println("4. Display Bus Details");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                adminChoice = scanner.nextInt();

                switch (adminChoice) {
                    case 1:
                        // Add Bus Details
                        addBusDetails(bookingSystem, scanner);
                        break;
                    case 2:
                        // Delete Bus Details
                        System.out.print("Enter the bus number to delete: ");
                        String deleteBusNumber = scanner.next();
                        boolean isDeleted = bookingSystem.deleteBus(deleteBusNumber);
                        if (isDeleted) {
                            System.out.println("Bus details deleted successfully.");
                        } else {
                            System.out.println("Bus with bus number " + deleteBusNumber + " not found.");
                        }
                        break;
                    case 3:
                        // Update Bus Details
                        updateBusDetails(bookingSystem, scanner);
                        break;
                    case 4:
                        // Display Bus Details
                        bookingSystem.displayAvailableBuses();
                        break;
                    case 5:
                        System.out.println("Exiting Admin Mode.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                System.out.println();
            } while (adminChoice != 5);
        } else {
            System.out.println("Invalid password. Exiting Admin Mode.");
        }
    }

    private static void addBusDetails(BusTicketBookingSystem bookingSystem, Scanner scanner) {
        System.out.print("Enter bus number: ");
        String busNumber = scanner.next();

        System.out.print("Enter bus name: ");
        String busName = scanner.next();

        System.out.print("Enter source: ");
        String source = scanner.next();

        // Consume the newline character
        scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        int totalSeats = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.print("Enter total seats: ");
                totalSeats = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        bookingSystem.addBus(new Bus(busNumber, busName, source, destination, totalSeats));
        System.out.println("Bus details added successfully.");
    }

    private static void updateBusDetails(BusTicketBookingSystem bookingSystem, Scanner scanner) {
        System.out.print("Enter the bus number to update: ");
        String busNumber = scanner.next();

        Bus bus = bookingSystem.getBusByNumber(busNumber);
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }

        System.out.print("Enter new bus name: ");
        String newBusName = scanner.next();

        // Consume the newline character
        scanner.nextLine();

        System.out.print("Enter new source: ");
        String newSource = scanner.nextLine();

        System.out.print("Enter new destination: ");
        String newDestination = scanner.nextLine();

        int newTotalSeats = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.print("Enter new total seats: ");
                newTotalSeats = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        bus.setBusName(newBusName);
        bus.setSource(newSource);
        bus.setDestination(newDestination);
        bus.setTotalSeats(newTotalSeats);
        System.out.println("Bus details updated successfully.");
    }

    private static void userMode(BusTicketBookingSystem bookingSystem, Scanner scanner) {
        // User Mode
        System.out.print("Enter your email: ");
        String userEmail = scanner.next();

        User user = new User(userEmail);

        int userChoice;
        do {
            System.out.println("========== User Mode ==========");
            System.out.println("1. Display available buses");
            System.out.println("2. Book a ticket");
            System.out.println("3. Cancel a ticket");
            System.out.println("4. View booked details by reference number");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    // Display available buses
                    bookingSystem.displayAvailableBuses();
                    break;
                case 2:
                    // Book a ticket
                    bookTicket(bookingSystem, scanner, user);
                    break;
                case 3:
                    // Cancel a ticket
                    System.out.print("Enter reference number to cancel the ticket: ");
                    int cancelReferenceNumber = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character from the previous input
                    System.out.print("Enter passenger name to cancel the ticket: ");
                    String passengerName = scanner.nextLine();
                    boolean canceled = bookingSystem.cancelTicket(cancelReferenceNumber, passengerName);
                    if (canceled) {
                        System.out.println("Ticket canceled successfully!");
                    } else {
                        System.out.println("Failed to cancel the ticket. Invalid reference number or passenger name.");
                    }
                    break;
                case 4:
                 // View booked details by reference number
                    System.out.print("Enter reference number to view booking details: ");
                    int viewReferenceNumber = scanner.nextInt();
                    Booking bookedTicket = bookingSystem.viewBookingByReferenceNumber(viewReferenceNumber);
        
                    if (bookedTicket != null) {
                        System.out.println("========== Booked Ticket Details ==========");
                        System.out.printf("| %-20s | %-30s |%n", "Field", "Value");
                        System.out.println("+----------------------+------------------------------+");

                        System.out.printf("| %-20s | %-30s |%n", "Reference Number", bookedTicket.getReferenceNumber());
                        System.out.printf("| %-20s | %-30s |%n", "User Email", bookedTicket.getUser().getEmail());
                        System.out.printf("| %-20s | %-30s |%n", "Bus Number", bookedTicket.getBus().getBusNumber());
                        System.out.printf("| %-20s | %-30s |%n", "Bus Name", bookedTicket.getBus().getBusName());
                        System.out.printf("| %-20s | %-30s |%n", "Source", bookedTicket.getBus().getSource());
                        System.out.printf("| %-20s | %-30s |%n", "Destination", bookedTicket.getBus().getDestination());

                        // Add more fields and data as needed

                        System.out.println("+----------------------+------------------------------+");
                    } else {
                        System.out.println("No booking found with the given reference number.");
                    } 
                    break;



                case 5:
                    System.out.println("Exiting User Mode.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        } while (userChoice != 5);
    }

    private static void bookTicket(BusTicketBookingSystem bookingSystem, Scanner scanner, User user) {
        System.out.print("Enter bus number: ");
        String busNumber = scanner.next();

        Bus bus = bookingSystem.getBusByNumber(busNumber);
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }

        System.out.print("Enter number of tickets to book: ");
        int numTickets = scanner.nextInt();

        // Create a new User object with the given email
        User newUser = new User(user.getEmail());

        int referenceNumber = bookingSystem.bookTickets(newUser, busNumber, numTickets);

        if (referenceNumber == -1) {
            System.out.println("Failed to book the tickets. Either the bus doesn't exist or there are not enough available seats.");
        } else {
            // Ask for passenger names for each ticket
            String[] passengerNames = new String[numTickets];
            for (int i = 0; i < numTickets; i++) {
                System.out.print("Enter name of passenger " + (i + 1) + ": ");
                passengerNames[i] = scanner.next();
            }

            // Save the passenger names in the booking details
            Booking bookedTicket = bookingSystem.viewBookingByReferenceNumber(referenceNumber);
            if (bookedTicket != null) {
                bookedTicket.setPassengerNames(passengerNames);
            }

            System.out.println("Tickets booked successfully! Your reference number is: " + referenceNumber);
        }
    }

}
