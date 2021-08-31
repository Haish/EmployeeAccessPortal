package com.intuit.updateaccessrequest.repository;

import com.intuit.updateaccessrequest.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UpdateAccessRequestRepository extends CrudRepository<Application, Integer> {

}