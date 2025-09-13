# LeafLogic - LLM-Powered Health & Food Tracking App

LeafLogic is a modern Android health and food tracking app that leverages cloud-based Large Language Models (LLMs) to provide personalized nutrition advice and help users develop better eating habits.

## Features

### 🏠 Home Dashboard
- Welcome screen with personalized greetings
- Daily overview of calories and water intake
- Quick stats cards with progress tracking
- Recent activities feed
- Quick log floating action button

### 🥗 Food Tracking
- Comprehensive food search and logging
- Daily calorie tracking with visual progress
- Meal categorization (breakfast, lunch, dinner, snacks)
- Time-stamped food entries
- Nutritional information display

### 💪 Health Metrics
- Weight tracking with goal setting
- Step counting with daily targets
- Weekly progress summaries
- Health insights and recommendations
- Visual progress indicators

### 🤖 LLM Nutrition Assistant
- Real-time chat with LLM nutrition expert
- Personalized dietary recommendations
- Nutritional question answering
- Recipe suggestions
- Health tips and insights

## Technology Stack

### Architecture
- **MVVM Architecture** with Jetpack Compose
- **Dependency Injection** with Hilt
- **Navigation** with Jetpack Navigation Compose
- **Local Storage** with Room Database
- **Preferences** with DataStore

### UI/UX
- **Material Design 3** (Material You)
- **Jetpack Compose** for modern UI
- **Dynamic Color** support (Android 12+)
- **Responsive Design** for different screen sizes
- **Accessibility** support built-in

### Modern Android Development
- **Kotlin** as primary language
- **Coroutines** for asynchronous programming
- **Flow** for reactive programming
- **Modern Gradle** configuration
- **Version Catalogs** for dependency management

### Cloud Integration
- **Retrofit** for API communication
- **OkHttp** with logging interceptor
- **Kotlin Serialization** for JSON handling
- **Cloud LLM APIs** for LLM conversations

### Security & Privacy
- **Database Encryption** with SQLCipher for sensitive health data
- **Biometric Authentication** using fingerprint/face recognition
- **Photo-Enhanced Security** leveraging device characteristics and photo metadata
- **Encrypted Shared Preferences** for sensitive app settings
- **Device Fingerprinting** for additional security layer

## Project Structure

```
app/
├── src/main/java/com/midoriai/leaflogic/
│   ├── ui/
│   │   ├── theme/          # Material Design 3 theming
│   │   ├── navigation/     # Navigation setup
│   │   ├── screens/        # Main app screens
│   │   └── components/     # Reusable UI components
│   ├── data/              # Data layer (repositories, APIs)
│   ├── domain/            # Business logic
│   └── di/                # Dependency injection modules
├── res/
│   ├── values/            # Strings, colors, themes
│   ├── drawable/          # Vector drawables
│   └── mipmap-*/          # App icons
└── AndroidManifest.xml
```

## Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 17 or newer
- Android SDK API 34
- Minimum Android version: API 24 (Android 7.0)

### Setup
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or device

### Configuration
- Add your cloud LLM API keys to local.properties
- Configure network security settings if needed
- Set up proper signing configurations for release builds

## Design Principles

### Material Design 3
- **Dynamic Color**: Adapts to user's wallpaper (Android 12+)
- **Color Harmony**: Consistent color system across the app
- **Typography Scale**: Following Material Design 3 type system
- **Component Consistency**: Using Material 3 components throughout

### Accessibility
- **Screen Reader Support**: Proper content descriptions
- **Touch Targets**: Minimum 48dp touch targets
- **Color Contrast**: WCAG AA compliance
- **Text Scaling**: Supports system font size changes

### Performance
- **Lazy Loading**: Efficient list rendering with LazyColumn
- **Image Optimization**: Proper image loading with Coil
- **Memory Management**: Efficient ViewModels and data handling
- **Battery Optimization**: Minimal background processing

### Data Security
- **End-to-End Encryption**: All sensitive data encrypted using Android Keystore
- **Biometric Protection**: Secure access using fingerprint or face recognition
- **Photo-Based Key Generation**: Enhanced security using device characteristics and photo metadata
- **Database Encryption**: SQLCipher integration for encrypted local storage
- **Privacy by Design**: No sensitive data stored in plaintext

## Contributing

Please read our [AGENTS.md](AGENTS.md) file for detailed contribution guidelines and development practices.

### Development Modes
The project follows the Midori-AI contribution framework with different modes:
- **Coder Mode**: For implementing features and bug fixes
- **Reviewer Mode**: For code review and quality assurance
- **Task Master Mode**: For project planning and task management

## License

This project is part of the Midori-AI ecosystem. Please refer to the main repository for licensing information.

## Architecture Decisions

### Why Jetpack Compose?
- Modern declarative UI framework
- Better performance and developer experience
- Built-in Material Design 3 support
- Seamless integration with modern Android development

### Why Material Design 3?
- Latest design system from Google
- Dynamic theming capabilities
- Improved accessibility features
- Future-proof design language

### Why Cloud LLMs?
- Access to latest LLM capabilities
- No need for on-device model storage
- Better performance and accuracy
- Regular model updates without app updates

## Future Enhancements

- [ ] Offline mode with local data sync
- [ ] Wearable app integration
- [ ] Barcode scanning for food items
- [ ] Social features and community
- [ ] Advanced analytics and insights
- [ ] Integration with health platforms
- [ ] Voice commands and speech recognition
- [ ] Multi-language support
