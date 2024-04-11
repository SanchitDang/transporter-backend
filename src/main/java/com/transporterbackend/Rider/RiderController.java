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
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        riderService.updateRider(riderId, name, email);
    }

}
