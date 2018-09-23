package service;

import dao.CarDAO;
import entity.Car;
import entity.CarBodyType;

import java.time.LocalDate;
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

    public List<Car> listByBrand(String brand) {
        List<Car> cars = carDAO.findByBrand(brand);
        return cars;
    }

    public List<Car> listByCarBodyType(CarBodyType carBodyType) {
        List<Car> cars = carDAO.findByBodyType(carBodyType);
        return cars;
    }

    public List<Car> listByProductionDate(LocalDate date) {
        List<Car> cars = carDAO.findByProductionDate(date);
        return cars;
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

    public List<Car> printAllSortByBrand() {
        List<Car> carList = carDAO.findAllSortByBrand();
        return carList;
    }

    public List<Car> printAllSortByProductionDate() {
        List<Car> carList = carDAO.findAllSortByProductionDate();
        return carList;
    }

    public Car printYoungestCar() {
        Car car = carDAO.findYoungestCar();
        return car;
    }

    public Car printOldestCar() {
        Car car = carDAO.findOldestCar();
        return car;
    }

    public Car printCarWithLowestMileage() {
        Car car = carDAO.findCarWithLowestMileage();
        return car;
    }

    public Car printCarWithHighestMileage() {
        Car car = carDAO.findCarWithHighestMileage();
        return car;
    }
}
