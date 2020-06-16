package com.dirk.chat.service.impl;

import com.dirk.chat.pojo.FriendRequest;
import com.dirk.chat.repository.FrequestRespository;
import com.dirk.chat.service.FrequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dirk
 * @date 2020-06-12 11:10
 * @description
 */
@Service
public class FrequestServiceImpl implements FrequestService {

    @Autowired
    private FrequestRespository frequestRespository;

    /**
     * 添加好友请求
     * @param userId
     * @param friendUserId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addFriendRequest(String userId, String friendUserId) {

        FriendRequest friendRequest = frequestRespository.findBySendUserIdAndAcceptUserId(userId, friendUserId);

        if (friendRequest == null) {
            friendRequest = new FriendRequest(userId, friendUserId);
            frequestRespository.save(friendRequest);
        }

    }

    /**
     * 删除好友请求
     * @param userId
     * @param friendId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteFriendRequest(String userId, String friendId) {

        frequestRespository.deleteFriendRequestBySendUserIdAndAcceptUserId(friendId, userId);

    }
}
