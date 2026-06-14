import controllers.AuthController;
import controllers.EmployeesController;

void main(String[] args) {
  EmployeesController e = new EmployeesController();
  AuthController auth = new AuthController();
  Scanner sc = new Scanner(System.in);

  while (true) {
    System.out.println("\n1. Add an Employee");
    System.out.println("2. Show all Employees");
    System.out.println("3. Update an Employee");
    System.out.println("4. Delete an Employee");
    System.out.println("5. Register");
    System.out.println("5. Exit");
    System.out.print("Choice (1-5): ");
    int choice = sc.nextInt();

    switch (choice) {
      case 1 -> e.addEmployee();
      case 2 -> e.showEmployees();
      case 3 -> e.updateEmployee();
      case 4 -> e.deleteEmployee();
//      case 5 -> auth.signup();
//      case 5 -> System.exit(0);
      default -> System.out.println("Invalid");
    }
  }

}