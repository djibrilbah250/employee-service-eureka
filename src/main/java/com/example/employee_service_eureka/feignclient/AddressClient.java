package com.example.employee_service_eureka.feignclient;

import com.example.employee_service_eureka.requests.AdressRequest;
import com.example.employee_service_eureka.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//communication directe entre services
//@FeignClient(
//        name = "address-service",
//        url = "http://localhost:8002", //url du service
//        path = "/address-service")

//Pour utiliser Eureka, supprimer l'attribut url de @FeignClient.
@FeignClient(
        name = "krakend-1",
        url = "http://localhost:8081"
)
public interface AddressClient {

    /**
     * Vérifier si difference de nommenclanture des artributs
     * Vérifier si difference de nommenclanture des methodes
     */

    @GetMapping("/address/{id}")
    ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id);

    @GetMapping("/address")
    ResponseEntity<List<AddressResponse>> getAllAddresses();

    @PostMapping("/address")
    ResponseEntity<AddressResponse> createAddress(@RequestBody AdressRequest adressRequest);


    @PutMapping("/address/{id}")
    ResponseEntity<AddressResponse> updateAddress(@PathVariable("id") int id, @RequestBody String city);

    @DeleteMapping("/address/{id}")
    ResponseEntity<AddressResponse> deleteAddress(@PathVariable("id") int id);

}
