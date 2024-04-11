package com.transporterbackend.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("getDrivers")
    public List<DriverModel> getDrivers(){
        return driverService.getDrivers();
    }

    @PostMapping("addDriver")
    public void addDriver(@RequestBody DriverModel driverModel){
        driverService.addDriver(driverModel);
    }

    @DeleteMapping("deleteDriver/{driverId}")
    public void deleteDriver(@PathVariable("driverId") Long driverId) {
        driverService.deleteDriver(driverId);
    }

    @PutMapping("updateDriver/{driverId}")
    public void updateDriver(
            @PathVariable("driverId") Long driverId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        driverService.updateDriver(driverId, name, email);
    }

}
