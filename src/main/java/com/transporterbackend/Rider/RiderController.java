package com.transporterbackend.Rider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rider")
public class RiderController {

    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @GetMapping("getRiders")
    public List<RiderModel> getRiders(){
        return riderService.getRiders();
    }

    @GetMapping("getRider/{riderId}")
    public RiderModel getRider(
            @PathVariable("riderId") Long riderId
    ){
        return riderService.getRider(riderId);
    }

    @PostMapping("addRider")
    public void addRider(@RequestBody RiderModel riderModel){
        riderService.addRider(riderModel);
    }

    @DeleteMapping("deleteRider/{riderId}")
    public void deleteRider(@PathVariable("riderId") Long riderId) {
        riderService.deleteRider(riderId);
    }

    @PutMapping("updateRider/{riderId}")
    public void updateRider(
            @PathVariable("riderId") Long riderId,
            @RequestBody RiderModel updatedRider){
        riderService.updateRider(riderId, updatedRider);
    }

    @PostMapping("loginRider")
    public void loginRider(@RequestBody LoginRequest loginRequest){
        riderService.loginRider(loginRequest.getEmail(), loginRequest.getPassword());
    }


}
