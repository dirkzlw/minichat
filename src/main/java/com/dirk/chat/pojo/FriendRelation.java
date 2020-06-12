package com.dirk.chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 好友关系表
 * @author Dirk
 * @date 2020-06-11 10:52
 * @description
 */
@Entity
@Table(name = "t_friend_relation")
@Getter
@Setter
public class FriendRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relationId;

    @Column(length = 64, unique = true, nullable = false)
    private String userId1;

    @Column(length = 64, unique = true, nullable = false)
    private String userId2;

}
