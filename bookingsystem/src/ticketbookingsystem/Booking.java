package ticketbookingsystem;

import java.util.Date;

public class Booking {
	private int referenceNumber;
	private User user;
	private Bus bus;
	private Date bookingDate;
	private Booking next;
	private String source;
	private String destination;
    private String[] passengerNames; // Array to store passenger names

    public Booking(int referenceNumber, User user, Bus bus, Date bookingDate, String source, String destination, int numTickets) {
		super();
		this.referenceNumber = referenceNumber;
		this.user = user;
		this.bus = bus;
		this.bookingDate = bookingDate;
		this.next = null; 
		this.source=source;
		this.destination=destination;
        this.passengerNames = new String[numTickets];
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Booking getNext() {
		return next;
	}

	public void setNext(Booking next) {
		this.next = next;
	}
	// Method to set passenger name for a specific ticket
	public void setPassengerNames(String[] passengerNames) {
	    this.passengerNames = passengerNames;
	}

 
    // Method to get all passenger names
    public String[] getPassengerNames() {
        return passengerNames;
    }

}
