package com.codeup.Repositories;

import com.codeup.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by violet on 6/23/17.
 */
public interface UsersRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}

