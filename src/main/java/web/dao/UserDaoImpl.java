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
@Repository //создаст бин класс и внедрит в контроллер
public class UserDaoImpl implements UserDao{
    // здесь логика для работы с БД

    @PersistenceContext()
    private EntityManager entityManager;
    @Override
    @SuppressWarnings("unchecked")
    public List<User> index() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User show(int id) { //находим человека с id

        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) { // добавляем новый объект класса User в БД
        entityManager.persist(user);
    }

    // добавляем метод обновления usera В БД
    @Override
    public void update(int id, User updatedUser) {
        User userToBeUbdated=show(id);// находим человека по id c помощью show и помещаем его в userToBeUbdated
        userToBeUbdated.setName(updatedUser.getName()); // обновляем у него имя
        userToBeUbdated.setEmail(updatedUser.getEmail()); // обновляем у него почту
        entityManager.merge(userToBeUbdated);
    }

    // добавляем метод удаления usera В БД
    @Override
    public void delete(int id) {
        entityManager.remove(show(id)); // удаляет  по предикату
        // проходимся  по каждому человеку из списка, и если id авен пришедшемку id то user удаляется

    }

}




/*@Repository //создаст бин класс и внедрит в контроллер
public class UserDaoImpl implements UserDao{
    // здесь логика для работы с БД

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @SuppressWarnings("unchecked")
    public List<User> index() {
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User show(int id) { //находим человека с id
        return sessionFactory.getCurrentSession().get(User.class,id);
    }

    @Override
    public void save(User user) { // добавляем новый объект класса User в БД
        sessionFactory.getCurrentSession().save(user);
    }

    // добавляем метод обновления usera В БД
    @Override
    public void update(int id, User updatedUser) {
        User userToBeUbdated=show(id);// находим человека по id c помощью show и помещаем его в userToBeUbdated
        userToBeUbdated.setName(updatedUser.getName()); // обновляем у него имя
        userToBeUbdated.setEmail(updatedUser.getEmail()); // обновляем у него почту
    }

    // добавляем метод удаления usera В БД
    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(show(id)); // удаляет  по предикату
        // проходимся  по каждому человеку из списка, и если id авен пришедшемку id то user удаляется

    }

}*/



/*
верный вариант с листом
@Repository //создаст бин класс и внедрит в контроллер
public class UserDaoImpl implements UserDao{
// здесь логика для работы с БД
    private static int USER_COUNT;

    private  List<User> users;
    {
        users=new ArrayList<>();
        users.add(new User(++USER_COUNT,"Dmitriy","Dmitriy@mail.ru"));
        users.add(new User(++USER_COUNT,"Vasiliy","Vasiliy@mail.ru"));
        users.add(new User(++USER_COUNT,"Mariya","Mariya@mail.ru"));
        users.add(new User(++USER_COUNT,"Valentina","Valentina@mail.ru"));
        users.add(new User(++USER_COUNT,"Ekaterina","Ekaterina@mail.ru"));

    }
    @Override
    public List<User> index() {
        return users;
    }

    @Override
    public User show(int id) { //находим человека с id
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void save(User user) { // добавляем новый объект класса User в БД
        user.setId(++USER_COUNT); // назначение id динамически
        users.add(user);
    }

    // добавляем метод обновления usera В БД
    @Override
    public void update(int id, User updatedUser) {
        User userToBeUbdated=show(id);// находим человека по id c помощью show и помещаем его в userToBeUbdated
        userToBeUbdated.setName(updatedUser.getName()); // обновляем у него имя
        userToBeUbdated.setEmail(updatedUser.getEmail()); // обновляем у него почту
    }

    // добавляем метод удаления usera В БД
    @Override
    public void delete(int id) {
        users.removeIf(user -> user.getId()==id); // удаляет  по предикату
        // проходимся  по каждому человеку из списка, и если id авен пришедшемку id то user удаляется

    }

}
*/