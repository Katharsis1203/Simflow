package io.github.katharsis1203.simflow.simulation;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class SimulationRunner {

    public SimulationEngine run(SimulationConfig config){

        SimulationEngine engine = new SimulationEngine();

        engine.state().setResource(
                config.resourceName(),
                config.initialAmount()
        );

        int hourlyChange =
                config.productionPerHour()
                - config.consumptionPerHour();

        for (int hour = 1; hour <= config.durationHours(); hour++) {

            engine.schedule(
                    new ResourceChangeEvent(
                            Duration.ofHours(hour),
                            config.resourceName(),
                            hourlyChange
                    )
            );
        }

        engine.runUntil(
                Duration.ofHours(config.durationHours())
        );

        return engine;

    }
}
