package com.dirk.chat.repository;

import com.dirk.chat.pojo.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-06-08 12:07
 * @description
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM t_user AS u,t_friend_request AS r " +
                    "WHERE r.accept_user_id=?1 " +
                    "AND u.user_id=r.send_user_id")
    List<User> findUserByAcceptId(String userId);
}
