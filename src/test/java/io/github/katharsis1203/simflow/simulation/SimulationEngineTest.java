package io.github.katharsis1203.simflow.simulation;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationEngineTest {

    @Test
    void runningAnEventAdvancesSimulationTime() {

        SimulationEngine engine = new SimulationEngine();

        SimulationEvent event = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(2);
            }

            @Override
            public void execute() {
                // Nothing needed yet
            }
        };

        engine.schedule(event);

        engine.runNextEvent();

        assertEquals(
                Duration.ofHours(2),
                engine.currentTime()
        );
    }

    @Test
    void eventsRunInChronologicalOrder() {

        SimulationEngine engine = new SimulationEngine();

        SimulationEvent laterEvent = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(5);
            }

            @Override
            public void execute() {
                // Nothing needed yet
            }
        };

        SimulationEvent earlierEvent = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(2);
            }

            @Override
            public void execute() {
                // Nothing needed yet
            }
        };

        engine.schedule(laterEvent);
        engine.schedule(earlierEvent);

        engine.runNextEvent();

        assertEquals(
                Duration.ofHours(2),
                engine.currentTime()
        );
    }

    @Test
    void pendingEventCountTracksQueuedEvents() {

        SimulationEngine engine = new SimulationEngine();

        SimulationEvent firstEvent = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(1);
            }

            @Override
            public void execute() {
            }
        };

        SimulationEvent secondEvent = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(2);
            }

            @Override
            public void execute() {
            }
        };

        engine.schedule(firstEvent);
        engine.schedule(secondEvent);

        assertEquals(2, engine.pendingEventCount());

        engine.runNextEvent();

        assertEquals(1, engine.pendingEventCount());
    }

    @Test
    void runUntilStopsBeforeLaterEvents() {

        SimulationEngine engine = new SimulationEngine();

        SimulationEvent firstEvent = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(1);
            }

            @Override
            public void execute() {
            }
        };

        SimulationEvent secondEvent = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(3);
            }

            @Override
            public void execute() {
            }
        };

        SimulationEvent laterEvent = new SimulationEvent() {

            @Override
            public Duration scheduledTime() {
                return Duration.ofHours(7);
            }

            @Override
            public void execute() {
            }
        };

        engine.schedule(firstEvent);
        engine.schedule(secondEvent);
        engine.schedule(laterEvent);

        engine.runUntil(Duration.ofHours(5));

        assertEquals(
                Duration.ofHours(5),
                engine.currentTime()
        );

        assertEquals(
                1,
                engine.pendingEventCount()
        );
    }
}