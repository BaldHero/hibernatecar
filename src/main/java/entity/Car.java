package entity;

import javax.persistence.*;
import java.time.LocalDate;

// @Table(name = "SAMOCHÓD")
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    @Enumerated(EnumType.STRING)
    private CarBodyType carBodyType;

    @Column
    private LocalDate productionDate;

    @Column
    @Transient
    private int age;

    @Column
    private String color;

    @Column
    private long km;

    @Column
    private LocalDate modifiedDate;

    public Car(String brand, String model, CarBodyType carBodyType, LocalDate productionDate, String color, long km) {
        this.brand = brand;
        this.model = model;
        this.carBodyType = carBodyType;
        this.productionDate = productionDate;
        this.color = color;
        this.km = km;
        this.modifiedDate = LocalDate.now();
    }

    public Car() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCarBodyType(CarBodyType carBodyType) {
        this.carBodyType = carBodyType;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public int getAge() {
        return LocalDate.now().getYear() - productionDate.getYear();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setKm(long km) {
        this.km = km;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carBodyType=" + carBodyType +
                ", productionDate=" + productionDate +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", km=" + km +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
