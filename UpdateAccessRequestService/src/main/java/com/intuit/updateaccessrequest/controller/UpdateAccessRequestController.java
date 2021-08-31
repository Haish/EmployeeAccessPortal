package com.intuit.updateaccessrequest.controller;

import com.intuit.updateaccessrequest.model.Application;
import com.intuit.updateaccessrequest.repository.UpdateAccessRequestRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
public class UpdateAccessRequestController {

    @Autowired
    UpdateAccessRequestRepository updateAccessRequestRepository;
    protected final Log logger = LogFactory.getLog(this.getClass());

    @PostMapping("/approve")
    public ResponseEntity<Application> approveRequest(@RequestBody Application application) throws IOException {
        logger.info("approving app id : "+application.getAppId());
        return ResponseEntity.ok().body(updateAccessRequestRepository.save(application));
    }


    @PostMapping("/approveall")
    public ResponseEntity<Iterable<Application>> approveRequests(@RequestBody List<Application> applications) throws IOException {
        logger.info("approving all request : ");
        return ResponseEntity.ok().body(updateAccessRequestRepository.saveAll(applications));
    }

    @PostMapping("/reject")
    public ResponseEntity<String> rejectRequest(@RequestBody Application application) throws IOException {
        logger.info("rejecting request id : "+application.getAppId());
        updateAccessRequestRepository.delete(application);
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping("/rejectall")
    public ResponseEntity<String> rejectRequests(@RequestBody List<Application> applications) throws IOException {
        logger.info("rejecting all requests  : ");
        updateAccessRequestRepository.deleteAll(applications);
        return ResponseEntity.ok().body("Success");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Application> getById(@PathVariable int id) throws IOException {
        logger.info("fetching request id : "+id);
        return ResponseEntity.ok().body(updateAccessRequestRepository.findById(id).get());
            }
}
