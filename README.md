# Refaccionaria El Moral - Android App

A WebView-based Android application for Refaccionaria El Moral's e-commerce store.

## Overview

This is a native Android app that wraps the Refaccionaria El Moral website (https://refaccionariaelmoral.sicarx.shop/) in a WebView, providing:

- Native Android app experience
- Google Play Store distribution
- Hardware back button support
- Optimized mobile performance
- Full access to website features

## Features

✅ WebView wrapper for seamless web-to-app experience
✅ JavaScript enabled for full functionality
✅ Local storage and database support
✅ Back button navigation
✅ Device permissions for camera and location (if needed)
✅ Mixed content support for HTTP/HTTPS resources
✅ Optimized for Android 7.0+ (API 24+)

## Requirements

- Android Studio 2023.1 or later
- Java 8+
- Gradle 8.0+
- Android SDK 34 (API 34)
- Minimum Android version: 7.0 (API 24)
- Target Android version: 14 (API 34)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/edlalin/refaccionaria-el-moral-app.git
cd refaccionaria-el-moral-app
```

### 2. Open in Android Studio

1. Open Android Studio
2. Click "Open" and select the cloned project folder
3. Wait for Gradle sync to complete

### 3. Build and Run

```bash
# Build the app
./gradlew build

# Install on connected device or emulator
./gradlew installDebug

# Run the app
adb shell am start -n com.refaccionariaelmoral.app/.MainActivity
```

### 4. Create a Signed APK/AAB for Google Play Store

```bash
# Build release AAB
./gradlew bundleRelease

# Build release APK
./gradlew assembleRelease
```

## Project Structure

```
refaccionaria-el-moral-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/refaccionariaelmoral/app/
│   │   │   │   ├── MainActivity.kt              # Main activity with WebView setup
│   │   │   │   └── RefaccionariaWebViewClient.kt # Custom WebView client
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml        # Main layout with WebView
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml              # String resources
│   │   │   │   │   ├── themes.xml               # App theme
│   │   │   │   │   └── colors.xml               # Color palette
│   │   │   │   └── xml/
│   │   │   │       ├── data_extraction_rules.xml
│   │   │   │       └── backup_rules.xml
│   │   │   └── AndroidManifest.xml              # App manifest
│   │   └── test/                                # Unit tests
│   ├── build.gradle.kts                         # App-level Gradle config
│   └── proguard-rules.pro                       # ProGuard rules for optimization
├── build.gradle.kts                             # Project-level Gradle config
├── settings.gradle.kts                          # Gradle settings
├── .gitignore                                   # Git ignore rules
└── README.md                                    # This file
```

## Customization

### Change App Icon

1. Right-click on `app/src/main/res/`
2. Select "New" → "Image Asset"
3. Choose "Icon Type" and select your image
4. Replace the default launcher icon

### Change App Colors

Edit `app/src/main/res/values/colors.xml`:

```xml
<color name="primary_color">#YOUR_COLOR</color>
<color name="primary_dark_color">#YOUR_DARK_COLOR</color>
```

### Change App Name

Edit `app/src/main/res/values/strings.xml`:

```xml
<string name="app_name">Your App Name</string>
```

### Configure Website URL

Edit `app/src/main/java/com/refaccionariaelmoral/app/MainActivity.kt`:

```kotlin
webView.loadUrl("https://your-website.com/")
```

## Publishing to Google Play Store

### 1. Create a Signed Release APK/AAB

```bash
./gradlew bundleRelease
```

### 2. Generate Keystore

In Android Studio:
- Go to Build → Generate Signed Bundle/APK
- Create a new keystore with a strong password
- Save the keystore file securely

### 3. Upload to Google Play Console

1. Create a project at [Google Play Console](https://play.google.com/console)
2. Upload the signed AAB file
3. Fill in app details, screenshots, and description
4. Submit for review

## Permissions

The app requests the following permissions:

- `INTERNET` - Access to load website content
- `ACCESS_NETWORK_STATE` - Check network connectivity
- `CAMERA` - For camera functionality in the website
- `ACCESS_FINE_LOCATION` - For location-based features
- `ACCESS_COARSE_LOCATION` - For approximate location

## Troubleshooting

### Website not loading
- Check internet connection
- Verify URL is correct and accessible
- Check WebView settings in `MainActivity.kt`

### JavaScript not working
- Ensure `javaScriptEnabled = true` in WebSettings
- Check browser console for JavaScript errors

### Back button not working
- Verify `onBackPressed()` override in MainActivity
- Check WebView history stack

## Building APK vs AAB

- **AAB (Android App Bundle)**: Recommended for Google Play Store. Smaller download size.
- **APK (Android Package)**: For direct distribution or testing. Works on any Android device.

## Performance Optimization

The app includes:
- ProGuard rules to minimize APK size
- WebView optimization for memory usage
- Hardware acceleration enabled
- Image loading optimization

## Security Considerations

- ✅ HTTPS enforced for website loading
- ✅ Data extraction rules configured
- ✅ Backup rules configured
- ⚠️ Review permissions before production
- ⚠️ Consider implementing certificate pinning for sensitive data

## Support

For issues or questions:
1. Check the troubleshooting section
2. Review Android documentation
3. Check WebView documentation
4. Create an issue on GitHub

## License

This project is provided as-is for use with Refaccionaria El Moral.

## Version History

### v1.0.0
- Initial release
- WebView wrapper implementation
- Basic navigation support
- Android 7.0+ support
