package com.springboot.learnspringboot.socialmedia;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserVersionsDaoService {
    //JPA/Hibernate > Database
    private static List<UserV1> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new UserV1(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new UserV1(++usersCount,"Eve", LocalDate.now().minusYears(20)));
        users.add(new UserV1(++usersCount,"Jim", LocalDate.now().minusYears(15)));
    }

    public List<UserV1> findAll(){
        return users;
    }

    public Optional<UserV1> findUser(int id)
    {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public UserV1 createUser(UserV1 user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id)
    {
        users.removeIf(user -> user.getId().equals(id));
    }
}
