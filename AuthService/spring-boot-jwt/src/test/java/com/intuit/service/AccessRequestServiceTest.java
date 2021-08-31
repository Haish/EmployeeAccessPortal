package com.intuit.service;

import com.intuit.exception.NotAuthorisedToApproveRejectExceptionTest;
import com.intuit.model.AccessRequest;
import com.intuit.model.Employee;
import com.intuit.model.ResponseTo;
import com.intuit.repository.AccessRequestRepository;
import com.intuit.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AccessRequestServiceTest {
    @Mock
    AccessRequestRepository accessRequestRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    AccessRequestService accessRequestService;

    @Test
    public void getAccessRequestByIdtest() throws IOException {
        Employee manager = new Employee();
        manager.setRole("Manager");
        Optional<Employee> emp = Optional.of(manager);
        given(employeeRepository.findById(1)).willReturn(emp);
        AccessRequest acess = new AccessRequest();
        acess.setRequestId(2);
        acess.setDigitalIdentityNumber(1);
        Optional<AccessRequest> access = Optional.of(acess);
        given(accessRequestRepository.findById(2)).willReturn(access);
        ResponseEntity<ResponseTo> responseEntity = accessRequestService.getAccessRequestById(2, 1);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody().getStatus(), "Created");
    }

    @Test
    public void approveRequestTest() throws IOException {
        Employee manager = new Employee();
        manager.setRole("Manager");
        manager.setManagerId(1);
        manager.setDigitalIdentityNumber(1);
        Optional<Employee> emp = Optional.of(manager);
        given(employeeRepository.findById(1)).willReturn(emp);
        AccessRequest acess = new AccessRequest();
        acess.setRequestId(2);
        acess.setDigitalIdentityNumber(1);
        Optional<AccessRequest> access = Optional.of(acess);
        given(accessRequestRepository.findById(2)).willReturn(access);
        ResponseEntity<String> responseEntity = accessRequestService.approveRequest(2, 1);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), "Success");
    }

    @Test(expected = NotAuthorisedToApproveRejectExceptionTest.class)
    public void approveRequestInvalidTest() throws IOException {
        Employee manager = new Employee();
        manager.setRole("Manager");
        manager.setManagerId(1);
        Optional<Employee> emp = Optional.of(manager);
        given(employeeRepository.findById(1)).willReturn(emp);
        AccessRequest acess = new AccessRequest();
        acess.setRequestId(2);
        acess.setDigitalIdentityNumber(1);
        Optional<AccessRequest> access = Optional.of(acess);
        given(accessRequestRepository.findById(2)).willReturn(access);
        ResponseEntity<String> responseEntity = accessRequestService.approveRequest(2, 1);

    }

    @Test
    public void rejectRequestTest() throws IOException {
        Employee manager = new Employee();
        manager.setRole("Manager");
        manager.setManagerId(1);
        manager.setDigitalIdentityNumber(1);
        Optional<Employee> emp = Optional.of(manager);
        given(employeeRepository.findById(1)).willReturn(emp);
        AccessRequest acess = new AccessRequest();
        acess.setRequestId(2);
        acess.setDigitalIdentityNumber(1);
        Optional<AccessRequest> access = Optional.of(acess);
        given(accessRequestRepository.findById(2)).willReturn(access);
        ResponseEntity<String> responseEntity = accessRequestService.rejectRequest(2, 1);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getBody(), "Success");
    }
}
