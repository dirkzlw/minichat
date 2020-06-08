package com.dirk.chat.repository;

import com.dirk.chat.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-06-08 12:07
 * @description
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}
