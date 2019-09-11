package com.ozymern.springbatch.util;

import com.ozymern.springbatch.entities.User;
import com.ozymern.springbatch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void postConstruct() {
        User user1 = new User();
        user1.setEmail("alejandro.fvaras@gmail.com");
        user1.setLastName("Fernandez");
        user1.setName("Alejandro");
        user1.setSex("Masculino");
        user1.setAge(31);
        userRepository.save(user1);

        User user2 = new User();

        user2.setEmail("maria.varas@gmail.com");
        user2.setLastName("Varas");
        user2.setName("Maria");
        user2.setSex("Femenino");
        user2.setAge(60);
        userRepository.save(user2);
    }
}
