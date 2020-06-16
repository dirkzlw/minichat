package com.dirk.chat.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-06-12 11:06
 * @description
 */
@Entity
@Table(name = "t_friend_request")
@Getter
@Setter
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(length = 64, nullable = false)
    private String sendUserId;

    @Column(length = 64, nullable = false)
    private String acceptUserId;

    public FriendRequest() {
    }

    public FriendRequest(String sendUserId, String acceptUserId) {
        this.sendUserId = sendUserId;
        this.acceptUserId = acceptUserId;
    }
}
