package io.github.katharsis1203.simflow.simulation;

public record SimulationResult (
        String resourceName,
        int finalAmount,
        int durationHours
){
}
