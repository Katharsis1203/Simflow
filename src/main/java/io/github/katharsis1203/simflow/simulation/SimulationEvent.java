package io.github.katharsis1203.simflow.simulation;

import java.time.Duration;

public interface SimulationEvent {

    Duration scheduledTime();

    void execute(SimulationState state);
}
