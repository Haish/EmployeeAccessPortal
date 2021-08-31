package com.intuit.employeeaccessmanagement.repository;

import com.intuit.employeeaccessmanagement.model.AccessRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRequestRepository extends CrudRepository<AccessRequest, Long> {
    List<AccessRequest> findById(String id);

}
