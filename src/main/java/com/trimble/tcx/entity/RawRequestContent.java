package com.trimble.tcx.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trimble.tcx.util.ObjectIdSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document(collection = "rawrequest")
@Component
public class RawRequestContent {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId uniqueId;
    private String sender;
    private String senderSCAC;
    private String receiver;
    private String receiverCode;
    private String contentType;
    private String fileData;
}
