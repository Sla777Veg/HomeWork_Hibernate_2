import dao.CityDAO;
import dao.EmployeeDAO;
import impl.CityDaoImpl;
import impl.EmployeeDaoImpl;

import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        CityDAO cityDAO = new CityDaoImpl();
        int n = 5;
        City city = new City("London");
        List<Employee> employees = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            employees.add(
                    new Employee("name"+(i+1), "sur"+(i+1), "m", 30+i, city)
            );
        }
        city.setEmployees(employees);

        cityDAO.create(city);

        employeeDAO.findAll().forEach(System.out::println);
        //cityDAO.deleteById(city);
        //employeeDAO.findAll().forEach(System.out::println);
    }
}