package com.example.GetRide.controller;

import com.example.GetRide.dto.request.DriverRequest;
import com.example.GetRide.dto.response.DriverResponse;
import com.example.GetRide.model.Driver;
import com.example.GetRide.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {

    public final DriverService driverService;
    @PostMapping
    public ResponseEntity addDriverAndCab(@RequestBody DriverRequest driverRequest){
      String response =  driverService.addDriverAndCab(driverRequest);
       return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getDriver(@RequestParam("mobNo") Long mobNo){
        DriverResponse driverResponse = driverService.getDriver(mobNo);
        return new ResponseEntity(driverResponse,HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity updataDriverMobNo(@RequestParam ("mobNo") Long mobNo,@RequestParam("newMobNo") Long newMobNo){
       String response = driverService.updateDriverMobNo(mobNo,newMobNo);
       return new ResponseEntity(response,HttpStatus.CREATED);
    }
}
