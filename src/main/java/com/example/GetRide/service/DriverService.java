package com.example.GetRide.service;

import com.example.GetRide.dto.request.DriverRequest;
import com.example.GetRide.dto.response.CabResponse;
import com.example.GetRide.dto.response.DriverResponse;
import com.example.GetRide.model.Cab;
import com.example.GetRide.model.Driver;
import com.example.GetRide.repository.DriverRepository;
import com.example.GetRide.transformer.CabTransformer;
import com.example.GetRide.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;
    public String addDriverAndCab(DriverRequest driverRequest) {
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Cab cab = CabTransformer.cabRequestToCab(driverRequest.getCabRequest());

        driver.setCab(cab);
        cab.setDriver(driver);

        driverRepository.save(driver);

        return "Driver registered successfully";
    }

    public DriverResponse getDriver(Long mobNo) {
        Driver driver = driverRepository.getDriverByMobNo(mobNo);
        DriverResponse driverResponse = DriverTransformer.driverToDriverResponse(driver);
        return driverResponse;
    }

    public String updateDriverMobNo(Long mobNo,Long newMobNo) {
        driverRepository.updateDriverMobNo(mobNo, newMobNo);
        return "Mobile Number Updated Successfully";
    }
}
