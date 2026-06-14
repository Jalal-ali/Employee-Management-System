package controllers;
import fileHandler.FileHandler;

import java.util.Scanner;

public class AuthController {
    FileHandler fileHandler = new FileHandler();
    Scanner input = new Scanner(System.in);
//    public void signup(){
//        try {
//            System.out.print("Enter username: ");
//            String user = input.nextLine();
//            System.out.print("Enter password: ");
//            String pass = input.nextLine();
//            fileHandler.register(user,pass);
//        }
//        catch(Exception ex){
//            System.out.println(ex.getMessage());
//            System.out.println("Something went wrong, Try again!");
//        }
//    }
    public void signup(String user, String pass){
        try {
            fileHandler.register(user,pass);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Something went wrong, Try again!");
        }
    }
}
