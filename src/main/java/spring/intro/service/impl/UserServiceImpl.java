package spring.intro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.intro.dao.UserDao;
import spring.intro.model.User;
import spring.intro.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public void add(User user) {
        userDao.add(user);
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
