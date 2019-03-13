package com.trimble.tcx.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RawRequestContentDTO {
    private String uniqueId;
    private String sender;
    private String senderSCAC;
    private String receiver;
    private String receiverCode;
    private String contentType;
    private String fileData;
    private String ediFileLocation;
}
