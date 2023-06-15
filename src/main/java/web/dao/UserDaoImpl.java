package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
@Repository // зраннеие данных, отвечает за работу с БД
public class UserDaoImpl implements UserDao{
    // здесь логика для работы с БД

    @PersistenceContext()//аналог autowired
    private EntityManager entityManager;
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User showUser(int id) { //находим человека с id
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) { // добавляем новый объект класса User в БД
        entityManager.persist(user);
    }

    // добавляем метод обновления usera В БД
    @Override
    public void update(int id, User updatedUser) {
        User userToBeUbdated=showUser(id);// находим человека по id c помощью show и помещаем его в userToBeUbdated
        userToBeUbdated.setName(updatedUser.getName()); // обновляем у него имя
        userToBeUbdated.setEmail(updatedUser.getEmail()); // обновляем у него почту
        //entityManager.merge(userToBeUbdated);
    }

    // добавляем метод удаления usera В БД
    @Override
    public void delete(int id) {
        entityManager.remove(showUser(id)); // удаляет  по предикату
        // проходимся  по каждому человеку из списка, и если id авен пришедшемку id то user удаляется

    }

}


