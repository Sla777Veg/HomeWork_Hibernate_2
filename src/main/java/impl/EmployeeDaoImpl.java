package impl;

import dao.CityDAO;
import dao.EmployeeDAO;
import model.Employee;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.HibernateSessionFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EmployeeDaoImpl implements EmployeeDAO {

    @Override
    public List<Employee> findAll() {
        //List<Employee> employees = new ArrayList<>();
        try(Session session = HibernateSessionFactoryUtil
                .getSessionFactory().openSession()) {
            return session.createQuery("From Employee", Employee.class).list();
        }
    }


    @Override
    public Optional<Employee> readById(int id) {
        // С помощью конфиг-файла получаем сессию, открываем ее
        // и через метод get получаем объект
        // В параметре метода get нужно указать объект какого класса нам нужен
        // и его id
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Employee.class, id));
        }
    }

    @Override
    public Employee create(Employee employee) {

        // В ресурсах блока try создаем объект сессии с помощью нашего конфиг-файла
        // И открываем сессию
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            // Создаем транзакцию и начинаем ее
            Transaction transaction = session.beginTransaction();
            Serializable createdId = session.save(employee);
            Employee createdEmployee = session.get(Employee.class, createdId);
            transaction.commit();
            return createdEmployee;
        }
    }

    @Override
    public Employee updateAmountById(Employee employee) {
        if (employee.getCity() !=null ) {
            employee.setCity(null);
        }
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Employee updated = entityManager.merge(employee);
        entityTransaction.commit();
        return updated;
    }


    @Override
    public Optional<Employee> deleteById(Employee employee) {
        Optional<Employee> employeeOptional = readById(employee.getId());
        if (employeeOptional.isPresent()) {
            try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                // Для удаления объекта из таблицы нужно передать его в метод delete
                session.delete(employeeOptional.get());
                transaction.commit();
                return employeeOptional;
            }
        }
        return Optional.empty();
    }
}
