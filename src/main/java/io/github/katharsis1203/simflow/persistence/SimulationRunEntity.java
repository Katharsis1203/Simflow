package io.github.katharsis1203.simflow.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "simulation_runs")
public class SimulationRunEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceName;
    private int initialAmount;
    private int productionPerHour;
    private int consumptionPerHour;
    private int durationHours;
    private int finalAmount;
    private Instant startedAt;

    protected SimulationRunEntity() {

    }

    public SimulationRunEntity(String resourceName, int initialAmount, int productionPerHour, int consumptionPerHour, int durationHours, int finalAmount, Instant startedAt) {
        this.resourceName = resourceName;
        this.initialAmount = initialAmount;
        this.productionPerHour = productionPerHour;
        this.consumptionPerHour = consumptionPerHour;
        this.durationHours = durationHours;
        this.finalAmount = finalAmount;
        this.startedAt = startedAt;
    }

    public int getConsumptionPerHour() {
        return consumptionPerHour;
    }

    public Long getId() {
        return id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getInitialAmount() {
        return initialAmount;
    }

    public int getProductionPerHour() {
        return productionPerHour;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public int getFinalAmount() {
        return finalAmount;
    }

    public Instant getStartedAt() {
        return startedAt;
    }
}