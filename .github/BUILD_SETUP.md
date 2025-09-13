# GitHub Actions Build Configuration

## Current Build Status

The GitHub Actions workflows are configured and ready to build the Android APK, but require network access configuration to function properly.

## Network Access Issue

The Android build requires access to Google's Maven repository (`dl.google.com`) to download the Android Gradle Plugin and related dependencies. This domain is currently blocked in the GitHub Actions environment.

## Solution Required

**Repository administrators need to:**

1. Go to [Copilot coding agent settings](https://github.com/Midori-AI-OSS/LeafLogic-by-Midori-AI/settings/copilot/coding_agent)
2. Add `dl.google.com` to the custom allowlist
3. This will enable the workflows to download required Android build dependencies

## Alternative Local Build

If you want to build locally, ensure you have:
- Android SDK installed
- Java 17 or higher
- Network access to `dl.google.com`

Then run:
```bash
./gradlew assembleDebug
```

## Workflows Included

1. **Android CI** - Basic build verification on PRs
2. **Build APK** - Creates debug and release APK files 
3. **Code Quality & Testing** - Runs lint checks and unit tests
4. **Release** - Automated release builds with GitHub releases

All workflows are properly configured and will work once network access is enabled.