package com.cloudcof.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by simon on 2016/8/20.
 */
public interface MessageRepository extends MongoRepository<Message,String> {
}
