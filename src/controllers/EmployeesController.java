package controllers;
import fileHandler.FileHandler;
import models.Employee;
import java.util.Scanner;

public class EmployeesController {
    FileHandler fileHandler = new FileHandler();
    Scanner input = new Scanner(System.in);

//    add employee
    public void addEmployee(){
        try{

            Employee emp = new Employee();
            System.out.print("Enter Employee's id: ");
            emp.id = input.nextInt();
            input.nextLine();
            System.out.print("Enter Employee's name: ");
            emp.name = input.nextLine();
            System.out.print("Enter Department: ");
            emp.department = input.nextLine();
            System.out.println(emp);
            fileHandler.saveEmployee(emp);
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Something went wrong. Try again!");
        }
    }

//    show employees
    public void showEmployees(){
        fileHandler.displayEmployees();
    }

//    update
    public void updateEmployee(){
        try{
            System.out.print("Enter id: ");
            int id = input.nextInt();
            input.nextLine();
            System.out.print("Enter new name: ");
            String newName = input.nextLine();
            System.out.print("Enter new department: ");
            String newDep = input.nextLine();
            fileHandler.update(id,newName,newDep);
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Something went wrong. Try again!");
        }
    }

//    delete
    public void deleteEmployee() {
        try {
            System.out.print("Enter id: ");
            int id = input.nextInt();
            fileHandler.delete(id);
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getLocalizedMessage());
            System.out.println("Something went wrong. Try again!");
        }
    }
}
