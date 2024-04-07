package com.fullstack.angular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fullstack.angular.exception.ResourceNotFoundException;
import com.fullstack.angular.model.Employee;
import com.fullstack.angular.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Get all employee details
    @GetMapping("/employees")
    public List<Employee>getAllEmployee(){
        return  employeeRepository.findAll();
    }

    //Create employee Rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //get employee by id Rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id "+id));
        return ResponseEntity.ok(employee);
    }

    //Update employee by Rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee>updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id "+id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //Delete employee by Rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id "+id));
        employeeRepository.delete(employee);
        Map<String,Boolean>responce=new HashMap<>();
        responce.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(responce);

    }


}
