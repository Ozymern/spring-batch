package com.ozymern.springbatch.processor;

import com.ozymern.springbatch.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;
import java.util.stream.Collectors;

public class UserProcessor implements ItemProcessor <List<User> ,List<User> >{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProcessor.class);

    @Override
    public List<User> process(List<User> userEntities) throws Exception {
        if(userEntities.isEmpty())
            LOGGER.info("users is empty, data processor canceled");


        return userEntities.stream().map(x->{
            x.setName(x.getName().toUpperCase());
            x.setSex(x.getSex().toUpperCase());
            x.setLastName(x.getLastName().toUpperCase());
            x.setEmail(x.getEmail().toUpperCase());
            return x;
        }).collect(Collectors.toList());
    }
}
