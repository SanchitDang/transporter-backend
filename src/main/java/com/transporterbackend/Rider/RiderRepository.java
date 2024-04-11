package com.transporterbackend.Rider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<RiderModel, Long> {

    Optional<RiderModel> findRiderByEmail(String email);

}
