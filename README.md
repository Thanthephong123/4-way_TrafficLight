# 4-Way Traffic Light System with Pedestrian Lights

This project simulates a simple 4-way traffic light system with pedestrian lights. It includes mechanisms to ensure secure states to manage traffic flow safely.

## Overview

The 4-way traffic light system manages traffic flow at an intersection. It includes traffic lights for vehicles and pedestrian lights for pedestrians to cross safely. The system goes through different sequences to regulate traffic in a secure and efficient manner.

## Components

- **Traffic Lights**: Control vehicle traffic in all four directions (North, South, East, West).
- **Pedestrian Lights**: Allow pedestrians to cross safely when activated.
- **Control Mechanism**: Implements logic to ensure secure states and manage the sequencing of traffic lights.

## Secure States

Secure states are crucial for ensuring the safety of both vehicles and pedestrians. The system anticipates all possible states and implements mechanisms to ensure secure transitions between them. Some key secure states include:

1. **Green for Vehicles, Red for Pedestrians**: Allows vehicles to move while pedestrians are stopped.
2. **Yellow for Vehicles, Red for Pedestrians**: Indicates an upcoming change, preparing vehicles to slow down.
3. **Red for Vehicles, Green for Pedestrians**: Enables pedestrians to cross safely while vehicles are stopped.
