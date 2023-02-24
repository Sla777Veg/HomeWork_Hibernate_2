package model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String name;
    @Column(name = "last_name", nullable = false, length = 50)
    private String surname;
    @Column(name = "gender", nullable = false, length = 50)
    private String gender;
    @Column(name = "age")
    private int age;
    @ManyToOne
    @JoinColumn(name = "City_id")
    private City city;

    public Employee() {
    }

    public Employee(String name, String surname, String gender, int age, City city) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public Employee(int id, String name, String surname, String gender, int age, City city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", city=" + city +
                '}';
    }
}