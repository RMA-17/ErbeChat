plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrainsCompose)
}

dependencies {
    api(project(":shared"))

    implementation(compose.desktop.currentOs)
    //Voyager - coroutine support
    implementation(libs.kotlinx.coroutines.swing)
}

compose.desktop {
    application {
        mainClass = "com.rmaprojects.erbechat.ErbeChatApplicationKt"

        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "com.rmaprojects.erbechat"
            packageVersion = "1.0.0"
        }
    }
}
