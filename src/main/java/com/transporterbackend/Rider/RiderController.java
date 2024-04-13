package com.transporterbackend.Rider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rider")
public class RiderController {

    private final RiderService riderService;
    @Value("${project.rider_images}")
    String imageStoringPath;

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

    @PostMapping("uploadRiderProfileImage")
    public void uploadRiderProfilePicture(
            @RequestParam("riderId") Long riderId,
            @RequestBody MultipartFile image
            ){
        riderService.uploadRiderProfilePicture(imageStoringPath, image, riderId);
    }

    @PostMapping("removeRiderProfileImage")
    public void removeRiderProfilePicture(
            @RequestParam("riderId") Long riderId
    ){
        riderService.removeRiderProfilePicture(riderId);
    }

}
