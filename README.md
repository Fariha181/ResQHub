# 🚑 ResQHub - Enterprise Emergency Response & Incident Management System

**ResQHub** is a high-performance, enterprise-grade emergency response backend system built with **Java 17** and **Spring Boot 3.x**. It leverages advanced Software Design Patterns to provide real-time incident handling, dynamic route optimization, hospital bed tracking, and police SOS dispatch in critical situations.

---

## 🛠️ Tech Stack & Infrastructure

- **Language:** Java 17+
- **Framework:** Spring Boot 3.x, Spring Data JPA
- **Database:** MySQL 8.0
- **Monitoring:** Spring Boot Actuator
- **Testing:** JUnit 5, Mockito
- **API Testing:** Thunder Client / Postman

---

## 📐 Software Design Patterns Implemented

1. **Abstract Factory Pattern (Responder Allocation):**
   Decouples object creation for emergency responders (Ambulance, Fire Truck, Police Patrol) based on incident categories.

2. **Observer Pattern (Live Incident Broadcast):**
   Implements a Publish-Subscribe mechanism to send instant real-time alerts to registered agencies when an emergency occurs.

3. **Singleton Pattern (Thread-Safe Central System Logger):**
   Ensures a single, thread-safe global instance (`SystemLogger`) for centralized logging without memory overhead.

4. **Strategy Pattern (Dynamic Route Optimization):**
   Enables runtime algorithm swapping (`FastestRouteStrategy`, `ShortestRouteStrategy`) based on live traffic and urgency.

5. **Command Pattern (Undo/Rollback Dispatch):**
   Encapsulates dispatch requests into command objects, allowing easy execution and rollback/undo capabilities for fake/cancelled calls.

---

## 🚀 Core Features & Modules

- **Resource Concurrency Control:** Thread-safe reservation system preventing race conditions during simultaneous emergency resource allocation.
- **Hospital Bed Tracking System:** Real-time registration, login, and dynamic updates for available ICU and General beds.
- **Police SOS Alert Module:** Emergency alert dispatching with nearest police station matching and Central Control (999) failover routing.
- **Production Health Monitoring:** Integrated Actuator endpoints (`/actuator/health`, `/actuator/metrics`) for live server and DB health status.
- **Automated Unit Testing:** Full JUnit 5 test suite covering Strategy execution and Concurrency logic.

---

## 🔌 API Endpoints Summary

### 🚑 Emergency Dispatch & Routes
- `POST /api/dispatch/send?responderType={type}` - Dispatch responder (Abstract Factory)
- `GET /api/routes/dispatch?strategy={type}` - Route calculation (Strategy Pattern)

### 🏥 Hospital Management
- `POST /api/hospitals/register` - Register a new hospital
- `POST /api/hospitals/login` - Hospital authentication
- `PUT /api/hospitals/update-beds/{id}` - Update ICU and General beds count
- `GET /api/hospitals/all` - List all hospitals with live bed counts

### 🚓 Police SOS Module
- `POST /api/police/add` - Enlist new police station
- `POST /api/police/sos-alert?area={area}&description={desc}` - Trigger Police SOS alert
- `GET /api/police/all` - Fetch all police stations

---

## 🧪 Running Unit Tests

To run the automated JUnit 5 test suite:
```bash
./mvnw test
