package com.transporterbackend.Driver;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    public List<DriverModel> getDrivers(){
        return driverRepository.findAll();
    }

    public void addDriver(DriverModel driverModel) {
        Optional<DriverModel> driverByEmail = driverRepository.findDriverByEmail(driverModel.getEmail());
        if(driverByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        driverRepository.save(driverModel);
        System.out.println(driverModel);
    }

    public void deleteDriver(Long driverId) {
            boolean exists = driverRepository.existsById(driverId);
            if(!exists){
                throw new IllegalStateException("Driver with id " + driverId + " does not exists.");
            }
            driverRepository.deleteById(driverId);
    }

    @Transactional
    public void updateDriver(Long driverId, String name, String email) {
        DriverModel driver = driverRepository.findById(driverId).orElseThrow(()-> new IllegalStateException(
                "Driver with id " + driverId + " does not exists."
        ));
        if(name != null && !name.isEmpty() && !Objects.equals(driver.getFirstName(), name)){
            driver.setFirstName(name);
        }
    }
}
