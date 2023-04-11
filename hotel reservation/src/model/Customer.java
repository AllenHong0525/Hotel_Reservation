package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Pattern pattern = Pattern.compile("^(.+)@(.+).(.+)$");
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString(){
        return "FirstName: " + this.firstName + "\nLastName: " + this.lastName + "\nEmail: " + this.email;
    }
}
