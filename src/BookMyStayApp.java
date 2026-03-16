

public class BookMyStayApp {



        public static void main(String[] args) {

            System.out.println("===== Book My Stay - Hotel Booking System v2.1 =====");

            Room single = new SingleRoom();
            Room doubleRoom = new DoubleRoom();
            Room suite = new SuiteRoom();

            int singleAvailability = 10;
            int doubleAvailability = 5;
            int suiteAvailability = 2;

            System.out.println("\n--- Single Room ---");
            single.displayRoomDetails();
            System.out.println("Available Rooms: " + singleAvailability);

            System.out.println("\n--- Double Room ---");
            doubleRoom.displayRoomDetails();
            System.out.println("Available Rooms: " + doubleAvailability);

            System.out.println("\n--- Suite Room ---");
            suite.displayRoomDetails();
            System.out.println("Available Rooms: " + suiteAvailability);
        }
    }



}
