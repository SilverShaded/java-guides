package com.springinaction.tacocloud.Data;

import com.springinaction.tacocloud.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
}
