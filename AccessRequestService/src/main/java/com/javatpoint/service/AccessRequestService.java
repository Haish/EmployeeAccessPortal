package com.javatpoint.service;
import java.util.Optional;

import com.javatpoint.model.AccessRequest;
import com.javatpoint.repository.AccessRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

//defining the business logic
@Service
public class AccessRequestService
{


    @Autowired
    AccessRequestRepository accessRequestRepository;


public void saveOrUpdate(AccessRequest request) { accessRequestRepository.save(request); }
public ResponseEntity<AccessRequest> getAccessRequestById(int requestId,int reviewerId) {
    Optional<AccessRequest> reviewerDetails = accessRequestRepository.findById(reviewerId);
    if(!reviewerDetails.isPresent()) {
        return ResponseEntity.noContent().build();
    } else if(reviewerDetails.get().getRole().equals("Employee") || reviewerDetails.get().getRole().equals("Manager") || reviewerDetails.get().getRole().equals("Admin"))
    {
        Optional<AccessRequest> optRequest = accessRequestRepository.findById(requestId);
        if(optRequest.isPresent()) {
            return ResponseEntity.ok().body(optRequest.get());
        }
        return ResponseEntity.noContent().build();
    } else {
        AccessRequest request = new AccessRequest();
        request.setErrorMessage("Do not have permissions to review");
        return ResponseEntity.ok().body(request);
    }
}

public String approveRequest(int requestId,int approverId) {
    Optional<AccessRequest> optionalAccessRequest = accessRequestRepository.findById(requestId);
    if (optionalAccessRequest.isPresent()) {
        if(optionalAccessRequest.get().getManagerId().equals(String.valueOf(approverId)) || optionalAccessRequest.get().getRole().equals("Admin")) {
            AccessRequest request = optionalAccessRequest.get();
            request.setStatus("Approved");
            accessRequestRepository.save(request);
            return "SUCCESS";
        } else {
            return "Do not have permissions to approve";
        }
    } else {
        return "Failure: No record found";
    }
}

public String rejectRequest(int requestId,int approverId) {
Optional<AccessRequest> optionalAccessRequest = accessRequestRepository.findById(requestId);
    if (optionalAccessRequest.isPresent()) {
        if(optionalAccessRequest.get().getManagerId().equals(String.valueOf(approverId))  || optionalAccessRequest.get().getRole().equals("Admin")) {
            AccessRequest request = optionalAccessRequest.get();
            request.setStatus("Reject");
            accessRequestRepository.save(request);
            return "SUCCESS";
        } else {
            return "Do not have permissions to reject";
        }
    }  else {
        return "Failure: No record found";
    }
}

}