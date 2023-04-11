package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class HotelResource {
    public CustomerService customerService = CustomerService.getInstance();
    public ReservationService reservationService = ReservationService.getInstance();

    private static HotelResource instance;
    private HotelResource(){};
    public static HotelResource getInstance(){
        if(instance == null){
            instance = new HotelResource();
        }
        return instance;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
    customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail){
        // this getCustomer is from HotelResource
        Customer customer = getCustomer(customerEmail);
        if(customer == null){
            return Collections.emptyList();
        }

        // this getCustomerReservations is from reservationService
        // HotelResource.getCustomer -> customerService.getCustomer -> customer.get() -> finally got the customer
        // reservationService.getCustomersReservation(The customer we got)
        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }

    public boolean checkRoomAvailabilityByNumber(String number){
        return reservationService.findRoomsByNumber(number);
    }
}
