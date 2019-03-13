package com.trimble.tcx.handler;

import com.trimble.tcx.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
//@Component
public class ReceiverHandler {

    @Autowired
    private ReceiverService receiverService;

    public Mono<ServerResponse> dataContent(ServerRequest serverRequest) {
        String contentType = serverRequest.headers().contentType().orElse(MediaType.TEXT_PLAIN).toString();
        Mono<String> bodyMono = serverRequest.bodyToMono(String.class);
        log.info("[ReceiverHandler - dataContent Execution]");
        //return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(receiverService.decorateDataContent(contentType, bodyMono), RawRequestContentDTO.class);
        return null;
    }

    public Mono<ServerResponse> fileDataContent(ServerRequest serverRequest) {
        log.info("[ReceiverHandler - dataContent Execution]");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject("Executed"));
    }
}
