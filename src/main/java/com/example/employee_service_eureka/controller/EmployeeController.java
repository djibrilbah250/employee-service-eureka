package com.example.employee_service_eureka.controller;

import com.example.employee_service_eureka.entity.Employee;
import com.example.employee_service_eureka.requests.AdressRequest;
import com.example.employee_service_eureka.response.AddressResponse;
import com.example.employee_service_eureka.response.EmployeeResponse;
import com.example.employee_service_eureka.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // --- EMPLOYEES ---

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody Employee employee) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    // --- ADDRESSES ---

    @PostMapping("/addresses") // Corrigé : "addresses" au lieu de "adresses"
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AdressRequest request) {
        AddressResponse addressResponse = employeeService.createAddress(request.getCity(), request.getState());
        return ResponseEntity.status(HttpStatus.CREATED).body(addressResponse);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable("id") int id, @RequestBody AdressRequest request) {
        AddressResponse addressResponse = employeeService.updateAddress(id, request.getCity());
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable("id") int id) {
        AddressResponse addressResponse = employeeService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        List<AddressResponse> addresses = employeeService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") int id) {
        employeeService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}