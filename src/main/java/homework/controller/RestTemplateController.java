//package homework.controller;
//
//import homework.pojo.RestEmployee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//
//@RestController
//public class RestTemplateController {
//
//    private final String URI_EMPLOYEE = "https://dummy.restapiexample.com/api/v1/employees";
////    private final String URI_EMPLOYEE_ID = "http://localhost:8081/employees/{id}";
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @GetMapping("/v1/allEmployees")
//    public ResponseEntity getAllV1() {
//        RestEmployee[] EmployeesArray = restTemplate.getForObject(URI_EMPLOYEE, RestEmployee[].class);
//        return new ResponseEntity<>(Arrays.asList(EmployeesArray), HttpStatus.OK);
//    }
//
////    @GetMapping("/v1/employees/{id}")
////    public ResponseEntity getByIdV1(@PathVariable final Integer id) {
////        Map < String, String > params = new HashMap < > ();
////        params.put("id", String.valueOf(id));
////        Employee Employee = restTemplate.getForObject(URI_EMPLOYEE_ID, Employee.class, params);
////        return new ResponseEntity < > (Employee, HttpStatus.OK);
////    }
//}
