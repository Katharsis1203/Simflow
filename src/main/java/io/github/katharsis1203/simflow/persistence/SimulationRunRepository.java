package io.github.katharsis1203.simflow.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRunRepository
        extends JpaRepository<SimulationRunEntity, Long> {
}