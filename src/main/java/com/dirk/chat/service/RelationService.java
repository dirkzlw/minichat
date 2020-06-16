package com.dirk.chat.service;

/**
 * @author Dirk
 * @date 2020-06-11 11:20
 * @description
 */
public interface RelationService {
    boolean isFriend(String userId, String userId1);

    void saveFriendRelation(String userId, String friendId);
}
