package com.intuit.employeeaccessmanagement.service;

import com.intuit.employeeaccessmanagement.model.AccessRequest;
import com.intuit.employeeaccessmanagement.model.Employee;
import com.intuit.employeeaccessmanagement.model.Role;
import com.intuit.employeeaccessmanagement.repository.AccessRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeAccessService {


     Map<Integer,AccessRequest> cacheMap = getData();
     static Integer id ;
     @Autowired
     AccessRequestRepository accessRequestRepository;

     public Optional<AccessRequest> getAccessRequestById(String requestId) {

         if(cacheMap.get(new Integer(requestId))!=null) {
             AccessRequest request = cacheMap.get(new Integer(requestId));
             return Optional.of(request);
         } else {
             Optional<AccessRequest> optRequest = accessRequestRepository.findById(Long.parseLong(requestId));
             cacheMap.put(id,optRequest.get());
             return optRequest;
         }
     }

    public String approveRequest(String requestId) {

        if(cacheMap.get(new Integer(requestId))!=null) {
            AccessRequest request = cacheMap.get(new Integer(requestId));
            request.setStatus("Approved");
            cacheMap.put(request.getRequestId(),request);
            return "SUCCESS";
        } else {
            Optional<AccessRequest> optionalAccessRequest = accessRequestRepository.findById(Long.parseLong(requestId));
            if (optionalAccessRequest.isPresent()) {
                AccessRequest request = optionalAccessRequest.get();
                request.setStatus("Approved");
                accessRequestRepository.save(request);
                return "SUCCESS";
            }
            return "Failure";
        }
    }

    public String rejectRequest(String requestId) {

        if(cacheMap.get(new Integer(requestId))!=null) {
            AccessRequest request = cacheMap.get(new Integer(requestId));
            request.setStatus("Reject");
            cacheMap.put(request.getRequestId(),request);
            return "SUCCESS";
        } else {
            Optional<AccessRequest> optionalAccessRequest = accessRequestRepository.findById(Long.parseLong(requestId));
            if (optionalAccessRequest.isPresent()) {
                AccessRequest request = optionalAccessRequest.get();
                request.setStatus("Reject");
                accessRequestRepository.save(request);
                return "SUCCESS";
            }
            return "Failure";
        }

    }

    public AccessRequest createAccessRequest(AccessRequest accessRequest) {
        accessRequest.setStatus("Create");
        cacheMap.put(id,accessRequest);

        return accessRequest;
       // return accessRequestRepository.save(accessRequest);
    }

    public static Map<Integer,AccessRequest> getData() {
        Map<Integer,AccessRequest> map = new HashMap<>();

        AccessRequest request1 = new AccessRequest();
        request1.setStatus("Created");
        request1.setRequestId(1);
        request1.setRequest("Need acess to src/dist folder");
        Employee emp1 = new Employee();
        emp1.setDigitalIdentityNumber(1);
        emp1.setName("John");
        emp1.setRole(Role.EMPLOYEE);
        emp1.setManagerId("2");
        request1.setEmployee(emp1);
        map.put(request1.getRequestId(),request1);

        AccessRequest request2 = new AccessRequest();
        request2.setStatus("Created");
        request2.setRequestId(2);
        request2.setRequest("Need acess to src/img folder");
        Employee emp2 = new Employee();
        emp2.setDigitalIdentityNumber(2);
        emp2.setName("Tom");
        emp2.setRole(Role.MANAGER);
        emp2.setManagerId("4");
        request1.setEmployee(emp2);
        map.put(request2.getRequestId(),request1);
        id = 3;
        return map;
    }


}
