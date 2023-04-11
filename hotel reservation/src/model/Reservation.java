package model;

import java.util.Date;

public class Reservation {
    public Customer customer;
    public IRoom room;
    public Date checkInDate;
    public Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer = customer;
        this.room = room;
    }

    public IRoom getRoom(){
        return this.room;
    }

    public Date getCheckInDate(){
        return this.checkInDate;
    }

    public Date getCheckOutDate(){
        return this.checkOutDate;
    }

    @Override
    public String toString(){
        return "Name: " + this.customer.toString() + "\nCheck In: " + this.checkInDate + "\nCheck Out " + this.checkOutDate;
    }
}
