package ticketbookingsystem;

class Node {
    Bus bus;
    Node next;

    public Node(Bus bus) {
        this.bus = bus;
        this.next = null;
    }
}

class Admin {
    // A list to store all buses in the system
    private Node head;

    public Admin() {
        head = null;
    }

 // Method to add a new bus to the system
    public void addBus(String busNumber, String busName, String source, String destination, int totalSeats) {
        try {
            Bus newBus = new Bus(busNumber, busName, source, destination, totalSeats);
            Node newNode = new Node(newBus);

            // Insert the new node at the beginning of the list
            newNode.next = head;
            head = newNode;

            System.out.println("Bus added successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while adding the bus: " + e.getMessage());
        }
    }


 // Method to delete a bus from the system
    public void deleteBus(String busNumber) {
        try {
            Node prev = null;
            Node curr = head;

            while (curr != null && !curr.bus.getBusNumber().equals(busNumber)) {
                prev = curr;
                curr = curr.next;
            }

            if (curr != null) {
                // Remove the node
                if (prev == null) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }

                System.out.println("Bus with bus number " + busNumber + " has been deleted.");
            } else {
                System.out.println("Bus with bus number " + busNumber + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the bus: " + e.getMessage());
        }
    }


    // Method to update bus details
    public void updateBus(String busNumber, String newBusName, String newSource, String newDestination, int newTotalSeats) {
        try {
            Node curr = head;

            while (curr != null && !curr.bus.getBusNumber().equals(busNumber)) {
                curr = curr.next;
            }

            if (curr != null) {
                // Update the bus details
                curr.bus.setBusName(newBusName);
                curr.bus.setSource(newSource);
                curr.bus.setDestination(newDestination);
                curr.bus.setTotalSeats(newTotalSeats);

                System.out.println("Bus details updated successfully.");
            } else {
                System.out.println("Bus with bus number " + busNumber + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while updating the bus details: " + e.getMessage());
        }
    }

    // Method to display all buses in the system
    public void displayAllBuses() {
        Node curr = head;
        if (curr == null) {
            System.out.println("No buses found in the system.");
        } else {
            System.out.println("List of all buses in the system:");
            while (curr != null) {
                System.out.println(curr.bus.getBusNumber() + " | " + curr.bus.getBusName() + " | " + curr.bus.getSource() + " -> " + curr.bus.getDestination() + " | Available Seats: " + curr.bus.getAvailableSeats());
                curr = curr.next;
            }
        }
    }
}
