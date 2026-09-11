package io.github.katharsis1203.simflow.api;

import io.github.katharsis1203.simflow.simulation.SimulationConfig;
import io.github.katharsis1203.simflow.simulation.SimulationEngine;
import io.github.katharsis1203.simflow.simulation.SimulationResult;
import io.github.katharsis1203.simflow.simulation.SimulationRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simulations")
public class SimulationController {

    private final SimulationRunner runner = new SimulationRunner();

    @PostMapping
    public SimulationResult runSimulation(
            @RequestBody SimulationConfig  config
    ){
        SimulationEngine engine = runner.run(config);

        return new SimulationResult(
                config.resourceName(),
                engine.state().getResource(config.resourceName()),
                config.durationHours()
        );
    }
}
