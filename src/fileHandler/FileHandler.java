package fileHandler;
import models.Employee;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
//   x --------- Employees CRUD operations started ------------------ x

    //    add to file
    public void saveEmployee(Employee emp){
        try{
            FileWriter writer = new FileWriter("src/data/employees.txt", true);
            writer.write(emp.toString() + "\n");
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    //   Read from file
    public void displayEmployees(){
        try{
            int count = 0 ;
            FileReader reader = new FileReader("src/data/employees.txt");
            Scanner sc = new Scanner(reader);

            while(sc.hasNextLine()){
                String fileData = sc.nextLine();
                count++ ;
                System.out.println(count + ". " + fileData);
            }
            sc.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    //    update
    public void update(int targetId, String newName, String newDep){
        try{
            FileReader reader = new FileReader("src/data/employees.txt");
            Scanner sc = new Scanner(reader);
            List<String> lines = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(","); // data = [10,"jalal",IT]
                int id = Integer.parseInt(data[0]);
                if (id == targetId) {
                    data[1] = newName; //  data = [10,"umar",IT]
                    data[2] = newDep;
                    line = String.join(",", data); // line = 10,umar,HR
                }
                lines.add(line + "\n");
            }
            sc.close();
//            write into file
            FileWriter writer = new FileWriter("src/data/employees.txt");
            for(int i = 0 ; i < lines.size(); i++){
                String line = lines.get(i);
                writer.write(line);
//                System.out.println("Line : " + line);

            }
            writer.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }  //    delete
    public void delete(int targetId){
        try{
            FileReader reader = new FileReader("src/data/employees.txt");
            Scanner sc = new Scanner(reader);
            List<String> lines = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(","); // data = [10,"jalal",IT]
                int id = Integer.parseInt(data[0]);
                if (id != targetId) {
                    lines.add(line + "\n");
                }
                else{
                    System.out.println("Employee has been deleted!, " + "Name: " + data[1]);
                }
            }
            sc.close();
//            write into file
            FileWriter writer = new FileWriter("src/data/employees.txt");
            for(int i = 0 ; i < lines.size(); i++){
                String line = lines.get(i);
                writer.write(line);
            }
            writer.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
//    x --------- Employees CRUD operations ended ------------------ x

//    x --------- Auth operations started ------------------ x
//    register
    public boolean register(String user, String pass){
        try{
            FileReader reader = new FileReader("src/data/users.txt");
            Scanner sc = new Scanner(reader);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                String userName = data[0];
                if (userName.equals(user)) {
                    System.out.println("User " + user + " already exist.");
                    return false ;
                }
            }
            FileWriter writer = new FileWriter("src/data/users.txt", true);
            writer.write(user + "," + pass + "\n");
            writer.close();
            System.out.println("Registered Successfully!");
            return true ;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false ;
        }

    }
//    login
    public boolean login(String reqUser, String reqPass){
        try{
            FileReader reader = new FileReader("src/data/users.txt");
            Scanner sc = new Scanner(reader);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                String userName = data[0];
                String pass = data[1];
                if(userName.equals(reqUser) && reqPass.equals(pass)){
                    System.out.println("Logged in Successfully!");
                    return true;
                }
            }
            return false ;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false ;

        }

    }


}
