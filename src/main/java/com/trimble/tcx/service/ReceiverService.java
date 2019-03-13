package com.trimble.tcx.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimble.tcx.dto.RawRequestContentDTO;
import com.trimble.tcx.entity.RawRequestContent;
import com.trimble.tcx.esb.MessageProducer;
import com.trimble.tcx.repository.IRequestContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReceiverService {
    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private IRequestContentRepository requestContentRepository;

    public RawRequestContent decorateDataContent(RawRequestContent rawRequestContent) {
        log.info("[ReceiverService decorateDataContent execution started]");
        RawRequestContentDTO rawRequestContentDTO = null;
        try {
            rawRequestContent = requestContentRepository.save(rawRequestContent);
            rawRequestContentDTO = mapRequestEntityToDto(rawRequestContent);
            log.info("[ReceiverService decorateDataContent execution][ContentType: " + rawRequestContent.getContentType() + "][Data: " + rawRequestContent.getFileData() + "]");
            messageProducer.sendMessageToTopic(getProducerRecord(rawRequestContentDTO));
        } catch (Exception ex) {
            log.error("[Exception occurred][Message: " + ex.getMessage() + "]");
        }
        log.info("[ReceiverService decorateDataContent execution completed]");
        return rawRequestContent;
    }

    private RawRequestContentDTO mapRequestEntityToDto(RawRequestContent rawRequestContent) {
        RawRequestContentDTO requestContentDTO = new RawRequestContentDTO();
        requestContentDTO.setUniqueId(rawRequestContent.getUniqueId().toString());
        requestContentDTO.setSender(rawRequestContent.getSender());
        requestContentDTO.setSenderSCAC(rawRequestContent.getSenderSCAC());
        requestContentDTO.setReceiver(rawRequestContent.getReceiver());
        requestContentDTO.setReceiverCode(rawRequestContent.getReceiverCode());
        requestContentDTO.setContentType(rawRequestContent.getContentType());
        requestContentDTO.setFileData(rawRequestContent.getFileData());
        return requestContentDTO;
    }

    private String getProducerRecord(RawRequestContentDTO rawRequestContent) {
        String message = null;
        log.info("[ReceiverService - getProducerRecord method execution started]");
        try {
            message = new ObjectMapper().writeValueAsString(rawRequestContent);
        } catch (Exception ex) {
            log.error("[ReceiverService - getProducerRecord Exception][Message: " + ex.getMessage() + "]");
        }
        log.info("[ReceiverService - getProducerRecord method execution completed]");
        return message;
    }
}
