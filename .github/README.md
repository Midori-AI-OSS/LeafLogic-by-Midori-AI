# LeafLogic CI/CD Documentation

This directory contains GitHub Actions workflows for automated building, testing, and deployment of the LeafLogic Android application.

## Workflows

### 1. Android CI (`ci.yml`)
**Triggers:** Push and PR to main/develop branches
**Purpose:** Continuous integration with build verification and testing
**Features:**
- Builds the Android app using Gradle
- Runs unit tests
- Executes instrumented tests on Android emulator
- Caches Gradle dependencies for faster builds

### 2. Build APK (`build-apk.yml`)
**Triggers:** Push/PR to main/develop branches, manual workflow dispatch
**Purpose:** Generates APK files for testing and distribution
**Features:**
- Builds both Debug and Release APKs
- Uploads APK artifacts for download
- Automatically comments on PRs with download links
- Provides installation instructions

### 3. Code Quality & Testing (`test.yml`)
**Triggers:** Push and PR to main/develop branches
**Purpose:** Comprehensive code quality analysis and testing
**Features:**
- Kotlin code linting (ktlint)
- Android lint checks
- Unit test execution with coverage reporting
- Uploads detailed test and lint reports
- Comments test results on PRs

### 4. Release Build (`release.yml`)
**Triggers:** Git tags (v*), manual workflow dispatch
**Purpose:** Production release builds with proper versioning
**Features:**
- Builds release APK and AAB (Android App Bundle)
- Creates GitHub releases with detailed changelog
- Uploads signed artifacts for distribution
- Suitable for Google Play Store deployment

## Artifacts

All workflows generate downloadable artifacts:

- **APK Files**: Debug and release builds for direct installation
- **AAB Files**: Android App Bundles for Play Store distribution
- **Test Reports**: Detailed test execution and coverage reports
- **Lint Reports**: Code quality analysis results

## Security Considerations

- No signing keys are stored in the repository
- Sensitive build configurations use GitHub Secrets
- All builds are reproducible and traceable
- Artifacts are automatically cleaned up after retention period

## Usage

### For Developers
1. **Push commits**: Automatically triggers CI builds and tests
2. **Create PRs**: Generates APKs for testing and quality reports
3. **Tag releases**: Creates production builds with proper versioning

### For Testers
1. Navigate to Actions tab in GitHub
2. Find the latest successful build
3. Download APK artifacts
4. Install on Android device for testing

### For Releases
1. Create a git tag: `git tag v1.0.0 && git push origin v1.0.0`
2. Release workflow automatically builds and publishes
3. Download production APK/AAB from GitHub releases

## Android Development Requirements

- **JDK 17**: Required for Kotlin and Android development
- **Android SDK 34**: Target and compile SDK version
- **Minimum SDK 24**: Android 7.0+ support
- **Gradle 8.4**: Build system version

## Key Features Tested

- ✅ **LLM Integration**: Cloud nutrition assistant functionality
- ✅ **Security**: Database encryption and biometric authentication
- ✅ **UI/UX**: Material Design 3 compliance
- ✅ **Performance**: Efficient data handling and smooth animations
- ✅ **Compatibility**: Android 7.0+ device support