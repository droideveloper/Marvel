plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
  //id("com.google.gms.google-services")
  //id("com.google.firebase.appdistribution")
  //id("com.google.firebase.crashlytics")
}

android {
  compileSdkVersion(Versions.androidCompileSdk)
  defaultConfig {
    applicationId = Versions.App.packageName
    minSdkVersion(Versions.androidMinSdk)
    targetSdkVersion(Versions.androidTargetSdk)
    versionCode = buildVersionCode()
    versionName = buildVersionName()

    multiDexEnabled = true

    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    getByName("debug") {
      applicationIdSuffix = ".debug"

      val baseUrl = "https://gateway.marvel.com"

      buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
      buildConfigField("String", "API_PREFIX", "\"/public\"")
      buildConfigField("String", "API_VERSION", "\"/v1\"")
      buildConfigField("String", "API_KEY", "\"8c2cf3b0bbbcc3fc60b33f8ab975f4f8\"")

      resValue("string", "app_name", "Marvel DEV")

      isMinifyEnabled = false
    }

    getByName("release") {

      val baseUrl = "https://gateway.marvel.com"

      buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
      buildConfigField("String", "API_PREFIX", "\"/public\"")
      buildConfigField("String", "API_VERSION", "\"/v1\"")
      buildConfigField("String", "API_KEY", "\"8c2cf3b0bbbcc3fc60b33f8ab975f4f8\"")

      resValue("string", "app_name", "Marvel")


      isMinifyEnabled = false
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  packagingOptions {
    exclude("META-INF/services/javax.annotation.processing.Processor")
  }

  dexOptions {
    javaMaxHeapSize = "4g"
  }

  lintOptions {
    isAbortOnError = false
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  // android
  implementation(Deps.AndroidX.material)
  implementation(Deps.AndroidX.appCompat)
  implementation(Deps.AndroidX.swipe_ui)
  implementation(Deps.AndroidX.constraint)
  implementation(Deps.AndroidX.constraintSolver)
  implementation(Deps.AndroidX.multidex)
  // helpers
  implementation(Deps.AndroidX.mvi)

  implementation(Deps.AndroidX.http)

  implementation(Deps.AndroidX.moshi)
  kapt(Deps.Kapt.moshi)

  //implementation(Deps.AndroidX.work_runtine)
  //implementation(Deps.AndroidX.work_rxjava2)

  kapt(Deps.Kapt.dagger)
  kapt(Deps.Kapt.daggerProcessor)

  implementation(Deps.AndroidX.glide)
  kapt(Deps.Kapt.glide)

  implementation(Deps.AndroidX.kotlin)

  //implementation(Deps.AndroidX.exo_core)
  //implementation(Deps.AndroidX.exo_ui)
  //implementation(Deps.AndroidX.exo_http)

  //implementation(Deps.AndroidX.firebase_messaging)
  //implementation(Deps.AndroidX.firebase_analytics)
  //implementation(Deps.AndroidX.firebase_crashlytics)
}

fun buildVersionCode(): Int = Versions.App.major * 100000 + Versions.App.minor * 10000 + Versions.App.build
fun buildVersionName(): String = "${Versions.App.major}.${Versions.App.minor}.${Versions.App.build}"

