import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static HotelResource hotelResource = HotelResource.getInstance();

    public static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            printMainMenu();
            String userInput = scanner.nextLine();
            if(userInput.length() == 1){
                switch (userInput.charAt(0)){
                    case '1':
                        findAndReserveARoom();
                        break;
                    case '2':
                        seeMyReservations();;
                        break;
                    case '3':
                        createAnAccount();
                        break;
                    case '4':
                        AdminMenu.adminMenu();
                        break;
                    case '5':
                        System.out.println("Thank you for using our application");
                        System.out.println("GoodBye");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 5");
                }
            } else {
                System.out.println("Please enter a single digit");
            }
        }
    }

    private static void findAndReserveARoom() {
        System.out.println("Enter CheckIn Date mm/dd/yyyy");
        Scanner scanner = new Scanner(System.in);
        String userCheckInDateInput = scanner.nextLine();
        Date checkInDate = null;
        try{
            checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(userCheckInDateInput);
        } catch (ParseException e){
            System.out.println("Invalid Check In Date");
            findAndReserveARoom();
        }

        System.out.println("Enter CheckOut Date mm/dd/yyyy");
        String userCheckOutDateInput = scanner.nextLine();
        Date checkOutDate = null;
        try{
            checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(userCheckOutDateInput);
        } catch (ParseException e){
            System.out.println("Invalid Check Out Date");
            findAndReserveARoom();
        }

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if(availableRooms.isEmpty()){
            System.out.println("No rooms are available on the date you chose");
            System.out.println("Searching for alternative rooms");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkInDate);
            calendar.add(Calendar.DATE, 4);
            Date alternativeCheckInDate = calendar.getTime();
            calendar.setTime(checkOutDate);
            calendar.add(Calendar.DATE, 4);
            Date alternativeCheckOutDate = calendar.getTime();
            Collection<IRoom> newAvailableRooms = hotelResource.findARoom(alternativeCheckInDate, alternativeCheckOutDate);
            if(newAvailableRooms.isEmpty()){
                System.out.println("Search is completed, still not room available");
                mainMenu();
            }
        }
        for(IRoom singleRoom : availableRooms){
            System.out.println(singleRoom);
        }

        System.out.println("would you like to book a room y/n?");
        String BookOrNo = scanner.nextLine();
         if(BookOrNo.equals("y")){
            System.out.println("Do you have an account with us y/n?");
            String accountOrNo = scanner.nextLine();
            if(accountOrNo.equals("n")){
                System.out.println("You need an account to book a room");
            } else {
                System.out.println("Enter your email");
                String userEmail = scanner.nextLine();

                if(hotelResource.getCustomer(userEmail) == null){
                    System.out.println("Email not found, please create an account");
                } else {
                    System.out.println("what room number would you like to reserve?");
                    String userInputRoom = scanner.nextLine();
                    // the first half tests if the room exists or not
                    // if the room is created by admin then we need to test if the room already exists
                    // in some other guest's reservation
                    if(hotelResource.getRoom(userInputRoom) == null && hotelResource.checkRoomAvailabilityByNumber(userInputRoom)){
                        System.out.println("Room not found");
                    } else {
                        IRoom room = hotelResource.getRoom(userInputRoom);
                        Reservation reservation = hotelResource.bookARoom(userEmail, room, checkInDate, checkOutDate);
                        System.out.println(reservation);
                    }
                }
            }
        } else {
             System.out.println("Invalid Input please enter y or n");
         }
    }

    private static void seeMyReservations(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email");
        String userInputEmail = scanner.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomerReservations(userInputEmail);
        for(Reservation oneReservation : reservations){
            System.out.println(oneReservation);
        }
    }

    private static void createAnAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Email");
        String userInputEmail = scanner.nextLine();

        System.out.println("First Name");
        String userFirstName = scanner.nextLine();

        System.out.println("Last Name");
        String userLastName = scanner.nextLine();

        try {
            hotelResource.createACustomer(userInputEmail, userFirstName, userLastName);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
            createAnAccount();
        }
    }
    public static void printMainMenu(){
        System.out.println("--------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
