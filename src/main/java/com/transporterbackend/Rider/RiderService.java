package com.transporterbackend.Rider;

import com.transporterbackend.Utils.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    // Method to upload rider's profile picture
    public void uploadRiderProfilePicture(String imageStoringPath, MultipartFile image, Long riderId){
        FileService fileService = new FileService();
        try {
            // check if rider id exist or throw exception
            RiderModel rider = riderRepository.findById(riderId).orElseThrow(() -> new IllegalStateException("Rider with id " + riderId + " does not exist."));
            // getting the path where the image is stored in server
            String filePath = fileService.uploadImage(imageStoringPath, image);
            System.out.println(filePath);
            // putting the path profileImagePath column
            rider.setProfileImagePath(filePath);
            // Save the updated rider to the database
            riderRepository.save(rider);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        }
    }

    public void removeRiderProfilePicture(Long riderId){
        FileService fileService = new FileService();
        try {
            // get the image path
            RiderModel rider = riderRepository.findById(riderId).orElseThrow(() -> new IllegalStateException("Rider with id " + riderId + " does not exist."));
            String imageFilePath = rider.getProfileImagePath();

            // delete the image from server
            fileService.removeImage(imageFilePath);

            // set the path as empty in the profileImagePath of rider
            rider.setProfileImagePath(null);
            riderRepository.save(rider);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        }

    }

}
