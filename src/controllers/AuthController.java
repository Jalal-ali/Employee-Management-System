package controllers;

import java.util.Scanner;

public class AuthController {
    Scanner input = new Scanner(System.in);
    public void signup(){
        System.out.print("Enter username: ");
        String user = input.nextLine();
        input.nextLine();
        System.out.print("Enter password: ");
        String pass = input.nextLine();
    }
}
