package service;

import dao.CarDAO;
import entity.Car;

import java.util.List;

public class CarService {

    CarDAO carDAO = new CarDAO();

    public void addCar(Car car) {
        carDAO.create(car);
    }

    public Car listById(long id) {
        Car car = carDAO.findById(id);
        return car;
    }

    public void editCar(long id) {
        carDAO.editCar(id);
    }

    public void removeCar(Car car) {
        carDAO.remove(car);
    }

    public List<Car> printAllCars() {
        List<Car> carList = carDAO.findAll();
        return carList;
    }
}
