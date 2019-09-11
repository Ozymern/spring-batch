package com.ozymern.springbatch.repositories;

import com.ozymern.springbatch.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
