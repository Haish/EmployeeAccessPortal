package com.javatpoint.repository;

import com.javatpoint.model.AccessRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccessRequestRepository extends CrudRepository<AccessRequest, Integer> {


}
