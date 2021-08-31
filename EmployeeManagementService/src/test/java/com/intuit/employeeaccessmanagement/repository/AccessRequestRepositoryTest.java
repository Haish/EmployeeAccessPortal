package com.intuit.employeeaccessmanagement.repository;

import com.intuit.employeeaccessmanagement.model.AccessRequest;
import com.intuit.employeeaccessmanagement.model.Employee;
import com.intuit.employeeaccessmanagement.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class AccessRequestRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccessRequestRepository repository;

    @Test
    public void testFindByName() {


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

        entityManager.persist(request1);

        List<AccessRequest> requests = repository.findById("1");
        assertEquals(1, requests.size());



    }

}
