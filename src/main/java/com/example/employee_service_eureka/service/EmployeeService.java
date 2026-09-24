package com.example.employee_service_eureka.service;

import com.example.employee_service_eureka.entity.Employee;
import com.example.employee_service_eureka.feignclient.AddressClient;
import com.example.employee_service_eureka.repository.EmployeeRepo;
import com.example.employee_service_eureka.requests.AdressRequest;
import com.example.employee_service_eureka.response.AddressResponse;
import com.example.employee_service_eureka.response.EmployeeResponse;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    // Spring will create the implementation
    // for this class
    // and will inject the bean here (proxy)
    @Autowired
    private AddressClient addressClient;

    public EmployeeResponse createEmployee(Employee employee) {
        return mapper.map(employeeRepo.saveAndFlush(employee), EmployeeResponse.class);
    }

    public EmployeeResponse getEmployeeById(int id) {

        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isEmpty()){
            throw new NotFoundException("Employee not found");
        }

        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        // Usage FeignClient avec ResponseEntity
        AddressResponse addressResponse = this.getAddressById(employee.get().getAddressId());
        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();

        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee employee : employees) {

            AddressResponse addressResponse = this.getAddressById(employee.getAddressId());

            EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

            employeeResponse.setAddressResponse(addressResponse);

            employeeResponses.add(employeeResponse);
        }
        return  employeeResponses;
    }

    public List<AddressResponse> getAllAddresses() {
        return addressClient.getAllAddresses().getBody();
    }

    public AddressResponse getAddressById(int id) {
        return addressClient.getAddressByEmployeeId(id).getBody();
    }

    public AddressResponse createAddress(String city, String state) {

        AdressRequest adress = new  AdressRequest();
        adress.setCity(city);
        adress.setState(state);
        return addressClient.createAddress(adress).getBody();
    }

    public AddressResponse updateAddress(int id, String city) {
        return  addressClient.updateAddress(id, city).getBody();
    }

    public void  deleteAddress(int id) {
        addressClient.deleteAddress(id);
    }





}