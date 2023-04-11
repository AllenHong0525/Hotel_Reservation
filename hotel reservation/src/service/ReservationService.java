package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    // The string is room number
    public Map<String, IRoom> rooms = new HashMap<>();
    // This string is the guest name
    private Map<String, Collection<Reservation>> reservations = new HashMap<>();

    private static ReservationService instance;
    private ReservationService(){};
    public static ReservationService getInstance(){
        if(instance == null){
            instance = new ReservationService();
        }
        return instance;
    }

    public void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomNumber){
        return rooms.get(roomNumber);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerResrevations = reservations.get(customer.getEmail());
        if(customerResrevations == null) customerResrevations = new LinkedList<>();
        customerResrevations.add(reservation);
        reservations.put(customer.getEmail(), customerResrevations);
        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        return reservations.get(customer.getEmail());
    }

    public void printAllReservation(){
        Collection<Reservation> allSingleReservations = new LinkedList<Reservation>();

        for(Collection<Reservation> reservationsForOnePerson : reservations.values()){
            allSingleReservations.addAll(reservationsForOnePerson);
        }

        if(allSingleReservations.isEmpty()){
            System.out.println("No Reservation");
        }

        for(Reservation oneSingleReservation: allSingleReservations){
            System.out.println(oneSingleReservation + "\n");
        }
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<Reservation> allSingleReservations = new LinkedList<Reservation>();
        Set<IRoom> reservedRooms = new HashSet<IRoom>();
        List<IRoom> avaliableRooms = new ArrayList<IRoom>();

        for(Collection<Reservation> reservationsForOnePerson : reservations.values()){
            allSingleReservations.addAll(reservationsForOnePerson);
        }
        for(Reservation oneSingleReservation: allSingleReservations){
            if(checkInDate.before(oneSingleReservation.getCheckOutDate()) && checkOutDate.after(oneSingleReservation.getCheckInDate())){
                reservedRooms.add(oneSingleReservation.getRoom());
            }
        }
        for(IRoom singleAvaliableRoom : rooms.values()){
            if(!reservedRooms.contains(singleAvaliableRoom)){
                avaliableRooms.add(singleAvaliableRoom);
            }
        }

        return avaliableRooms;
    }

    public boolean findRoomsByNumber(String userInputNumber){
        Collection<Reservation> allSingleReservations = new LinkedList<Reservation>();
        Set<String> reservedRoomNumber = new HashSet<String>();

        for(Collection<Reservation> reservationsForOnePerson : reservations.values()){
            allSingleReservations.addAll(reservationsForOnePerson);
        }

        for(Reservation oneSingleReservation: allSingleReservations){
            reservedRoomNumber.add(oneSingleReservation.getRoom().getRoomNumber());
        }
        return !reservedRoomNumber.contains(userInputNumber);
    }
}
