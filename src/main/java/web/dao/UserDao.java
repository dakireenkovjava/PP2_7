package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> index();

    User show(int n);

    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
}





/*public interface UserDao {
    List<User> getUser();

    List<User> getUserId(int n);
}*/