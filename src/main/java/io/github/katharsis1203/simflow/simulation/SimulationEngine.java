package io.github.katharsis1203.simflow.simulation;

import java.time.Duration;
import java.util.PriorityQueue;
import java.util.Comparator;

public class SimulationEngine {
    private final PriorityQueue<SimulationEvent> eventQueue;
    private Duration currentTime = Duration.ZERO;

    public SimulationEngine(){
        eventQueue = new PriorityQueue<>(
                Comparator.comparing(SimulationEvent::scheduledTime)
        );
    }

    public void schedule(SimulationEvent event){
        eventQueue.add(event);
    }

    public Duration currentTime(){
        return currentTime;
    }

    public void runNextEvent(){
        SimulationEvent nextEvent = eventQueue.poll();

        if(nextEvent == null){
            return;
        }

        currentTime = nextEvent.scheduledTime();

        nextEvent.execute();
    }

    public int pendingEventCount(){
        return eventQueue.size();
    }

    public void runUntil(Duration endTime){
        while (!eventQueue.isEmpty()
            && eventQueue.peek().scheduledTime().compareTo(endTime)<=0) {

            runNextEvent();
        }

        currentTime = endTime;
    }
}
