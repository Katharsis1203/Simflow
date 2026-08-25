# Simflow

<p align="center">
  <img src="docs/assets/simflow-banner.png" alt="SimFlow banner" width="100%">
</p>

**SimFlow** is a configurable discrete-event simulation platform built with Java and Spring Boot.

The long-term goal is to model systems using reusable concepts such as **entities, resources, properties, processes, conditions and events**, allowing the same simulation engine to represent very different domains without hardcoding domain-specific behaviour into the core engine.

Examples could range from manufacturing and logistics systems to ecosystems, infrastructure, queueing systems, or experimental scenarios.

> **Status:** Early development. The core event scheduler is currently being implemented and tested.

## Concept

A SimFlow scenario describes:

* what exists in the simulation;
* what properties those things have;
* which resources are available;
* which processes can occur;
* the conditions required for those processes;
* how processes change simulation state;
* when events occur.

The engine then advances through simulated time by processing events in chronological order.

For example, an island scenario might define:

```text
Resource:
    Coconuts = 3000

Entity:
    Person
        hydration = 100

Process:
    Drink Coconut

Condition:
    hydration < 40
    coconuts > 0

Effects:
    coconuts -= 1
    hydration += 25
```

The simulation engine should not need to understand what a *person* or *coconut* is. They are simply scenario components governed by configurable rules.

The same underlying engine could instead represent:

```text
Factory:
Raw Material → Machine → Product

Hospital:
Patient → Queue → Treatment

Infrastructure:
Request → Server → Database

Ecosystem:
Plant → Herbivore → Predator
```

## Current Progress

The current implementation contains the beginnings of the discrete-event simulation engine.

Implemented so far:

* `SimulationEvent` abstraction;
* priority-based event scheduling;
* simulated time using Java `Duration`;
* execution of the next chronological event;
* execution up to a specified simulation time;
* pending event tracking;
* automated tests for core scheduling behaviour.

A simplified view of the current architecture is:

```text
SimulationEvent
       │
       ▼
 PriorityQueue
       │
       ▼
SimulationEngine
       │
       ├── current simulated time
       ├── run next event
       └── run until time
```

## Planned Architecture

SimFlow is intended to develop toward:

```text
Scenario
   │
   ├── Entities
   │      └── Properties
   │
   ├── Resources
   │
   └── Processes
          ├── Conditions
          ├── Inputs
          ├── Outputs
          └── Effects
               │
               ▼
        Simulation Engine
               │
         Event Priority Queue
               │
               ▼
          State Changes
               │
          ┌────┴────┐
          ▼         ▼
      Event Log   Metrics
```

The simulation engine should remain independent of the user interface and as independent as practical from Spring-specific infrastructure.

## Planned Demonstration Scenarios

To prove that the simulation model is genuinely reusable, the project is expected to include multiple substantially different scenarios.

### Island Resource Simulation

A simple population and renewable-resource system involving concepts such as:

* hydration;
* health;
* coconuts;
* water;
* resource consumption;
* renewable resource generation.

This scenario is intended to demonstrate recurring events, conditional processes and resource depletion/regeneration.

### Production System

A small manufacturing system involving:

* raw materials;
* machines;
* processing time;
* finished goods;
* machine failures;
* repair processes.

This scenario will exercise the same engine using a very different domain.

## Future Visualisation

A later goal is a lightweight **simulation replay viewer**.

Rather than building a game-style interface, the viewer would provide a simple graphical representation of simulation activity using basic nodes, connections and an event log.

```text
┌───────────────────────────────┬──────────────────────┐
│                               │ Event Log            │
│      ●           ●            │                      │
│            ───►               │ 08:00 Event started  │
│   ●                    ●      │ 08:05 Resource used  │
│                               │ 08:10 Process ended  │
│          ●                    │                      │
│                               │                      │
└───────────────────────────────┴──────────────────────┘

       Play   Pause   Step   Speed
```

The frontend would replay events produced by the simulation engine rather than controlling simulation logic itself.

## Technology

Current:

* Java 25
* Spring Boot
* Maven
* JUnit
* Git / GitHub

Planned:

* PostgreSQL
* Spring Data JPA
* React
* TypeScript
* Docker

Additional technologies will only be introduced where they solve a clear problem.

## Design Goals

SimFlow is being developed around several principles:

* **Domain independence** — core engine code should avoid concepts specific to one scenario.
* **Explainable behaviour** — it should be possible to determine why a state change occurred.
* **Reproducibility** — probabilistic simulations should eventually support recorded random seeds.
* **Testability** — the simulation engine should be testable without a frontend or database.
* **Correctness before complexity** — a small reliable engine is preferable to a large collection of incomplete features.
* **Separation of concerns** — simulation, persistence, API and presentation should remain clearly separated.

## Development Roadmap

Broadly, development is expected to progress through:

```text
Event scheduling                 ← current
        ↓
Simulation state
        ↓
Resources
        ↓
Entities and properties
        ↓
Conditions
        ↓
Processes
        ↓
Recurring / probabilistic events
        ↓
Event history and metrics
        ↓
Multiple demonstration scenarios
        ↓
Persistence and API
        ↓
Simulation replay viewer
```

The roadmap may change as the underlying simulation model develops.

## Building the Project

Requirements:

* Java 25
* Maven, or the included Maven Wrapper

Run the test suite:

```bash
./mvnw test
```

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

## Project Motivation

The primary challenge in SimFlow is not creating one specific simulation.

It is designing a **small, understandable set of abstractions that can describe many different simulated systems without turning the engine into a domain-specific application or an unrestricted scripting language**.

That design problem is the focus of the project.
