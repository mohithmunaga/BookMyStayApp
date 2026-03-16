import java.util.*;

class Service {

    String name;
    double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class AddOnServiceManager {

    private Map<String, List<Service>> reservationServices = new HashMap<>();

    public void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<Service> services = reservationServices.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.price;
            }
        }

        return total;
    }

    public void displayServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Services for Reservation " + reservationId + ":");

        for (Service s : services) {
            System.out.println(s.name + " - $" + s.price);
        }

        System.out.println("Total Add-On Cost: $" + calculateTotalCost(reservationId));
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - Hotel Booking System v7.1 =====");

        String reservationId = "R101";

        AddOnServiceManager manager = new AddOnServiceManager();

        Service breakfast = new Service("Breakfast", 20);
        Service spa = new Service("Spa Access", 50);
        Service airportPickup = new Service("Airport Pickup", 30);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, airportPickup);

        manager.displayServices(reservationId);
    }
}