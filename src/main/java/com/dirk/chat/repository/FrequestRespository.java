package com.dirk.chat.repository;

import com.dirk.chat.pojo.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-06-12 11:09
 * @description
 */
public interface FrequestRespository extends JpaRepository<FriendRequest,Integer> {

    FriendRequest findBySendUserIdAndAcceptUserId(String sendUserId, String acceptUserId);

    void deleteFriendRequestBySendUserIdAndAcceptUserId(String sendUserId, String acceptUserId);

}
