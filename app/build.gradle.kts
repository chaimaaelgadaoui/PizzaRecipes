plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.pizzarecipes"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.pizzarecipes"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

tasks.register("renameProsciutto") {
    doLast {
        val oldFile = file("src/main/res/mipmap/prosciutto e funghi.png")
        val newFile = file("src/main/res/mipmap/prosciutto_e_funghi.png")
        if (oldFile.exists()) {
            oldFile.renameTo(newFile)
            println("Renamed ${oldFile.absolutePath} to ${newFile.absolutePath}")
        }
    }
}
