import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        AdminResource admin = AdminResource.getInstance();
        HotelResource hotel = HotelResource.getInstance();
//        LocalDate checkIn = LocalDate.of(2000,5,25);

        // we first add three rooms
        // yes, as a admin you can only add a list of rooms, even if you only
        // want to add one room you will still need to add the rooms as a list
        List<IRoom> newRooms = new ArrayList<IRoom>();
        newRooms.add(new Room("001", 100.0, RoomType.SINGLE));
        newRooms.add(new Room("002", 100.0, RoomType.SINGLE));
        newRooms.add(new Room("003", 200.0, RoomType.DOUBLE));
        admin.addRoom(newRooms);
        hotel.createACustomer("allenhonggod@gmail.com", "Allen", "Hong");
//        System.out.println(hotel.getCustomer("allenhonggod@gmail.com"));
//        System.out.println(hotel.getRoom("001"));
        hotel.bookARoom("allenhonggod@gmail.com", newRooms.get(1), new Date(2000, 1, 21), new Date(2000, 2, 22));
//        System.out.println(hotel.getCustomerReservations("allenhonggod@gmail.com"));
        // hotel.findARoom returns a list and because println will print all the element in the list and each
        // list element is overridden by toString(), that is why you are able to see the messages.
        System.out.println(hotel.findARoom(new Date(2000, 1, 21), new Date(2000, 2, 22)));

//        List<Integer> test = new ArrayList<Integer>();
//        test.add(3);
//        test.add(2);
//        System.out.println(test);

        ///////// the tests for hotel guest is good, now we test admin
//        System.out.println(admin.getCustomer("allenhonggod@gmail.com"));
//        System.out.println(admin.getAllRooms());
//        System.out.println(admin.getAllCustomers());
        admin.displayAllReservations();
        // the test for admin works perfectly








    }
}
