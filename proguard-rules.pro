# Proguard rules for Business Card Generator

# Keep the MainActivity and all activities
-keep class com.example.businesscardgenerator.ui.** { *; }

# Keep data classes
-keep class com.example.businesscardgenerator.data.** { *; }

# Keep utility classes
-keep class com.example.businesscardgenerator.utils.** { *; }

# ZXing library
-keep class com.google.zxing.** { *; }
-keepclassmembers class com.google.zxing.** { *; }

# Keep Kotlin metadata
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# Keep data classes properties
-keepclassmembers class com.example.businesscardgenerator.data.** {
    public <methods>;
    public <fields>;
}

# AndroidX
-keep class androidx.** { *; }
-keepnames class androidx.** { *; }

# Coroutines
-keepclassmembernames class kotlinx.coroutines.** {
    volatile <fields>;
}

# Generic signature handling
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
