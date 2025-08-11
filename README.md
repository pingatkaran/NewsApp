# News App Assignment

A modern Android news application built with Jetpack Compose and Clean Architecture principles. This app fetches news articles from NewsAPI, allows users to browse them by category, and provides detailed article views with offline caching capabilities.

## 📸 Screenshots

| Light Mode | Dark Mode |
|------------|-----------|
| <img src="https://raw.githubusercontent.com/pingatkaran/NewsApp/refs/heads/main/media/WhatsApp%20Image%202025-08-11%20at%2015.52.17.jpeg" width="250"/> | <img src="https://raw.githubusercontent.com/pingatkaran/NewsApp/refs/heads/main/media/WhatsApp%20Image%202025-08-11%20at%2015.52.16.jpeg" width="250"/> |
| <img src="https://raw.githubusercontent.com/pingatkaran/NewsApp/refs/heads/main/media/WhatsApp%20Image%202025-08-11%20at%2015.52.17%20(1).jpeg" width="250"/> | <img src="https://raw.githubusercontent.com/pingatkaran/NewsApp/refs/heads/main/media/WhatsApp%20Image%202025-08-11%20at%2015.52.16%20(1).jpeg" width="250"/> |

## 🎥 Video Walkthrough

https://github.com/user-attachments/assets/1679d64f-1726-46c1-832d-c6b544dfa9bf

## ✨ Features

- **Browse News by Category**: Select from various categories to view relevant news articles
- **Article Details**: Tap on articles to view full details including images, authors, and content
- **Offline Caching**: Articles are cached locally for offline access
- **Clean & Modern UI**: Intuitive interface built with Jetpack Compose
- **Light/Dark Theme**: Automatic theme switching based on system settings
- **Real-time Updates**: Fresh content fetched from NewsAPI

## 🛠️ Built With

- **[Kotlin](https://kotlinlang.org/)** - Official programming language for Android development
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** - Modern toolkit for building native UI
- **[Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html)** - Asynchronous programming
- **[Hilt](https://dagger.dev/hilt/)** - Dependency injection framework
- **[Retrofit](https://square.github.io/retrofit/)** - Type-safe HTTP client
- **[Room](https://developer.android.com/training/data-storage/room)** - Local database storage
- **[Coil](https://coil-kt.github.io/coil/)** - Image loading library
- **[Jetpack Navigation](https://developer.android.com/guide/navigation)** - Navigation between screens
- **MVVM Pattern** - Model-View-ViewModel architectural pattern

## 🏗️ Architecture

This app follows **Clean Architecture** principles with a multi-module setup, organized into three main layers:

### 📊 Architecture Layers

- **Data Layer**: Handles data operations including repository implementations, local/remote data sources, and data mapping
- **Domain Layer**: Contains business logic, use cases, models, and repository interfaces (framework-independent)
- **Presentation Layer (UI)**: Manages UI components built with Jetpack Compose and ViewModels

### 📁 Module Structure

```
├── :app                 # Main application module integrating all components
├── :data               # Data layer with repository implementations and DAOs
├── :domain             # Domain layer with business logic and use cases
└── :feature_home       # Feature module for home screen and article details
```

## ⚙️ Setup and Installation

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24+
- NewsAPI account (free)

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/pingatkaran/NewsApp.git
   cd NewsAppAssigment
   ```

2. **Get NewsAPI Key**
   - Visit [NewsAPI](https://newsapi.org/) and create a free developer account
   - Copy your API key

3. **Configure API Key**
   - Open `data/build.gradle.kts`
   - Replace `"YOUR_API_KEY"` with your actual API key:
   ```kotlin
   buildConfigField("String", "API_KEY", "\"YOUR_ACTUAL_API_KEY\"")
   ```

4. **Build and Run**
   - Open the project in Android Studio
   - Sync Gradle files
   - Run the app on an emulator or physical device

## 🧪 Testing

The project includes comprehensive unit tests covering:

- **ViewModels**: `HomeViewModelTest` - Tests news fetching and display logic
- **Use Cases**: `GetNewsUseCaseTest` - Verifies use case interactions with repository
- **Repositories**: `NewsRepositoryImplTest` - Tests data fetching from remote and local sources
- **Mappers**: `MapperTest` - Validates mapping between DTOs, entities, and domain models

### Running Tests

```bash
./gradlew test
```

## 🤖 AI-Assisted Development

This project utilized AI tools to enhance development efficiency:

- **UX Pilot AI**: Generated UI/UX design ideas and user experience concepts
- **Claude AI**: Assisted in writing comprehensive unit tests and code documentation


## 📄 Project Link

Project Link: [https://github.com/pingatkaran/NewsApp](https://github.com/pingatkaran/NewsApp)
