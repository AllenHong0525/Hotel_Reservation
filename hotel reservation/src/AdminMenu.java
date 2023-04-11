import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import javax.lang.model.element.AnnotationMirror;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static AdminResource adminResource = AdminResource.getInstance();

    public static void adminMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean backToMain = false;
        while(!backToMain){
            printAdminMenu();
            String userInput = scanner.nextLine();
            if(userInput.length() == 1){
                switch (userInput.charAt(0)){
                    case '1':
                        seeAllCustomers();
                        break;
                    case '2':
                        seeAllRooms();
                        break;
                    case '3':
                        seeAllReservations();
                        break;
                    case '4':
                        addARoom();
                        break;
                    case '5':
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 5");
                }
            } else {
                System.out.println("Please enter a single digit");
            }
        }
    }

    private static void seeAllReservations(){
        adminResource.displayAllReservations();
    }
    private static void seeAllCustomers(){
        Collection<Customer> customers = adminResource.getAllCustomers();
        if(customers.size() == 0){
            System.out.println("No customer");
        } else {
            for(Customer oneCustomer : customers){
                System.out.println(oneCustomer);
            }
        }
    }

    private static void seeAllRooms(){
        Collection<IRoom> customers = adminResource.getAllRooms();
        if(customers.size() == 0){
            System.out.println("No room");
        } else {
            for(IRoom oneRoom : customers){
                System.out.println(oneRoom);
            }
        }
    }
    private static void addARoom(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter room number");

        Integer roomNumberForTest = 0;
        boolean validRoomNumber = false;
        while(!validRoomNumber){
            try{
                roomNumberForTest = Integer.parseInt(userInput.nextLine());
                validRoomNumber = true;
            } catch (NumberFormatException e){
                System.out.println("Invalid room number, please enter a digit");
                validRoomNumber = false;
            }
        }

        String roomNumber = roomNumberForTest.toString();

        System.out.println("Enter price per night");

        double roomPrice = 0.0;
        boolean validPrice = false;
        while(!validPrice){
            try{
                roomPrice = Double.parseDouble(userInput.nextLine());
                validPrice = true;
            } catch (NumberFormatException e){
                System.out.println("Invalid price, please enter a digit");
                validPrice = false;
            }
        }

        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
        String roomType = userInput.nextLine();
        Room room = null;
        if(roomType.equals("1")){
            room = new Room(roomNumber, roomPrice, RoomType.SINGLE);
        } else if (roomType.equals("2")){
            room = new Room(roomNumber, roomPrice, RoomType.DOUBLE);
        } else {
            System.out.println("Please enter 1 or 2");
        }
        List<IRoom> newRooms = new ArrayList<IRoom>();
        newRooms.add(room);
        adminResource.addRoom(newRooms);
    }

    public static void printAdminMenu(){
        System.out.println("--------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("--------------------------------------");
    }
}
