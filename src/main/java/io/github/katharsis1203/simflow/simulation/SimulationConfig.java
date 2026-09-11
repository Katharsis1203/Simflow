package io.github.katharsis1203.simflow.simulation;

public record SimulationConfig (
    String resourceName,
    int initialAmount,
    int productionPerHour,
    int consumptionPerHour,
    int durationHours
    ){

}
