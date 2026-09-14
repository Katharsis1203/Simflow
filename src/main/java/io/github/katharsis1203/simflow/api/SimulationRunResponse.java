package io.github.katharsis1203.simflow.api;

import java.time.Instant;

public record SimulationRunResponse(
        Long id,
        String resourceName,
        int initialAmount,
        int productionPerHour,
        int consumptionPerHour,
        int durationHours,
        int finalAmount,
        Instant startedAt
) {


}
