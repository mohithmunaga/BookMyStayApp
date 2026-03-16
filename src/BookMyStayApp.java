import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class BookingService {

    private RoomInventory inventory;
    private Queue<Reservation> requestQueue;
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    private Set<String> usedRoomIds = new HashSet<>();
    private int idCounter = 1;

    BookingService(RoomInventory inventory, Queue<Reservation> requestQueue) {
        this.inventory = inventory;
        this.requestQueue = requestQueue;
    }

    public void processBookings() {

        while (!requestQueue.isEmpty()) {

            Reservation r = requestQueue.poll();
            String type = r.roomType;

            if (inventory.getAvailability(type) > 0) {

                String roomId = type.substring(0,1) + idCounter++;

                while (usedRoomIds.contains(roomId)) {
                    roomId = type.substring(0,1) + idCounter++;
                }

                usedRoomIds.add(roomId);

                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                inventory.decrement(type);

                System.out.println("Reservation Confirmed: "
                        + r.guestName + " -> Room ID: " + roomId + " (" + type + ")");
            }
            else {
                System.out.println("No availability for " + r.guestName + " (" + type + ")");
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - Hotel Booking System v6.1 =====");

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Double Room"));
        queue.add(new Reservation("Charlie", "Single Room"));
        queue.add(new Reservation("David", "Suite Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService bookingService = new BookingService(inventory, queue);

        bookingService.processBookings();
    }
}