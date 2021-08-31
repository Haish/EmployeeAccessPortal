package com.intuit.repository;

import com.intuit.model.AccessRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccessRequestRepository extends CrudRepository<AccessRequest, Integer> {


}
