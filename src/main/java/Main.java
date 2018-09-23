import entity.Car;
import entity.CarBodyType;
import service.CarService;
import util.HibernateUtils;
import util.UserInput;
import util.UserPrompts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
//        UserInput userInput = new UserInput();
//        UserPrompts userPrompts = new UserPrompts();
            CarService carService = new CarService();

            // Creating car objects
            Car car1 = new Car("Ford", "Focus", CarBodyType.HATCHBACK,
                    LocalDate.of(2005, 4, 12),
                    "Metallic Blue", 73000);
            Car car2 = new Car("Hyundai", "i20", CarBodyType.SEDAN,
                    LocalDate.of(2008, 2, 17),
                    "Jet Black", 120000);
            Car car3 = new Car("Skoda", "Octavia", CarBodyType.COMBI,
                    LocalDate.of(2001, 9, 23),
                    "Purple Pearl", 376000);
            Car car4 = new Car("Mercedes", "W124", CarBodyType.SEDAN,
                    LocalDate.of(1992, 1, 27),
                    "Black", 780000);
            Car car5 = new Car("Ford", "Mondeo", CarBodyType.COMBI,
                    LocalDate.of(2006, 11, 26),
                    "Silver", 230000);
            Car car6 = new Car("Kia", "Rio", CarBodyType.COMBI,
                    LocalDate.of(2005, 4, 12),
                    "Olive Green", 175000);
            Car car7 = new Car("Opel", "Astra", CarBodyType.HATCHBACK,
                    LocalDate.of(2005, 4, 15),
                    "Red", 227000);

            // Creating a car with no parameters, to be edited later with the editCar() method
//            Car car8 = new Car();

            // Adding cars to the database
            carService.addCar(car1);
            carService.addCar(car2);
            carService.addCar(car3);
            carService.addCar(car4);
            carService.addCar(car5);
            carService.addCar(car6);
            carService.addCar(car7);
//            carService.addCar(car8);

            // Editing and removing the entry which initially had no set parameters
//        carService.editCar(8);
//        carService.removeCar(car8);

            // Finding a car by its ID
            Car foundById = carService.listById(1);
            System.out.println("Car with ID #1:");
            System.out.println(foundById);
            System.out.println("====================================");

            // Calculating Car 1's age
            System.out.println("Car 1 age:");
            System.out.println(car1.getAge());
            System.out.println("====================================");

            // Printing all cars
            List<Car> carList;
            carList = carService.printAllCars();
            System.out.println("List of all cars:");
            System.out.println(carList);
            System.out.println("====================================");

            // Printing all cars of a specific brand
            List<Car> carsByBrand;
            carsByBrand = carService.listByBrand("Ford");
            System.out.println("List of all Ford cars:");
            System.out.println(carsByBrand);
            System.out.println("====================================");

            // Printing all cars with a specific body type
            List<Car> carsByBodyType;
            carsByBodyType = carService.listByCarBodyType(CarBodyType.SEDAN);
            System.out.println("List of all sedan cars:");
            System.out.println(carsByBodyType);
            System.out.println("====================================");

            // Printing all cars manufactured on a specific date
            List<Car> carsByProductionDate;
            carsByProductionDate = carService.listByProductionDate(LocalDate.of(2005, 4, 12));
            System.out.println("List of cars made on 2005-4-12:");
            System.out.println(carsByProductionDate);
            System.out.println("====================================");

            // Printing all cars manufactured on a specific date (expecting an exception - list is empty)
            List<Car> carsByProductionDateNone;
            carsByProductionDateNone = carService.listByProductionDate(LocalDate.of(2017, 3, 1));

            // Printing all cars sorted by brand
            List<Car> carsSortedByBrand;
            carsSortedByBrand = carService.printAllSortByBrand();
            System.out.println("List of all cars sorted by brand:");
            System.out.println(carsSortedByBrand);
            System.out.println("====================================");

            // Printing all cars sorted by production date
            List<Car> carsSortedByProductionDate;
            carsSortedByProductionDate = carService.printAllSortByProductionDate();
            System.out.println("List of all cars sorted by production date:");
            System.out.println(carsSortedByProductionDate);
            System.out.println("=====================================");

            // Printing the youngest car
            Car youngestCar = carService.printYoungestCar();
            System.out.println("Youngest car:");
            System.out.println(youngestCar);
            System.out.println("=====================================");

            // Printing the oldest car
            Car oldestCar = carService.printOldestCar();
            System.out.println("Oldest car:");
            System.out.println(oldestCar);
            System.out.println("=====================================");

            // Printing a car with the lowest mileage
            Car carWithLowestMileage = carService.printCarWithLowestMileage();
            System.out.println("Car with the lowest mileage:");
            System.out.println(carWithLowestMileage);
            System.out.println("=====================================");

            // Printing a car with the highest mileage
            Car carWithHighestMileage = carService.printCarWithHighestMileage();
            System.out.println("Car with the highest mileage:");
            System.out.println(carWithHighestMileage);
            System.out.println("=====================================");
        } finally {
            HibernateUtils.getSessionFactory().close();
        }
    }
}
