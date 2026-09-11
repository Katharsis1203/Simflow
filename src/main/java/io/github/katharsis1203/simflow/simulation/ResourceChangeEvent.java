package io.github.katharsis1203.simflow.simulation;

import java.time.Duration;

public class ResourceChangeEvent implements SimulationEvent {
    private final Duration scheduledTime;
    private final String resourceName;
    private final int amount;

    public ResourceChangeEvent(
            Duration scheduledTime,
            String resourceName,
            int amount
    ){
        this.scheduledTime = scheduledTime;
        this.resourceName = resourceName;
        this.amount = amount;
    }

    @Override
    public Duration scheduledTime(){
        return scheduledTime;
    }

    @Override
    public void execute(SimulationState state){
        state.changeResource(resourceName, amount);
    }

}
