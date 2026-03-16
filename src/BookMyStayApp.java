import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void validateRoom(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }
    }

    public void allocateRoom(String roomType) throws InvalidBookingException {
        validateRoom(roomType);
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

class BookingService {

    private RoomInventory inventory;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processReservation(Reservation reservation) {
        try {
            inventory.allocateRoom(reservation.roomType);
            System.out.println("Booking confirmed for "
                    + reservation.guestName + " (" + reservation.roomType + ")");
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - Hotel Booking System v9.1 =====");

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);

        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Suite Room");
        Reservation r3 = new Reservation("Charlie", "Luxury Room"); // invalid
        Reservation r4 = new Reservation("David", "Suite Room"); // may fail if unavailable

        bookingService.processReservation(r1);
        bookingService.processReservation(r2);
        bookingService.processReservation(r3);
        bookingService.processReservation(r4);

        System.out.println();
        inventory.displayInventory();
    }
}