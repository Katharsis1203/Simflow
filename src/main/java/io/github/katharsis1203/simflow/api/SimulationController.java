package io.github.katharsis1203.simflow.api;

import io.github.katharsis1203.simflow.persistence.SimulationRunEntity;
import io.github.katharsis1203.simflow.persistence.SimulationRunRepository;
import io.github.katharsis1203.simflow.simulation.SimulationConfig;
import io.github.katharsis1203.simflow.simulation.SimulationEngine;
import io.github.katharsis1203.simflow.simulation.SimulationResult;
import io.github.katharsis1203.simflow.simulation.SimulationRunner;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/simulations")
public class SimulationController {

    private final SimulationRunner runner;
    private final SimulationRunRepository runRepository;

    public SimulationController(
            SimulationRunner runner,
            SimulationRunRepository runRepository
    ) {
        this.runner = runner;
        this.runRepository = runRepository;
    }

    @PostMapping
    public SimulationResult runSimulation(
            @RequestBody SimulationConfig config
    ) {
        Instant startedAt = Instant.now();

        SimulationEngine engine = runner.run(config);

        SimulationRunEntity run = new SimulationRunEntity(
                config.resourceName(),
                config.initialAmount(),
                config.productionPerHour(),
                config.consumptionPerHour(),
                config.durationHours(),
                engine.state().getResource(config.resourceName()),
                startedAt
        );

        runRepository.save(run);

        return new SimulationResult(
                config.resourceName(),
                engine.state().getResource(config.resourceName()),
                config.durationHours()
        );
    }
}