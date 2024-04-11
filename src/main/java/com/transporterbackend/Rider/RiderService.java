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

    // Method to fetch all riders from the database
    public List<RiderModel> getRiders(){
        return riderRepository.findAll();
    }

    // Method to fetch a rider from the database by ID
    public RiderModel getRider(Long riderId){
        // Return the rider entity with the provided id
        return riderRepository.findById(riderId)
                .orElseThrow(() -> new IllegalStateException("Rider with id " + riderId + " does not exist."));
    }


    // Method to add a new rider to the database
    public void addRider(RiderModel riderModel) {
        // Check if a rider with the same email already exists
        riderRepository.findRiderByEmail(riderModel.getEmail())
                .ifPresent(existingRider -> {
                    // Throw exception if email is already taken
                    throw new IllegalStateException("Email is already taken");
                });

        // Save the new rider to the database
        riderRepository.save(riderModel);

        // Print the added rider details
        System.out.println(riderModel);
    }


    // Method to delete a rider from the database by riderId
    public void deleteRider(Long riderId) {
        // Check if a rider with the given id exists
        boolean exists = riderRepository.existsById(riderId);
        if(!exists){
            // Throw exception if rider with the provided id does not exist
            throw new IllegalStateException("Rider with id " + riderId + " does not exist.");
        }
        // Delete the rider from the database
        riderRepository.deleteById(riderId);
    }

    // Method to update rider details in the database
    @Transactional // Marks the method as transactional
    public void updateRider(Long riderId, RiderModel updatedRider) {
        // Find the rider by id, or throw exception if not found
        RiderModel rider = riderRepository.findById(riderId).orElseThrow(()-> new IllegalStateException(
                "Rider with id " + riderId + " does not exist."
        ));

        // Set all properties of the rider to the properties of the updatedRider object
        rider.setFirstName(updatedRider.getFirstName());
        rider.setLastName(updatedRider.getLastName());
        rider.setEmail(updatedRider.getEmail());
        rider.setPassword(updatedRider.getPassword());
        rider.setPhone(updatedRider.getPhone());
    }

    // Method to login using email password
    public void loginRider(String email, String password){
        // Find the rider by email, or throw exception if not found
        RiderModel rider = riderRepository.findRiderByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Rider with provided email does not exist"));

        // Check if the password matches
        if(rider.getPassword().equals(password)){
            // Password matches, so login successful
            System.out.println("Login successful");
        } else {
            // Password does not match
            throw new IllegalArgumentException("Incorrect password");
        }
    }

}
