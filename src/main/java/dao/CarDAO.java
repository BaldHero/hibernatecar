package dao;

import entity.Car;
import entity.CarBodyType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.UserInput;
import util.UserPrompts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CarDAO {

    private static final int BRAND = 1;
    private static final int MODEL = 2;
    private static final int BODY_TYPE = 3;
    private static final int COLOR = 4;
    private static final int MILEAGE = 5;
    private static final int PRODUCTION_DATE = 6;
    private static final int SAVE_AND_EXIT = 0;

    private static UserPrompts userPrompts = new UserPrompts();
    private static UserInput userInput = new UserInput();

    public void create(Car car) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            session.persist(car);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Car findById(long id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Car car = new Car();

        try {
            String query = "select c from Car c where id = :id";
            car = session.createQuery(query, Car.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }

    public List <Car> findByBrand(String brand) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Car> cars = new ArrayList<>();

        try {
            String query = "select c from Car c where brand = :brand";
            cars = session.createQuery(query, Car.class)
                    .setParameter("brand", brand)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cars;
    }

    public List<Car> findByBodyType(CarBodyType carBodyType) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Car> cars = new ArrayList<>();

        try {
            String query = "select c from Car c where carBodyType = :carBodyType";
            cars = session.createQuery(query, Car.class)
                    .setParameter("carBodyType", carBodyType)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cars;
    }

    public List<Car> findByProductionDate(LocalDate productionDate) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Car> cars = new ArrayList<>();

        try {
            String query = "select c from Car c where productionDate = :productionDate";
            cars = session.createQuery(query, Car.class)
                    .setParameter("productionDate", productionDate)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        if (cars.isEmpty()) {
            try {
                throw new Exception("There are no results that match your query.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cars;
    }

    public void editCar(long id) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        userPrompts.askForParameter();
        int parameter;
        Car car;
//        Car car = findById(id);

        try {
            car = session.find(Car.class, id);
            while ((parameter = userInput.getParameter()) != SAVE_AND_EXIT) {
                if (parameter == BRAND) {
                    String brand = userInput.getUserBrand();
                    System.out.println("you have entered " + brand);
                    car.setBrand(brand);
                } else if (parameter == MODEL) {
                    car.setModel(userInput.getUserModel());
                    car.setModifiedDate(LocalDate.now());
                } else if (parameter == BODY_TYPE) {
                    car.setCarBodyType(userInput.getUserCarBody());
                    car.setModifiedDate(LocalDate.now());
                } else if (parameter == COLOR) {
                    car.setColor(userInput.getUserColor());
                    car.setModifiedDate(LocalDate.now());
                } else if (parameter == MILEAGE) {
                    car.setKm(userInput.getUserMileage());
                    car.setModifiedDate(LocalDate.now());
                } else if (parameter == PRODUCTION_DATE) {
                    car.setProductionDate(userInput.getUserProductionDate());
                    car.setModifiedDate(LocalDate.now());
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void remove(Car car) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction  transaction = session.beginTransaction();

        try {
            session.remove(car);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Car> findAll() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Car> cars = new ArrayList<>();

        try {
            String query = "Select c from Car c";
            cars = session.createQuery(query, Car.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cars;
    }

    public List<Car> findAllSortByBrand() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Car> cars = new ArrayList<>();

        try {
            String query = "Select c from Car c order by brand";
            cars = session.createQuery(query, Car.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cars;
    }

    public List<Car> findAllSortByProductionDate() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Car> cars = new ArrayList<>();

        try {
            String query = "Select c from Car c order by productionDate";
            cars = session.createQuery(query, Car.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cars;
    }

    public Car findYoungestCar() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Car car = new Car();

        try {
            String query = "Select c from Car c order by productionDate DESC";
            car = session.createQuery(query, Car.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }

    public Car findOldestCar() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Car car = new Car();

        try {
            String query = "Select c from Car c order by productionDate";
            car = session.createQuery(query, Car.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }

    public Car findCarWithLowestMileage() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Car car = new Car();

        try {
            String query = "Select c from Car c order by km";
            car = session.createQuery(query, Car.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }

    public Car findCarWithHighestMileage() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Car car = new Car();

        try {
            String query = "Select c from Car c order by km DESC";
            car = session.createQuery(query, Car.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }
}
