package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    public CustomerService customerService = CustomerService.getInstance();
    public ReservationService reservationService = ReservationService.getInstance();

    private static AdminResource instance;
    private AdminResource(){};
    public static AdminResource getInstance(){
        if(instance == null){
            instance = new AdminResource();
        }
        return instance;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        rooms.forEach(reservationService::addRoom);
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.rooms.values();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.customers.values();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }

}
