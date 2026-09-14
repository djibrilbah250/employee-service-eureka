package com.example.employee_service_eureka.feignclient;

import com.example.employee_service_eureka.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service", url = "http://localhost:8002", path = "/address-service")
public interface AddressClient {

    @GetMapping("/address/{id}")
    ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id);

}