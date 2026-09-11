package io.github.katharsis1203.simflow.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationRunnerTest {

    @Test
    void runsConfiguredSimulation() {

        SimulationConfig config = new SimulationConfig(
                "coconuts",
                100,
                5,
                8,
                24
        );

        SimulationRunner runner = new SimulationRunner();

        SimulationEngine engine = runner.run(config);

        assertEquals(
                28,
                engine.state().getResource("coconuts")
        );
    }
}