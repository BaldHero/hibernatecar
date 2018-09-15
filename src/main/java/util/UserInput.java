package util;

import entity.CarBodyType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInput {
    private Scanner scanner;
    private UserPrompts userPrompts = new UserPrompts();

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public int getParameter() {
        userPrompts.askForParameter();
        scanner = new Scanner(System.in);
        int parameter = 0;

        try {
            parameter = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }

    public String getUserBrand() {
        scanner = new Scanner(System.in);
        System.out.println("Enter brand:");
        String brand = scanner.next();
        return brand;
    }

    public String getUserModel() {
        scanner = new Scanner(System.in);
        System.out.println("Enter model:");
        String model = scanner.nextLine();
        return model;
    }

    public CarBodyType getUserCarBody() {
        scanner = new Scanner(System.in);
        System.out.println("Enter body type:" +
                "sedan, combi, cabrio, hatchback");
        String input = scanner.nextLine();
        int carBody = checkCarBodyInput(input);
        CarBodyType carBodyType = identifyCarBody(carBody);
        return carBodyType;
    }

    public String getUserColor() {
        scanner = new Scanner(System.in);
        System.out.println("Enter color:");
        String color = scanner.nextLine();
        return color;
    }

    public long getUserMileage() {
        scanner = new Scanner(System.in);
        System.out.println("Enter mileage:");
        long mileage = scanner.nextLong();
        return mileage;
    }

    public LocalDate getUserProductionDate() {
        scanner = new Scanner(System.in);
        System.out.println("Enter date of manufacture: (yyyy-mm-dd format)");
        String dateOfManufacture = scanner.nextLine();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateOfManufacture, dateTimeFormatter);
        return localDate;
    }

    public int checkCarBodyInput(String input) {
        int choice = 0;
        try {
            if (input.equalsIgnoreCase("sedan")) {
                choice = 1;
            } else if (input.equalsIgnoreCase("combi")) {
                choice = 2;
            } else if (input.equalsIgnoreCase("cabrio")) {
                choice = 3;
            } else if (input.equalsIgnoreCase("hatchback")) {
                choice = 4;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return choice;
    }

    public CarBodyType identifyCarBody(int choice) {
        CarBodyType carBodyType = null;
        if (choice == 1) {
            carBodyType = CarBodyType.SEDAN;
        } else if (choice == 2) {
            carBodyType = CarBodyType.COMBI;
        } else if (choice == 3) {
            carBodyType = CarBodyType.CABRIO;
        } else if (choice == 4) {
            carBodyType = CarBodyType.HATCHBACK;
        }
        return carBodyType;
    }

    public void scClose() {
        scanner.close();
    }
}
