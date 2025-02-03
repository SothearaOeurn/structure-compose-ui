# Structure-Compose-UI

## Overview

This project is an Android application built using Kotlin, Jetpack Compose, and Clean Architecture principles. It follows the MVVM (Model-View-ViewModel) architecture to ensure separation of concerns, maintainability, and scalability.

---

## Project Structure

The project is organized into different layers to maintain Clean Architecture and modularity:

### 1. **App Layer (`app` package)**
   - **App**: The main application class responsible for initializing dependencies and configurations.

### 2. **Core Layer (`core` package)**
   - **Utils**: Contains utility functions and common tools used across the application.

### 3. **Data Layer (`data` package)**
   Handles data from multiple sources (local and remote) and maps it to domain models.
   - **Api**: Defines Retrofit API service interfaces for network communication.
   - **Datasource**: Manages data sources:
     - **Local**: Handles local storage (e.g., Room database, SharedPreferences).
     - **Remote**: Manages remote data sources (e.g., API calls).
   - **Error**: Defines custom error handling and exceptions.
   - **Mapper**: Converts data models to domain models and vice versa.
   - **Model**:
     - **Request**: Data Transfer Objects (DTOs) for API request payloads.
     - **Response**: DTOs for API response payloads.
   - **Repository**: Implements repository interfaces to fetch and store data.

### 4. **Dependency Injection Layer (`di` package)**
   - **DI Modules**: Provides dependency injection setup using Hilt to manage dependencies.

### 5. **Domain Layer (`domain` package)**
   Contains business logic, independent of data sources or UI.
   - **Model**:
     - **Request**: Models for input data processing.
     - **Response**: Models for structured output data.
   - **Repository**: Abstract repository interfaces to decouple data from business logic.
   - **UseCase**: Encapsulates business logic for specific application actions.

### 6. **Presentation Layer (`presentation` package)**
   Handles UI, user interactions, and ViewModel-based state management using Jetpack Compose.
   - **UI**:
     - **Components**: Reusable UI components for consistent design.
     - **Dialogs**: Custom modal dialogs for user interactions.
     - **Navigation**: Manages screen navigation using Compose Navigation.
     - **Screen**: Contains composable screens and their logic.
     - **Theme**: Defines colors, typography, and styles for the app.
   - **ViewModel**: Manages UI-related data and business logic.

### 7. **Utils Layer (`utils` package)**
   Contains helper functions and common utilities such as:
   - **Network Utils**: Checks network connectivity.
   - **Date Utils**: Handles date formatting and manipulation.
   - **Extensions**: Kotlin extensions for common operations.

### 8. **Main Entry Point (`MainActivity.kt`)**
   The primary activity that initializes the app and manages navigation.

---

## Setup Instructions

### Prerequisites
- Android Studio (latest version)
- Kotlin 1.8+
- Gradle 8+

### Clone the Repository
```bash
git clone https://github.com/SothearaOeurn/structure-compose-ui.git
cd project
