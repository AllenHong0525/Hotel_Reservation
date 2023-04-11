package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this. price = price;
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public Double getRoomPrice() {
        return this.price;
    }

    public RoomType getRoomType() {
        return this.enumeration;
    }

    public boolean isFree() {
        return this.price.equals(0.0);
    }
    @Override
    public String toString() {
        return "\nRoom Number: " + this.roomNumber + "\nPrice: " + this.price + "\nRoom Type: " + this.enumeration;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        // this includes the null case
        if(!(obj instanceof Room)){
            return false;
        }

        final Room room = (Room) obj;
        return this.roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode(){
        return Objects.hash(roomNumber);
    }
}
