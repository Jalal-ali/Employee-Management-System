package models;

public class Employee extends User {
    public String department ;
    protected String role = "user" ;

    @Override
    public String toString() {
        return id + "," + name + "," + department +
                "," + role ;
    }
}
