package impl;

import dao.CityDAO;
import model.City;
import hibernate.HibernateSessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CityDaoImpl implements CityDAO {

    @Override
    public List<City> findAll() {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory().openSession()) {
            return session.createQuery("From City ", City.class).list();
        }
    }

    @Override
    public City create(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            // Создаем транзакцию и начинаем ее
            Transaction transaction = session.beginTransaction();
            Serializable createdId = session.save(city);
            City createdCity = session.get(City.class, createdId);
            transaction.commit();
            return createdCity;
        }
    }

    @Override
    public Optional<City> readById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(City.class, id));
        }
    }

    @Override
    public City updateAmountById(City city) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        City updated = entityManager.merge(city);
        entityTransaction.commit();
        return updated;
    }

    @Override
    public Optional<City> deleteById(City city) {
        Optional<City> cityOptional = readById(city.getId());
        if (cityOptional.isPresent()) {
            try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                // Для удаления объекта из таблицы нужно передать его в метод delete
                session.delete(cityOptional.get());
                transaction.commit();
                return cityOptional;
            }
        }
        return Optional.empty();
    }
}
