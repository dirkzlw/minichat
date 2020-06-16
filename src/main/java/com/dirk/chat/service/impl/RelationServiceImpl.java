package com.dirk.chat.service.impl;

import com.dirk.chat.pojo.FriendRelation;
import com.dirk.chat.repository.RelationRepository;
import com.dirk.chat.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dirk
 * @date 2020-06-11 11:20
 * @description
 */
@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationRepository relationRepository;

    /**
     * @param userId
     * @param userId1
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isFriend(String userId, String userId1) {

        FriendRelation relation = relationRepository.findRelationByUserIds(userId, userId1);

        return relation == null ? false : true;
    }

    /**
     * 保存好友关系
     * @param userId
     * @param friendId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveFriendRelation(String userId, String friendId) {

        FriendRelation relation = relationRepository.findRelationByUserIds(userId, friendId);
        if(relation == null){
            relation = new FriendRelation(userId, friendId);
            relationRepository.save(relation);
        }
    }
}
