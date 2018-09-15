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

//        UserInput userInput = new UserInput();
//        UserPrompts userPrompts = new UserPrompts();
        CarService carService = new CarService();

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
        Car car5 = new Car();

        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);
        carService.addCar(car4);
        carService.addCar(car5);

//        carService.editCar(5);

//        carService.removeCar(car5);

//        Car foundById = carService.listById(1);
//        System.out.println(foundById);

        List<Car> carList;
        carList = carService.printAllCars();
        System.out.println(carList);

        HibernateUtils.getSessionFactory().close();
    }
}
