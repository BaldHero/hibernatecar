package dao;

import entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtils;
import util.UserInput;
import util.UserPrompts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public static final int BRAND = 1;
    public static final int MODEL = 2;
    public static final int BODY_TYPE = 3;
    public static final int COLOR = 4;
    public static final int MILEAGE = 5;
    public static final int PRODUCTION_DATE = 6;
    public static final int SAVE_AND_EXIT = 0;

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
}
