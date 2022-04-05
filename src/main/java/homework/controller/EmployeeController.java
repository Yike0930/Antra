package homework.controller;

import homework.exception.GlobalExceptionHandler;
import homework.pojo.RestEmployee;
import homework.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    private ResponseEntity<List<RestEmployee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/{age}")
    private ResponseEntity<List<RestEmployee>> getEmployeesByAge(@PathVariable("age") int age) {
        return new ResponseEntity<>(employeeService.getEmployeesByAge(age), HttpStatus.OK);
    }

    @GetMapping("employees/age")
    public ResponseEntity<List<List<RestEmployee>>> getAllEmployeesGroupByAge() {
        return new ResponseEntity<>(employeeService.getAllEmployeesGroupByAge(), HttpStatus.OK);
    }

}
