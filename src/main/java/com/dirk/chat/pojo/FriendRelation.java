package com.dirk.chat.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class FriendRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relationId;

    @Column(length = 64, nullable = false)
    private String userId1;

    @Column(length = 64, nullable = false)
    private String userId2;

    public FriendRelation() {
    }

    public FriendRelation(String userId1, String userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}
