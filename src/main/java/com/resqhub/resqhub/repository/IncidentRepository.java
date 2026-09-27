package com.resqhub.resqhub.repository;

import com.resqhub.resqhub.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
