package com.springboot.learnspringboot.socialmedia;

import com.springboot.learnspringboot.socialmedia.User;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {
    //JPA/Hibernate > Database
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Eve", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount,"Jim", LocalDate.now().minusYears(15)));
    }

    public List<User> findAll(){
        return users;
    }

    public Optional<User> findUser(int id)
    {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public User createUser(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id)
    {
        users.removeIf(user -> user.getId().equals(id));
    }
}
