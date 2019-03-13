package com.trimble.tcx.repository;

import com.trimble.tcx.entity.RawRequestContent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequestContentRepository extends MongoRepository<RawRequestContent, ObjectId> {
    RawRequestContent findByUniqueId(ObjectId requestId);
}
