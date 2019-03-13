package com.trimble.tcx.esb;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.SubscriptionClient;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class MessageProducer {

    @Autowired
    private TopicClient topicClient;
    @Autowired
    private SubscriptionClient subscriptionClient;

    public void sendMessageToTopic(String message) throws InterruptedException, ServiceBusException {
        log.info("[MessageProducer - sendMessageToTopic execution started]");
        final Message topicMessage =new Message(message.getBytes(StandardCharsets.UTF_8));
        log.info("[MessageProducer - sendMessageToTopic execution completed][Message: "+message+"]");
        topicClient.send(topicMessage);
    }

}
