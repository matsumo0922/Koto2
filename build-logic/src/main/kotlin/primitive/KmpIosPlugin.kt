package primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpIosPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            kotlin {
                applyDefaultHierarchyTemplate()

                iosX64()
                iosArm64()
                iosSimulatorArm64()

                sourceSets.named { it.lowercase().startsWith("ios") }.configureEach {
                    languageSettings {
                        optIn("kotlinx.cinterop.ExperimentalForeignApi")
                    }
                }
            }
        }
    }
}
