package com.resqhub.resqhub.repository;

import com.resqhub.resqhub.model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Long> {
    List<PoliceStation> findByAreaIgnoreCase(String area);
}