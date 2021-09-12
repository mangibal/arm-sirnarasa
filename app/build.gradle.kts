plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-android")
    id("koin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
}

android {
    compileSdk = Apps.compileSdk
    buildToolsVersion = Apps.buildTools

    defaultConfig {
        applicationId = "com.robithohmurid.app"
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments["room.incremental"] = "true"
//            }
//        }
    }

    signingConfigs {
        create("release") {
            keyAlias = "talagasirnarasa"
            keyPassword = "38s1rn@r4s4"
            storeFile =
                file("../keystore/mursyidapp.keystore")
            storePassword = "38s1rn@r4s4"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
//            isShrinkResources = true
//            isZipAlignEnabled = true
//            isJniDebuggable = false
//            isRenderscriptDebuggable = false
            buildConfigField("String", "BASE_URL", "\"${Properties.BASE_URL}\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".dev"
            buildConfigField("String", "BASE_URL", "\"${Properties.BASE_URL_DEBUG}\"")
        }
        create("internal") {
            isMinifyEnabled = true
            isDebuggable = false
//            isShrinkResources = true
//            isZipAlignEnabled = true
//            isJniDebuggable = false
//            isRenderscriptDebuggable = false
            buildConfigField("String", "BASE_URL", "\"${Properties.BASE_URL_DEBUG}\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
//        exclude("META-INF/metadata.kotlin_module")
//        exclude("META-INF/metadata.jvm.kotlin_module")
        resources.excludes.add("META-INF/metadata.kotlin_module")
        resources.excludes.add("META-INF/metadata.jvm.kotlin_module")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Apps.kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Apps.kotlinVersion}")
    // KTX
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtx}")
    implementation("androidx.collection:collection-ktx:${Versions.collectionKtx}")
    /* UI */
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraint}")
    implementation("androidx.recyclerview:recyclerview:${Versions.recylerView}")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.viewpager2:viewpager2:${Versions.viewPager}")
    implementation("io.coil-kt:coil:${Versions.coil}") // Image Loader
    implementation("de.hdodenhof:circleimageview:${Versions.circleImage}")
    implementation("com.facebook.shimmer:shimmer:${Versions.shimmer}")
    implementation("com.tbuonomo:dotsindicator:4.2")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.viewModel}")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.viewModel}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.viewModelExt}")
    // Logger
    implementation("com.jakewharton.timber:timber:4.7.1")
    implementation("com.github.ajalt:timberkt:1.5.1")
    implementation("com.orhanobut:logger:2.2.0")
    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    // Koin AndroidX (Dependency Injection)
    // Koin for Kotlin
    implementation("io.insert-koin:koin-core:${Versions.koin}")
// Koin extended & experimental features
    implementation("io.insert-koin:koin-core-ext:${Versions.koin}")
    // Koin AndroidX Scope features
    implementation("io.insert-koin:koin-androidx-scope:${Versions.koin}")
// Koin AndroidX ViewModel features
    implementation("io.insert-koin:koin-androidx-viewmodel:${Versions.koin}")
// Koin AndroidX Fragment features
    implementation("io.insert-koin:koin-androidx-fragment:${Versions.koin}")
// Koin AndroidX WorkManager
    implementation("io.insert-koin:koin-androidx-workmanager:${Versions.koin}")
// Koin AndroidX Experimental features
    implementation("io.insert-koin:koin-androidx-ext:${Versions.koin}")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    // Session
    implementation("com.orhanobut:hawk:2.0.1")
    /**
     * Date & Time
     * */
    implementation("joda-time:joda-time:2.10.8")
    /**
     * Markwon
     * */
    implementation("io.noties.markwon:core:${Versions.markWon}")
    implementation("io.noties.markwon:html:${Versions.markWon}")
    implementation("io.noties.markwon:image-coil:${Versions.markWon}")

    /**
     * Permission Helper
     * */
    implementation("com.karumi:dexter:6.2.2")

    /**
     * Gson
     */
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.browser:browser:1.3.0")

    /**
     * Firebase
     * */
    implementation(platform("com.google.firebase:firebase-bom:28.2.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    /**
     * Google
     * */
    implementation("com.google.android.gms:play-services-location:18.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}