package com.transporterbackend.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<DriverModel, Long> {

    Optional<DriverModel> findDriverByEmail(String email);

}
