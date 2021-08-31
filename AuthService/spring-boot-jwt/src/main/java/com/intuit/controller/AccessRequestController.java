package com.intuit.controller;

import com.intuit.model.AccessRequest;
import com.intuit.model.ResponseTo;
import com.intuit.service.AccessRequestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AccessRequestController
{

    protected final Log logger = LogFactory.getLog(this.getClass());
@Autowired
AccessRequestService accessRequestService;


@PostMapping("/approve/{id}")
public ResponseEntity<String> approveRequest(@PathVariable(value = "id") int requestId, @RequestBody int approverId) throws IOException {
    logger.info("approving request id : "+requestId);
    return accessRequestService.approveRequest(requestId,approverId);
}

@PostMapping("/reject/{id}")
public ResponseEntity<String> rejectRequest(@PathVariable(value = "id") int requestId, @RequestBody int approverId) throws IOException {
    logger.info("rejecting request id : "+requestId);
    return accessRequestService.rejectRequest(requestId,approverId);
}

@PostMapping("/request")
private int createAccessRequest(@RequestBody AccessRequest request)
{
    logger.info("Create Access Request  : "+request.toString());
    accessRequestService.saveOrUpdate(request);
    return request.getRequestId();
}

@PostMapping("/requests")
private String createAllAccessRequest(@RequestBody List<AccessRequest> requests)
{
    logger.info("Creating all Access Request  : ");
    accessRequestService.createAllRequest(requests);
    return "Success";
}

@PostMapping("/review/{id}")
public ResponseEntity<ResponseTo> getAccessRequestToReview(@PathVariable(value = "id") int requestId, @RequestBody int reviewerId) throws IOException {
    logger.info("Review Access Request  : ");
    return accessRequestService.getAccessRequestById(requestId,reviewerId);
}
}
