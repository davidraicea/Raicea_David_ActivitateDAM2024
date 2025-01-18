plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.seminar04"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.seminar04"
        minSdk = 23
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
implementation(libs.firebase.database)
    //room-compiler, room-runtime
    //in clasa disc, @entinty cu table name discuri
    //primary key autogenerate, not null @
    //interfata DAO
    //insert, query
    //baza de date-clasa abstracta - returneaza un obiect de tip dao
    //adnotari: Database, TypeConverters - clasa conversie (metode @typeconverter),anunt in db ca fol. acest converter
    //utilizare: in adaugare, create database = Room.databaseBuilder
    //adaugare pe fir secundar (inainte de intent)

    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}