# LeafLogic Android Architecture Documentation

## Project Overview

LeafLogic is a modern Android health and food tracking application built using the latest Android development best practices and technologies. The app leverages cloud-based Large Language Models (LLMs) to provide personalized nutrition advice and help users develop better eating habits.

## Architecture

### MVVM + Clean Architecture

The application follows the Model-View-ViewModel (MVVM) architecture pattern combined with Clean Architecture principles:

```
┌─────────────────┐
│   Presentation  │ ← UI Layer (Composables, ViewModels)
├─────────────────┤
│     Domain      │ ← Business Logic (Use Cases, Models)
├─────────────────┤
│      Data       │ ← Data Layer (Repositories, APIs, Database)
└─────────────────┘
```

### Technology Stack

#### Core Android
- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern declarative UI framework
- **Material Design 3**: Latest design system with dynamic theming
- **Android Architecture Components**: ViewModel, LiveData, Navigation

#### Dependency Injection
- **Hilt**: Dependency injection framework
- **Dagger**: Underlying DI framework

#### Database
- **Room**: Local SQLite database abstraction
- **Flow**: Reactive data streams
- **Coroutines**: Asynchronous programming

#### Networking
- **Retrofit**: HTTP client for API communication
- **OkHttp**: HTTP client with logging and interceptors
- **Gson**: JSON serialization/deserialization

#### Cloud Integration
- **Cloud LLM APIs**: Integration with various LLM providers
- **Retrofit**: RESTful API communication
- **Coroutines**: Asynchronous network calls

## Project Structure

```
app/src/main/java/com/midoriai/leaflogic/
├── ui/
│   ├── theme/              # Material Design 3 theming
│   │   ├── Color.kt        # Color palette
│   │   ├── Theme.kt        # Theme configuration
│   │   └── Type.kt         # Typography scale
│   ├── navigation/         # Navigation setup
│   │   └── LeafLogicNavigation.kt
│   ├── screens/            # Main app screens
│   │   ├── HomeScreen.kt
│   │   ├── FoodTrackingScreen.kt
│   │   ├── HealthScreen.kt
│   │   └── ChatScreen.kt
│   ├── viewmodel/          # ViewModels for MVVM
│   │   └── ChatViewModel.kt
│   └── components/         # Reusable UI components
├── data/
│   ├── api/                # API interfaces and models
│   │   ├── ChatModels.kt
│   │   └── NutritionApiService.kt
│   ├── repository/         # Data repositories
│   │   └── ChatRepository.kt
│   └── local/              # Local database
│       ├── entity/         # Room entities
│       ├── dao/            # Data Access Objects
│       └── database/       # Database configuration
├── domain/                 # Business logic (future)
└── di/                     # Dependency injection modules
    ├── NetworkModule.kt
    └── DatabaseModule.kt
```

## Key Features Implementation

### 1. Home Dashboard
- **Composable**: `HomeScreen.kt`
- **Features**: Welcome card, quick stats, recent activities
- **Components**: Custom cards with Material Design 3 styling

### 2. Food Tracking
- **Composable**: `FoodTrackingScreen.kt`
- **Features**: Food search, calorie tracking, meal categorization
- **Database**: `FoodEntryEntity` for local storage

### 3. Health Metrics
- **Composable**: `HealthScreen.kt`
- **Features**: Weight tracking, step counting, progress indicators
- **Database**: `HealthMetricsEntity` for metrics storage

### 4. AI Chat Assistant
- **Composable**: `ChatScreen.kt`
- **ViewModel**: `ChatViewModel.kt`
- **Repository**: `ChatRepository.kt`
- **Features**: Real-time chat, AI responses, conversation history

## Data Flow

### Chat Feature Example
1. User types message in `ChatScreen`
2. Message sent to `ChatViewModel.sendMessage()`
3. ViewModel calls `ChatRepository.sendMessage()`
4. Repository makes API call via `NutritionApiService`
5. Response flows back through layers to update UI

### Local Data Persistence
1. User actions trigger ViewModel methods
2. ViewModel calls Repository methods
3. Repository uses DAOs to interact with Room database
4. Data flows back as `Flow<T>` for reactive UI updates

## Design System

