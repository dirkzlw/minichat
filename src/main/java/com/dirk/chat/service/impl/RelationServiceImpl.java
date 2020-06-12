package com.dirk.chat.service.impl;

import com.dirk.chat.pojo.FriendRelation;
import com.dirk.chat.repository.RelationRepository;
import com.dirk.chat.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public boolean isFriend(String userId, String userId1) {

        FriendRelation relation = relationRepository.findRelationByUserIds(userId, userId);

        return relation == null ? true : false;
    }
}
