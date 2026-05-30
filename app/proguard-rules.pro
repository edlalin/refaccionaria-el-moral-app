# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# signingConfigs blocks in the build.gradle file.

-keep class * extends android.webkit.WebViewClient { *; }
-keep class * extends android.webkit.WebChromeClient { *; }
-keep class android.webkit.** { *; }

# If your project uses WebView with JS, uncomment the following
-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
