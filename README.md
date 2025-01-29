# Structure-Compose-UI

## Overview

This project is an Android application built using Kotlin. It follows the MVVM (Model-View-ViewModel) architecture and Clean Architecture principles while incorporating Jetpack components.

## Project Structure

The project is organized into different layers to maintain a clean architecture and separate concerns:

### 1. App Layer (app package)
- **App**: The main application class that initializes dependencies and configurations.

### 2. Data Layer (data package)
Responsible for handling data from multiple sources (local and remote) and mapping it to domain models.

#### Local (local package):
- **Entity**: Defines database entities for Room persistence.

#### Remote (remote package):
- **API**: Defines Retrofit API service interfaces.

#### Model (model package):
- **Request**: DTOs for API request payloads.
- **Response**: DTOs for API response payloads.
- **Mapper**: Handles conversion between data models and domain models.
- **Repository**: Implements domain repository interfaces to fetch and store data.

### 3. Dependency Injection Layer (di package)
- **DI Modules**: Provides dependency injection setup using Hilt.

### 4. Domain Layer (domain package)
Defines business logic, independent of data sources or UI.

#### Model (model package):
- **Request**: Models for input data processing.
- **Response**: Models for structured output data.
- **Repository**: Abstract repository interfaces to decouple data from business logic.
- **Use Case**: Encapsulates business logic for specific application actions.

### 5. Presentation Layer (presentation package)
Handles UI, user interactions, and ViewModel-based state management.

#### UI (ui package):
- **Components**: Reusable UI elements.
- **Dialogs**: Custom modal dialogs.
- **Navigation**: Manages screen navigation.
- **Screen**: Activity and Fragment implementations.
- **Theme**: Defines colors, typography, and styles.

### 6. Utils Layer (utils package)
Contains helper functions and common utilities such as:
- **Network Utils**: Checks network connectivity.
- **Date Utils**: Handles date formatting.
- **Extensions**: Kotlin extensions for common operations.

### 7. Main Entry Point (MainActivity.kt)
The primary activity that initializes the app and manages navigation.

## Setup Instructions

### Prerequisites
- Android Studio (latest version)
- Kotlin 1.8+
- Gradle 8+

### Clone the Repository

```bash
git clone https://github.com/your-repo/project.git
cd project
