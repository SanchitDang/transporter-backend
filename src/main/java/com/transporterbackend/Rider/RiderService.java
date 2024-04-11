package com.transporterbackend.Rider;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RiderService {

    private final RiderRepository riderRepository;

    @Autowired
    public RiderService(RiderRepository riderRepository){
        this.riderRepository = riderRepository;
    }

    public List<RiderModel> getRiders(){
        return riderRepository.findAll();
    }

    public void addRider(RiderModel riderModel) {
        Optional<RiderModel> riderByEmail = riderRepository.findRiderByEmail(riderModel.getEmail());
        if(riderByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        riderRepository.save(riderModel);
        System.out.println(riderModel);
    }

    public void deleteRider(Long riderId) {
            boolean exists = riderRepository.existsById(riderId);
            if(!exists){
                throw new IllegalStateException("Rider with id " + riderId + " does not exists.");
            }
            riderRepository.deleteById(riderId);
    }

    @Transactional
    public void updateRider(Long riderId, String name, String email) {
        RiderModel driver = riderRepository.findById(riderId).orElseThrow(()-> new IllegalStateException(
                "Rider with id " + riderId + " does not exists."
        ));
        if(name != null && name.length() > 0 && !Objects.equals(driver.getFirstName(), name)){
            driver.setFirstName(name);
        }
    }
}