### Material Design 3 Implementation
- **Dynamic Colors**: Adapts to user's wallpaper (Android 12+)
- **Color Tokens**: Semantic color naming (primary, secondary, tertiary)
- **Typography Scale**: Following Material Design 3 type system
- **Component Library**: Consistent Material 3 components

### Theme Configuration
```kotlin
// Color palette specifically designed for health/nutrition app
val LeafGreen40 = Color(0xFF4CAF50)  // Primary color
val AccentOrange40 = Color(0xFFFF9800)  // Secondary color
val NutritionalBlue40 = Color(0xFF2196F3)  // Tertiary color
```

### Accessibility
- **Content Descriptions**: Proper accessibility labels
- **Touch Targets**: Minimum 48dp touch targets
- **Color Contrast**: WCAG AA compliance
- **Text Scaling**: Supports system font size changes

## Database Schema

### Room Database Tables
1. **food_entries**: Food logging data
2. **health_metrics**: Daily health measurements
3. **chat_messages**: Conversation history
4. **user_goals**: User preferences and targets

### Entity Relationships
- One-to-many: User → Food Entries
- One-to-many: User → Health Metrics
- One-to-many: User → Chat Messages

## API Integration

### Cloud LLM Integration
The app is designed to work with various LLM providers:
- **OpenAI GPT**: For advanced conversational AI
- **Anthropic Claude**: For nutrition-focused responses
- **Custom LLM APIs**: Extensible architecture

### API Models
- **ChatRequest/Response**: Core chat functionality
- **NutritionContext**: User context for personalized responses
- **FoodAnalysis**: Food recognition and nutritional analysis

## Testing Strategy

### Unit Testing
- **ViewModels**: Business logic testing
- **Repositories**: Data layer testing
- **Use Cases**: Domain logic testing

### UI Testing
- **Compose Testing**: UI component testing
- **Integration Tests**: End-to-end user flows
- **Accessibility Testing**: Screen reader compatibility

## Performance Considerations

### Optimization Techniques
- **Lazy Loading**: Efficient list rendering with `LazyColumn`
- **State Management**: Proper Compose state handling
- **Memory Management**: Lifecycle-aware ViewModels
- **Background Processing**: Coroutines for async operations

### Battery Optimization
- **Minimal Background Work**: Efficient API calls
- **Smart Sync**: Intelligent data synchronization
- **Doze Mode Compatibility**: Android battery optimization compliance

## Security Considerations

### Data Protection
- **Local Encryption**: Sensitive data encryption
- **API Security**: Secure token management
- **Privacy Compliance**: GDPR/CCPA compliance ready

### Network Security
- **Certificate Pinning**: API communication security
- **Request Signing**: API request authentication
- **Data Validation**: Input sanitization and validation

## Deployment

### Build Variants
- **Debug**: Development builds with logging
- **Release**: Production builds with optimization
- **Staging**: Testing builds with staging APIs

### CI/CD Pipeline
- **GitHub Actions**: Automated testing and building
- **Code Quality**: Linting and static analysis
- **Security Scanning**: Vulnerability detection

## Future Enhancements

### Planned Features
- [ ] Offline Mode: Local AI model integration
- [ ] Wearable Support: Android Wear integration
- [ ] Social Features: Community and sharing
- [ ] Advanced Analytics: ML-powered insights
- [ ] Voice Interface: Speech recognition and synthesis
- [ ] Barcode Scanning: Food identification via camera
- [ ] Integration APIs: Connect with fitness trackers

### Architecture Evolution
- [ ] Modularization: Feature-based modules
- [ ] Clean Architecture: Use cases and domain layer
- [ ] Compose Multiplatform: Shared UI components
- [ ] GraphQL: Advanced API querying capabilities

## Development Setup

### Prerequisites
- Android Studio Hedgehog | 2023.1.1+
- JDK 17+
- Android SDK API 34
- Minimum Android version: API 24 (Android 7.0)

### Configuration
1. Clone repository
2. Add API keys to `local.properties`
3. Sync Gradle files
4. Run on emulator or device

### Environment Variables
```properties
# local.properties
NUTRITION_API_KEY=your_api_key_here
NUTRITION_API_BASE_URL=https://api.your-provider.com/v1/
```

This architecture provides a solid foundation for a scalable, maintainable, and user-friendly health tracking application with AI-powered nutrition guidance.