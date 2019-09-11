package com.ozymern.springbatch.reader;

import com.ozymern.springbatch.entities.User;

import com.ozymern.springbatch.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserReader implements ItemReader<List<User>> {

    boolean read;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserReader.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (read)
            return null;
        List<User> users= userRepository.findAll();

        if(users.isEmpty()){
            LOGGER.info("users is empty, no reading data was found");
        }

        read = true;

        return users;
    }
}
