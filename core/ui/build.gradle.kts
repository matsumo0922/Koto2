plugins {
    id("matsumo.primitive.kmp.common")
    id("matsumo.primitive.android.library")
    id("matsumo.primitive.kmp.compose")
    id("matsumo.primitive.kmp.android")
    id("matsumo.primitive.kmp.ios")
    id("matsumo.primitive.kmp.jvm")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "me.matsumo.koto.core.ui"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:model"))
            implementation(project(":core:common"))
            implementation(project(":core:repository"))
            implementation(project(":core:datasource"))
            implementation(project(":core:resource"))

            api(libs.bundles.ui.common)
            api(libs.bundles.calf)

            api(compose.runtime)
            api(compose.runtimeSaveable)
            api(compose.foundation)
            api(compose.animation)
            api(compose.animationGraphics)
            api(compose.material)
            api(compose.material3)
            api(compose.ui)
            api(compose.materialIconsExtended)
            api(compose.components.uiToolingPreview)

            api(libs.adaptive)
            api(libs.adaptive.layout)
            api(libs.adaptive.navigation)
        }

        androidMain.dependencies {
            api(libs.bundles.ui.android)
            api(libs.play.service.ads)
        }

        jvmMain.dependencies {
            api(compose.desktop.currentOs) {
                exclude(group = "org.jetbrains.compose.material")
            }
        }
    }
}
