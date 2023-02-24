package dao;

import model.City;
import model.Employee;

import java.util.List;
import java.util.Optional;

public interface CityDAO {
    List<City> findAll();
    City create(City city);
    Optional<City> readById(int id);
    City updateAmountById(City city);
    Optional<City> deleteById(City city);
}
