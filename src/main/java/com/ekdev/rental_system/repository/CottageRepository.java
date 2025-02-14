package com.ekdev.rental_system.repository;

import com.ekdev.rental_system.model.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {
    Optional<Cottage> findByNumber(String number);
}
