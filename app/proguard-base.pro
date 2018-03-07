# http://drakeet.me/android-advanced-proguard-and-security/
-ignorewarnings
-keep public class * extends android.os.Binder
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}

# v7
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

# log
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int d(...);
    public static int w(...);
    public static int v(...);
    public static int i(...);
}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep class org.ocpsoft.prettytime.** { *; }

# Fabric
#-keep public class * extends java.lang.Exception
#-keepattributes *Annotation*
#-keep class com.crashlytics.** { *; }
#-dontwarn com.crashlytics.**
#-keep class io.fabric.** { *; }

-renamesourcefileattribute Proguard
-keepattributes SourceFile,LineNumberTable

# 混淆字典
#-obfuscationdictionary dictionary-drakeet.txt
#-classobfuscationdictionary dictionary-drakeet.txt
#-packageobfuscationdictionary dictionary-drakeet.txt

# 把代码以及所使用到的各种第三方库代码统统移动到同一个包下
-repackageclasses 'com.mrtan.data'
-allowaccessmodification