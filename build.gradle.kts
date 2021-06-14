buildscript {
  repositories {
    jcenter()
    google()
    mavenCentral()
  }
  dependencies {
    classpath(Deps.Plugins.gradle)
    classpath(Deps.Plugins.kotlin)
    classpath(Deps.Plugins.nav_safe_args)
    //classpath(Deps.Plugins.google)
    //classpath(Deps.Plugins.firebase_app_dist)
    //classpath(Deps.Plugins.firebase_crashlytics)
  }
}

allprojects {
  repositories {
    jcenter()
    google()
    mavenCentral()
  }
}