object Versions {

  val androidMinSdk = 21
  val androidTargetSdk = 30
  val androidCompileSdk = 30

  val kotlin = "1.4.32"

  val material = "1.2.1"
  val constraint = "2.0.4"
  val appCompat = "1.2.0"
  val navComp = "2.3.5"

  val gradle = "4.0.1"
  val test = "1.3.0"

  val multidex = "2.0.1"

  val mvi = "2.0.2"
  val dagger = "2.17"
  val glide = "4.11.0"
  val http = "3.11.0"
  val exo = "2.9.3"
  val moshi = "1.9.3"

  val work = "2.5.0"

  val firebase = "21.1.1"
  val firebase_app_dist = "2.1.1"
  val firebase_messaging = "21.1.0"
  val firenase_analytics = "18.0.3"
  val firebase_crashlytcis = "2.5.2"
  val crashlytics = "17.4.1"

  val google_services = "4.3.5"

  val swipe_ui = "1.1.0"

  object App {
    val packageName = "io.fs.marvel"

    val major = 1
    val minor = 0
    val build = 0
  }
}

object Deps {

  object Plugins {

    val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val firebase_app_dist = "com.google.firebase:firebase-appdistribution-gradle:${Versions.firebase_app_dist}"
    val firebase_crashlytics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebase_crashlytcis}"
    val google = "com.google.gms:google-services:${Versions.google_services}"
    val nav_safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navComp}"
  }

  object AndroidX {
    val material = "com.google.android.material:material:${Versions.material}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val constraintSolver = "androidx.constraintlayout:constraintlayout-solver:${Versions.constraint}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    val swipe_ui = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_ui}"

    val mvi = "org.fs.architecture:mvi:${Versions.mvi}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val http = "com.squareup.okhttp3:logging-interceptor:${Versions.http}"

    val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navComp}"
    val nav_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navComp}"

    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val exo_core = "com.google.android.exoplayer:exoplayer:${Versions.exo}"
    val exo_ui = "com.google.android.exoplayer:exoplayer-ui:${Versions.exo}"
    val exo_http = "com.google.android.exoplayer:extension-okhttp:${Versions.exo}"

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    val work_runtine = "androidx.work:work-runtime:${Versions.work}"
    val work_rxjava2 = "androidx.work:work-rxjava2:${Versions.work}"

    val firebase_messaging = "com.google.firebase:firebase-messaging:${Versions.firebase_messaging}"
    val firebase_analytics = "com.google.firebase:firebase-analytics:${Versions.firenase_analytics}"
    val firebase_crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.crashlytics}"
  }

  object Kapt {
    val moshi = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    val dagger = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val glide = "com.github.bumptech.glide:compiler:${Versions.glide}"
  }

  object Test {
    val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val core = "androidx.test:core:${Versions.test}"
  }
}
