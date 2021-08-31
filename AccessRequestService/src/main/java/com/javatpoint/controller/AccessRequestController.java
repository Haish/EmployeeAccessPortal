package com.javatpoint.controller;

import com.javatpoint.model.AccessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javatpoint.service.AccessRequestService;
//creating RestController
@RestController
public class AccessRequestController
{

@Autowired
AccessRequestService accessRequestService;


@PostMapping("/approve/{id}")
public String approveRequest(@PathVariable(value = "id") int requestId, @RequestBody int approverId) {
    return accessRequestService.approveRequest(requestId,approverId);
}

@PostMapping("/reject/{id}")
public String rejectRequest(@PathVariable(value = "id") int requestId, @RequestBody int approverId) {
    return accessRequestService.rejectRequest(requestId,approverId);
}

@PostMapping("/request")
private int createAccessRequest(@RequestBody AccessRequest request)
{
    accessRequestService.saveOrUpdate(request);
    return request.getRequestId();
}

@PostMapping("/review/{id}")
public ResponseEntity<AccessRequest> getAccessRequestToReview(@PathVariable(value = "id") int requestId,@RequestBody int reviewerId) {
    return accessRequestService.getAccessRequestById(requestId,reviewerId);
}
}
