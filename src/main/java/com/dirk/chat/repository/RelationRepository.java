package com.dirk.chat.repository;

import com.dirk.chat.pojo.FriendRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-06-11 11:20
 * @description
 */
public interface RelationRepository extends JpaRepository<FriendRelation,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM t_friend_relation " +
            "WHERE (user_id1=?1 AND user_id2=?2) " +
            "OR (user_id1=?2 AND user_id2=?1)")
    FriendRelation findRelationByUserIds(String userId1, String userId2);

}
