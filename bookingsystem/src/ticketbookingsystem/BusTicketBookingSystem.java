package ticketbookingsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Date;

public class BusTicketBookingSystem {
    private Bus headBuses;
    private int referenceNumberCounter;
    private Map<String, Integer> bookedTicketsPerBus;
    private List<Booking> bookings;

    public BusTicketBookingSystem() {
        headBuses = null;
        Random random = new Random();
        referenceNumberCounter = random.nextInt(1001) + 1000;
        bookedTicketsPerBus = new HashMap<>();
        bookings = new ArrayList<>();
    }

	// Methods to add, delete, and update bus details
	public void addBus(Bus bus) {
        if (headBuses == null) {
            headBuses = bus;
        } else {
            Bus current = headBuses;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(bus);
        }
    }

	public boolean deleteBus(String busNumber) {
	    Bus previous = null;
	    Bus current = headBuses;

	    while (current != null) {
	        if (current.getBusNumber().equals(busNumber)) {
	            // Remove the current bus node from the list
	            if (previous == null) {
	                // If the current bus node is the first node, update the headBuses
	                headBuses = current.getNext();
	            } else {
	                // If the current bus node is in the middle or end, update the pointers
	                previous.setNext(current.getNext());
	            }
	            return true; // Bus found and deleted successfully
	        }

	        // Move to the next node
	        previous = current;
	        current = current.getNext();
	    }

	    return false; // Bus not found
	}


	public void updateBus(String busNumber, Bus updatedBus) {
		Bus current = headBuses;

		while (current != null) {
			if (current.getBusNumber().equals(busNumber)) {
				// Update the current bus node with the details from the updatedBus
				current.setBusNumber(updatedBus.getBusNumber());
				current.setBusName(updatedBus.getBusName());
				current.setSource(updatedBus.getSource());
				current.setDestination(updatedBus.getDestination());
				current.setTotalSeats(updatedBus.getTotalSeats());
				current.setAvailableSeats(updatedBus.getAvailableSeats());
				break;
			}

			// Move to the next node
			current = current.getNext();
		}
	}
	
	// Method to display all available buses with their details
	public void displayAvailableBuses() {
	    if (headBuses == null) {
	        System.out.println("No buses available in the system.");
	    } else {
	        System.out.println("========== Available Buses ==========");
	        System.out.printf("| %-15s | %-20s | %-20s | %-20s | %-15s |%n",
	                "Bus Number", "Bus Name", "Source", "Destination", "Total Seats");
	        System.out.println("+---------------+----------------------+----------------------+----------------------+---------------+");

	        Bus current = headBuses;
	        while (current != null) {
	            System.out.printf("| %-15s | %-20s | %-20s | %-20s | %-15s |%n",
	                    current.getBusNumber(), current.getBusName(), current.getSource(),
	                    current.getDestination(), current.getTotalSeats());
	            current = current.getNext();
	        }
	        System.out.println("+---------------+----------------------+----------------------+----------------------+---------------+");
	    }
	}


	 // Method to book a ticket
    public int bookTickets(User user, String busNumber, int numTickets) {
        Bus bus = getBusByNumber(busNumber);
        if (bus == null) {
            return -1; // Bus not found
        }

        if (bus.getAvailableSeats() < numTickets) {
            return -1; // Not enough available seats
        }

        Booking booking = new Booking(referenceNumberCounter, user, bus, new Date(), busNumber, busNumber, numTickets);
        boolean added = addBooking(booking);

        if (!added) {
            return -1; // Failed to add the booking
        }

        bus.decrementAvailableSeats(numTickets);

        bookedTicketsPerBus.put(busNumber, bookedTicketsPerBus.getOrDefault(busNumber, 0) + numTickets);

        Random random = new Random();
        referenceNumberCounter = random.nextInt(1001) + 1000; // Generate a new random number

        return booking.getReferenceNumber();
    }
    
 // Method to cancel a ticket
    public boolean cancelTicket(int referenceNumber, String passengerName) {
        Booking bookingToCancel = getBookingByReferenceNumber(referenceNumber);
        if (bookingToCancel == null) {
            return false; // No booking found with the given reference number
        }

        // Find the index of the passenger name to be canceled
        int passengerIndex = -1;
        String[] passengerNames = bookingToCancel.getPassengerNames();
        for (int i = 0; i < passengerNames.length; i++) {
            if (passengerNames[i] != null && passengerNames[i].equals(passengerName)) {
                passengerIndex = i;
                break;
            }
        }

        if (passengerIndex == -1) {
            return false; // Passenger name not found in the booking
        }

        // Cancel the ticket for the given passenger
        passengerNames[passengerIndex] = null;

        // Increment the available seats for the bus
        int numTicketsCanceled = 1;
        Bus bus = bookingToCancel.getBus();
        bus.incrementAvailableSeats(numTicketsCanceled);

        // Shift the remaining passenger names to fill the gap
        for (int i = passengerIndex + 1; i < passengerNames.length; i++) {
            if (passengerNames[i] != null) {
                passengerNames[i - 1] = passengerNames[i];
                passengerNames[i] = null;
            }
        }

        return true; // Ticket canceled successfully
    }

    private boolean addBooking(Booking booking) {
        return bookings.add(booking);
    }
	
    // Method to view passenger names for a booked ticket
    public String[] viewPassengerNamesByReferenceNumber(int referenceNumber) {
        Booking booking = getBookingByReferenceNumber(referenceNumber);
        if (booking != null) {
            return booking.getPassengerNames();
        } else {
            return null; // Booking with the given reference number not found
        }
    }
    
 // Method to view booked details using reference number
    public Booking viewBookingByReferenceNumber(int referenceNumber) {
        for (Booking booking : bookings) {
            if (booking.getReferenceNumber() == referenceNumber) {
                return booking;
            }
        }
        return null; // Booking with the given reference number not found
    }


	// Method to retrieve a bus by bus number
	 Bus getBusByNumber(String busNumber) {
	        Bus current = headBuses;
	        while (current != null) {
	            if (current.getBusNumber().equals(busNumber)) {
	                return current;
	            }
	            current = current.getNext();
	        }
	        return null; // Bus with the given bus number not found
	    }

	// Helper method to get a booking by reference number
	 private Booking getBookingByReferenceNumber(int referenceNumber) {
	        for (Booking booking : bookings) {
	            if (booking.getReferenceNumber() == referenceNumber) {
	                return booking;
	            }
	        }
	        return null; // Booking with the given reference number not found
	    }
}