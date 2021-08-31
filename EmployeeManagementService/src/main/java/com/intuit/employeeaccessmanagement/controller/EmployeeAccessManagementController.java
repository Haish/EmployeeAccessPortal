package com.intuit.employeeaccessmanagement.controller;

import com.intuit.employeeaccessmanagement.model.AccessRequest;
import com.intuit.employeeaccessmanagement.service.EmployeeAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RequestMapping("/api")
public class EmployeeAccessManagementController {

     @Autowired
     EmployeeAccessService employeeAccessService;

     @GetMapping("/accessrequest/review/{id}")
     public ResponseEntity<AccessRequest> getAccessRequestToReview(@PathVariable(value = "id") String requestId) {

         Optional<AccessRequest> request = employeeAccessService.getAccessRequestById(requestId);
         if(request.isPresent()) {
             return ResponseEntity.ok().body(request.get());
         }
         return ResponseEntity.notFound().build();
     }

    @PostMapping("/accessrequest/approve/{id}")
    public String approveRequest(@PathVariable(value = "id") String requestId) {
        String status = employeeAccessService.approveRequest(requestId);
        return status;
    }

    @PostMapping("/accessrequest/reject/{id}")
    public String rejectRequest(@PathVariable(value = "id") String requestId) {
        String status = employeeAccessService.rejectRequest(requestId);
        return status;
    }

  // need ti implement logging , cache ,
    @PostMapping("/create/accessRequest")
    public AccessRequest createAccessRequest(@RequestBody AccessRequest accessRequest) {
        return employeeAccessService.createAccessRequest(accessRequest);
    }
}
