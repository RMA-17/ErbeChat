import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serialization.plugin)
    alias(libs.plugins.buildKonfig)
}

kotlin {
    androidTarget()
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {}
        desktopMain.dependencies {}
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            @OptIn(ExperimentalComposeLibrary::class)
            api(compose.components.resources)

            api(libs.kotlinx.datetime)
            api(compose.materialIconsExtended)

            //Supabase
            implementation(project.dependencies.platform(libs.supabase.bom))
            implementation(libs.supabase.postgresql)
            implementation(libs.supabase.gotrue)
            implementation(libs.supabase.realtime)

            //Ktor
            implementation(libs.ktor.client.okhttp)

            //Koin
            api(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)
            implementation(libs.koin.compose)

            //SerializationX
            implementation(libs.kotlinx.serialization)

            //Multi-Platform Settings
            implementation(libs.multiplatform.settings.coroutines)
            implementation(libs.multiplatform.settings.no.arg)
        }
    }
}

android {
    namespace = "com.rmaprojects.rabbaniichat"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

buildkonfig {
    packageName = "com.rmaprojects.composeMain"

    defaultConfigs {
        buildConfigField(
            STRING,
            "API_KEY",
            gradleLocalProperties(rootDir).getProperty("API_KEY") ?: ""
        )
        buildConfigField(
            STRING,
            "API_URL",
            gradleLocalProperties(rootDir).getProperty("API_URL") ?: ""
        )
    }
}

