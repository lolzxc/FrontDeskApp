package org.FrontDeskApp;

import org.FrontDeskApp.Area.Area;
import org.FrontDeskApp.Area.AreaService;
import org.FrontDeskApp.Box.Box;
import org.FrontDeskApp.Box.BoxController;
import org.FrontDeskApp.Box.BoxService;
import org.FrontDeskApp.Customer.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner myObj = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        Main main = new Main();

        while(true) {
            System.out.print("""
                FrontDeskApp
                1. Create a new storage customer.
                2. Retrieve Box.
                3. Check for the remaining availability of the storage area.
                """);

            try {
                int choice = main.myObj.nextInt();
                main.myObj.nextLine();
                switch (choice) {
                    case 1 -> {
                        int generatedKey = main.createNewCustomer();
                        main.storeBox(generatedKey);
                    }
                    case 2 -> {
                        main.retrieveBox();
                    }
                    case 3 -> {
                        main.checkAvailabilityOfStorageArea();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                main.myObj.nextLine();
            }
        }
    }
    int createNewCustomer() throws SQLException {
        CustomerService customerService = new CustomerService();
        CustomerController customerController = new CustomerController(customerService);

        System.out.println("Creating a new customer...");

        System.out.print("Enter the First Name: ");
        String firstName = myObj.nextLine();
        while(customerController.inputValidation(firstName)) {
            System.out.print("Invalid Input. Please try again: ");
            firstName = myObj.nextLine();
        };

        System.out.print("Enter the Last Name: ");
        String lastName = myObj.nextLine();
        while(customerController.inputValidation(lastName)) {
            System.out.print("Invalid Input. Please try again: ");
            lastName = myObj.nextLine();
        };

        System.out.print("Enter the Phone Number: ");
        String phoneNumber = myObj.nextLine();
        while(!customerController.inputValidation(phoneNumber) && !phoneNumber.isEmpty()) {
            System.out.print("Invalid Input. Please try again: ");
            firstName = myObj.nextLine();
        };

        Customer customer = new Customer(firstName, lastName, phoneNumber);
        customerController.handleCustomerRegistration(customer);
        return customer.getId();
    }

    void checkAvailabilityOfStorageArea() throws SQLException {
        AreaService areaService = new AreaService();
        System.out.println("Size of area\tAvailable Capacity\tTotal Capacity");
        areaService.getAllArea().forEach(i -> {
            System.out.printf("\t%s\t\t\t\t %d\t\t\t\t %d\n", i.getSizeOfArea().toUpperCase(), i.getAvailableCapacity(), i.getTotalCapacity());
        });
    }

    void storeBox(int customerId) {
        while(true) {
            try {
                AreaService areaService = new AreaService();
                BoxService boxService = new BoxService();

                Area area;
                Area updatedArea;

                System.out.print("""
                    Select the size of the customer's package
                    1. Small
                    2. Medium
                    3. Large
                    4. Cancel
                    """);

                int choice = myObj.nextInt();
                myObj.nextLine();

                while (choice < 1 || choice > 4) {
                    System.out.print("Invalid input. Please try again: ");
                    choice = myObj.nextInt();
                    myObj.nextLine();
                }

                if (choice == 4) break;

                area = areaService.get(choice-1);

                if (area.getAvailableCapacity() == 0) {
                    System.out.println("Sorry, there is not enough space.");
                    break;
                };

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                String[] sizes = {"small", "medium", "large"};
                Box box = new Box(customerId, sizes[choice-1], formattedDate, null);
                boxService.insertBox(box);

                int newAvailableCapacity = area.getAvailableCapacity() - 1;
                int newUsedCapacity = area.getUsedCapacity() + 1;

                updatedArea = new Area(newAvailableCapacity, newUsedCapacity, area.getId());
                areaService.update(updatedArea);

                System.out.printf("The box has been stored. The reference id is %d.\n", box.getId());

                System.out.println("Do you want to add another box? Y/N");
                String ans = myObj.nextLine();

                if (!ans.equalsIgnoreCase("y")) break;
            } catch (Exception ex) {
                ex.printStackTrace();
                myObj.nextLine();
            }
        }
    }

    void retrieveBox() throws SQLException {
        BoxService boxService = new BoxService();
        List<String> box;
        System.out.println("Enter the id of the box you want to retrieve: ");
        int id = myObj.nextInt();
        myObj.nextLine();
        box = boxService.getBox(id);

        if (box.isEmpty()) {
            System.out.println("Sorry, there is no box with that id or that box is already retrieved.");
            return;
        }

        String firstName = boxService.getBox(id).get(0);
        String lastName = boxService.getBox(id).get(1);
        String boxId = boxService.getBox(id).get(2);


        System.out.printf("Do you want to retrieve the box of %s %s with the id of %s? Y/N ",
                firstName, lastName, boxId);

        String choice = myObj.nextLine();


        if (choice.equalsIgnoreCase("y")) {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);

            Box newBox = new Box(formattedDate, Integer.parseInt(boxId));
            boxService.updateBox(newBox);
            System.out.println("Successfully retrieved.");
        } else {
            System.out.println("Thank you for using.");
        }
    }
}