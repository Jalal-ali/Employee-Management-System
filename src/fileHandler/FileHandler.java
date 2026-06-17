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
    public boolean saveEmployee(Employee emp){
        try{
            File file = new File("src/data/employees.txt");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            }
            else{
                System.out.println("File already exist.");

            }
            FileReader reader = new FileReader("src/data/employees.txt");
            Scanner sc =new Scanner(reader);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                if(id == emp.id){
                    return false ;
                }
            }
            FileWriter writer = new FileWriter("src/data/employees.txt", true);
            writer.write(emp.toString() + "\n");
            writer.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    //   Count emps from file
    public String employeesCount(){
        try{
            if(new File("src/data/employees.txt").exists()){
                int count = 0 ;
                FileReader reader = new FileReader("src/data/employees.txt");
                Scanner sc = new Scanner(reader);

                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    if(line.length() > 0){
                        count++ ;
                    }
                }
                sc.close();
                return String.valueOf(count);
            }
            else{
                System.out.println("No employees found!");
                return "0" ;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null ;
    }
    public Object[][] displayEmployees() {
        try {
            if(new File("src/data/employees.txt").exists()){
                List<Object[]> rows = new ArrayList<>();
                FileReader reader = new FileReader("src/data/employees.txt");
                Scanner sc = new Scanner(reader);
                while (sc.hasNextLine()) {
                    String[] data = sc.nextLine().split(",");
                    rows.add(new Object[]{
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3]
                    });
                }
                return rows.toArray(new Object[0][]);
            }
            else{
                System.out.println("File not Found!");
                return new Object[0][0];
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Object[0][0];
    }
//    search single employee
    public String searchEmployee(int targetedId){
        try{
            FileReader reader = new FileReader("src/data/employees.txt");
            Scanner sc = new Scanner(reader);

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                if(id == targetedId){
                    return line ;
                }
            }
            sc.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
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
            File file = new File("src/data/users.txt");
            if(!file.exists()){
                file.createNewFile();
                System.out.println("File Created: " + file.getName());
            }
//            FileReader reader = new FileReader("src/data/users.txt");
            FileReader reader = new FileReader(file);
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

//    forgot pass
public String updatePass(String username, String newPass) {
    try {
        FileReader reader = new FileReader("src/data/users.txt");
        Scanner sc = new Scanner(reader);
        List<String> lines = new ArrayList<>();
        boolean success = false ;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] data = line.split(",");
//            if matches
            if (data[0].equals(username)) {
                data[1] = newPass;
                line = String.join("," , data);
                success = true ;
            }
            lines.add(line + "\n");
        }
        reader.close();
        if(!success){
            return "Invalid input, Try again!" ;
        }
        else{
//        write to file
            FileWriter writer = new FileWriter("src/data/users.txt");
            for(int i = 0 ; i < lines.size(); i++){
                String line = lines.get(i);
                writer.write(line);
            }
            writer.close();
            return "Credentials updated ";
        }


    } catch (Exception e) {
        e.printStackTrace();
        return e.getMessage() ;
    }
}


}
