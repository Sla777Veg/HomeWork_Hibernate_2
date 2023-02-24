package dao;

import model.City;
import model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee create(Employee employee);
    Optional<Employee> readById(int id);
    Employee updateAmountById(Employee employee);
    Optional<Employee> deleteById(Employee employee);
}
