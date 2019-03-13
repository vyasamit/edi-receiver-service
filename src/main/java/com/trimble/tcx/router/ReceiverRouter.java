package com.trimble.tcx.router;

import com.trimble.tcx.handler.ReceiverHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author arajan
 * @apiNote DataReceiverRouter Class to expose routes
 */
//@Configuration
public class ReceiverRouter {
//    @Bean
    public RouterFunction<ServerResponse> dataReceiverRouter(ReceiverHandler receiverHandler) {
        return RouterFunctions.route(RequestPredicates.POST("/tcx/data").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), receiverHandler::dataContent)
                .andRoute(RequestPredicates.POST("/tcx/filedata").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)).and(RequestPredicates.contentType(MediaType.MULTIPART_FORM_DATA)), receiverHandler::fileDataContent);
    }
}
