package io.github.katharsis1203.simflow.simulation;

import java.util.HashMap;
import java.util.Map;

public class SimulationState {

    private final Map<String, Integer> resources = new HashMap<>();

    public void setResource(String name, int amount){
        resources.put(name,amount);
    }
    public int getResource(String name){
        return resources.getOrDefault(name, 0);
    }

    public void changeResource(String name, int amount){
        int currentAmount = getResource(name);
        resources.put(name, currentAmount + amount);
    }
}
