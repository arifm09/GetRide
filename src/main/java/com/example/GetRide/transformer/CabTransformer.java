package com.example.GetRide.transformer;

import com.example.GetRide.dto.request.CabRequest;
import com.example.GetRide.dto.response.CabResponse;
import com.example.GetRide.model.Cab;

public class CabTransformer {

    public static Cab cabRequestToCab(CabRequest cabRequest){
        return Cab.builder()
                .cabNUmber(cabRequest.getCabNumber())
                .farePerKm(cabRequest.getFarePerKm())
                .cabType((cabRequest.getCabType()))
                .build();
    }

    public static CabResponse cabToCabResponse(Cab cab){
        return CabResponse.builder()
                .cabNumber(cab.getCabNUmber())
                .cabType(cab.getCabType())
                .farePerKm(cab.getFarePerKm())
                .build();
    }
}
