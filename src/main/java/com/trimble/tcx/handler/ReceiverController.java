package com.trimble.tcx.handler;

import com.trimble.tcx.dto.RawRequestContentDTO;
import com.trimble.tcx.entity.RawRequestContent;
import com.trimble.tcx.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.trimble.tcx.util.ReceiverConstant.APPLICATION_CSV_VALUE;
import static com.trimble.tcx.util.ReceiverConstant.APPLICATION_X12_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Slf4j
@RestController
@RequestMapping("/tcx")
public class ReceiverController {
    @Autowired
    private ReceiverService receiverService;

    @RequestMapping(value = "/data",
            method = RequestMethod.POST,
            consumes = {APPLICATION_CSV_VALUE, APPLICATION_X12_VALUE, APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE},
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RawRequestContent> dataContent(@RequestHeader(value = "Content-Type") String contentType, @RequestBody String data) {
        ResponseEntity<RawRequestContent> responseEntity = null;
        RawRequestContent rawRequestContent = null;
        rawRequestContent = new RawRequestContent();
        rawRequestContent.setContentType(contentType);
        rawRequestContent.setFileData(data);
        rawRequestContent = receiverService.decorateDataContent(rawRequestContent);
        if (rawRequestContent != null) {
            responseEntity = new ResponseEntity<>(rawRequestContent, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/fileData",
            method = RequestMethod.POST,
            consumes = "text/csv",
            produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE
    )
    public ResponseEntity<RawRequestContentDTO> fileDataContent(String fileData) {
        log.info("[ReceiverHandler - dataContent Execution][Body: " + fileData + "]");
        return null;
    }
}
