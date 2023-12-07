plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrainsCompose)
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "com.rmaprojects.erbechat.ErbeChatApplicationKt"

        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "com.rmaprojects.rabbaniichat"
            packageVersion = "1.0.0"
        }
    }
}
