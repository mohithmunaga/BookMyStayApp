import java.util.*;

class Reservation {

    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {

        System.out.println("\nBooking History:");

        for (Reservation r : reservations) {
            r.display();
        }
    }

    public void generateSummary(List<Reservation> reservations) {

        System.out.println("\nTotal Confirmed Bookings: " + reservations.size());

        Map<String, Integer> roomSummary = new HashMap<>();

        for (Reservation r : reservations) {
            roomSummary.put(r.roomType,
                    roomSummary.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("\nBookings by Room Type:");

        for (Map.Entry<String, Integer> entry : roomSummary.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - Hotel Booking System v8.1 =====");

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("R101", "Alice", "Single Room"));
        history.addReservation(new Reservation("R102", "Bob", "Double Room"));
        history.addReservation(new Reservation("R103", "Charlie", "Suite Room"));
        history.addReservation(new Reservation("R104", "David", "Single Room"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getHistory());

        reportService.generateSummary(history.getHistory());
    }
}