package ticketbookingsystem;

class Bus {
    private String busNumber;
    private String busName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private Bus next;

    public Bus(String busNumber, String busName, String source, String destination, int totalSeats) {
        this.busNumber = busNumber;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.next = null;
    }

    // Getters and setters...

    public void decrementAvailableSeats(int numTickets) {
        if (numTickets > 0 && numTickets <= availableSeats) {
            availableSeats -= numTickets;
        }
    }

    public void incrementAvailableSeats(int numTickets) {
        if (availableSeats + numTickets <= totalSeats) {
            availableSeats += numTickets;
        }
    }

    public void displayBusDetails() {
        System.out.println("Bus Number: " + busNumber);
        System.out.println("Bus Name: " + busName);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Available Seats: " + availableSeats);
    }
		public String getBusNumber() {
			return busNumber;
		}
		public void setBusNumber(String busNumber) {
			this.busNumber = busNumber;
		}
		public String getBusName() {
			return busName;
		}
		public void setBusName(String busName) {
			this.busName = busName;
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
		public int getTotalSeats() {
			return totalSeats;
		}
		public void setTotalSeats(int totalSeats) {
			this.totalSeats = totalSeats;
		}
		public int getAvailableSeats() {
			return availableSeats;
		}
		public void setAvailableSeats(int availableSeats) {
			this.availableSeats = availableSeats;
		}
		public Bus getNext() {
	        return next;
	    }

	    public void setNext(Bus next) {
	        this.next = next;
	    }
}