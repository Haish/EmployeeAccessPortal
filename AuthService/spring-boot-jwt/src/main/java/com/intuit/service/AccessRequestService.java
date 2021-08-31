package com.intuit.service;


import com.google.gson.Gson;
import com.intuit.exception.NotAuthorisedToApproveRejectException;
import com.intuit.exception.ResourceNotFoundException;
import com.intuit.model.Employee;
import com.intuit.model.ResponseTo;
import com.intuit.repository.AccessRequestRepository;
import com.intuit.repository.EmployeeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.intuit.model.AccessRequest;
import org.springframework.cache.annotation.Cacheable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.util.List;
import java.util.Optional;

@Service
public class AccessRequestService
{


    protected final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    AccessRequestRepository accessRequestRepository;

    @Autowired
    EmployeeRepository employeeRepository;


public void saveOrUpdate(AccessRequest request) {
    logger.info("Save Access request : Access Request Service");
    accessRequestRepository.save(request);
}

public void createAllRequest(List<AccessRequest> requests) {
    logger.info("Create Access request : Access Request Service");
    accessRequestRepository.saveAll(requests);
}


public ResponseEntity<ResponseTo> getAccessRequestById(int requestId, int reviewerId) throws IOException {
    logger.info("Get Access request by Id : Access Request Service "+requestId);
    Optional<Employee> reviewerDetails = getEmployeeById(reviewerId);
    if(reviewerDetails.get().getRole().equals("Employee") || reviewerDetails.get().getRole().equals("Manager") || reviewerDetails.get().getRole().equals("Admin"))
    {
        Optional<AccessRequest> optRequest = getAcessRequestById(requestId);
        Optional<Employee> optEmployee = getEmployeeById(optRequest.get().getDigitalIdentityNumber());
        ResponseTo responseTo = new ResponseTo();
        responseTo.setEmp(optEmployee.get());
        responseTo.setRequest(optRequest.get().getRequest());
        responseTo.setStatus("Created");
        responseTo.setRequestId(optRequest.get().getRequestId());
        return ResponseEntity.ok().body(responseTo);
    } else {
        logger.error("Do not have permissions to review this request");
        throw new NotAuthorisedToApproveRejectException("Do not have permissions to review this request");
    }
}

@Cacheable(value = "employee")
public Optional<Employee> getEmployeeById(int digitalId) throws IOException {
        Optional<Employee> optRequest = getEmployee(digitalId);

        // GET call to employee service to fetch the details


        if(!optRequest.isPresent()) {
            logger.error("employee details Not found  with id = " + digitalId);
            throw new ResourceNotFoundException("employee details Not found  with id = " + digitalId);
        }
        return optRequest;
    }

    private  Optional<Employee> getEmployee(int digitalId) throws IOException {
        String token = getToken();
        Employee emp = null;
        URL obj = new URL("http://localhost:8081/employee/"+digitalId);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization",token);
        con.setRequestProperty("Content-Type", "application/json");
       // con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            Gson g = new Gson();
            emp = g.fromJson(response.toString(), Employee.class);
        } else {
            System.out.println("GET request not worked");
        }


        return Optional.of(emp);
    }

    private static String getToken() throws IOException {

        URL obj = new URL("http://localhost:8082/authenticate");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        String inputLine = "";
        String jsonInputString = "{\"username\": \"javainuse\", \"password\": \"password\"}";
      //  con.setRequest
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }



        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            inputLine = response.toString();
            System.out.println(response.toString());
            String inputLines[] = inputLine.split(":");
            inputLine = inputLines[1].replace("\"","").replace("}","");
        }


       return "Bearer "+inputLine;

    }

@Cacheable(value = "accessRequest")
public Optional<AccessRequest> getAcessRequestById(int requestId){
    Optional<AccessRequest> optRequest = accessRequestRepository.findById(requestId);
    if(!optRequest.isPresent()) {
        throw new ResourceNotFoundException("Access Request Not found  with id = " + requestId);
    }
    return optRequest;
}


public ResponseEntity<String> approveRequest(int requestId,int approverId) throws IOException {
    logger.info("Approve Access request with request Id  = " + requestId);
    Optional<AccessRequest> OptRequest = getAcessRequestById(requestId);
    int digitalId = OptRequest.get().getDigitalIdentityNumber();
    Optional<Employee> optRequestEmployee = getEmployeeById(digitalId);
    Optional<Employee> optApproverEmployee = getEmployeeById(approverId);
    if((optApproverEmployee.get().getDigitalIdentityNumber() == optRequestEmployee.get().getManagerId())  || (optApproverEmployee.get().getRole().equals("Admin"))) {
        Optional<AccessRequest> optionalAccessRequest = getAcessRequestById(requestId);
        AccessRequest request = optionalAccessRequest.get();
        request.setStatus("Approved");
        accessRequestRepository.save(request);
        return ResponseEntity.ok().body("Success");
    } else {
        logger.error("Do not have permissions to approve this request");
        throw new NotAuthorisedToApproveRejectException("Do not have permissions to approve this request");
    }
}

    public ResponseEntity<String> rejectRequest(int requestId,int approverId) throws IOException {
        logger.info("Reject Access request with request Id  = " + requestId);
        Optional<AccessRequest> OptRequest = getAcessRequestById(requestId);
        int digitalId = OptRequest.get().getDigitalIdentityNumber();
        Optional<Employee> optRequestEmployee = getEmployeeById(digitalId);
        Optional<Employee> optApproverEmployee = getEmployeeById(approverId);
        if((optApproverEmployee.get().getDigitalIdentityNumber() == optRequestEmployee.get().getManagerId())  || (optApproverEmployee.get().getRole().equals("Admin"))) {
            Optional<AccessRequest> optionalAccessRequest = getAcessRequestById(requestId);
            AccessRequest request = optionalAccessRequest.get();
            request.setStatus("Reject");
            accessRequestRepository.save(request);
            return ResponseEntity.ok().body("Success");
        } else {
            logger.error("Do not have permissions to reject this request");
            throw new NotAuthorisedToApproveRejectException("Do not have permissions to reject this request");
        }
    }

}