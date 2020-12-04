package com.elixirtask.parselogs.controller;

import com.elixirtask.parselogs.dto.LogDto;
import com.elixirtask.parselogs.dto.ResponseMessage;
import com.elixirtask.parselogs.service.LogParserService;
import com.elixirtask.parselogs.util.LogParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 3/12/20 - 11:20 PM
 */

@RestController
public class LogParserController {

    @Autowired
    private LogParserService logParserService;

    @PostMapping("/import")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("logFile") MultipartFile logFile) {
        try {
            List<LogDto> logList = LogParserUtil.getLogs(logFile);
            logParserService.saveLogs(logList);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Logs Saved Successfully", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Could not upload the file", HttpStatus.EXPECTATION_FAILED.value()));
        }
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseMessage> searchByDate(@RequestParam("searchQuery") String searchQuery) {
        try {
            List<LogDto> logList = logParserService.getLogByDate(searchQuery);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>("Logs Saved Successfully", HttpStatus.CREATED.value(), logList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage<>("Could not upload the file", HttpStatus.EXPECTATION_FAILED.value()));
        }
    }


}
