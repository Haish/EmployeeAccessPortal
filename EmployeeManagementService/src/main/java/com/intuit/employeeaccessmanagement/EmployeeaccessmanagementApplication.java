package com.intuit.employeeaccessmanagement;
import com.intuit.employeeaccessmanagement.model.AccessRequest;
import com.intuit.employeeaccessmanagement.model.Employee;
import com.intuit.employeeaccessmanagement.model.Role;
import com.intuit.employeeaccessmanagement.repository.AccessRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeaccessmanagementApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(EmployeeaccessmanagementApplication.class);

	@Autowired
	private AccessRequestRepository accessRequestRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeaccessmanagementApplication.class, args);
	}

	@Override
	public void run(String... args) {

		log.info("StartApplication...");

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


		accessRequestRepository.save(request1);
		accessRequestRepository.save(request2);



		System.out.println("\nfindAll()");
		accessRequestRepository.findAll().forEach(x -> System.out.println(x));

		System.out.println("\nfindById(1L)");
		accessRequestRepository.findById((long) 1).ifPresent(x -> System.out.println(x));

	}
}
