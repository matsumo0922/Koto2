plugins {
    id("matsumo.primitive.kmp.common")
    id("matsumo.primitive.android.library")
    id("matsumo.primitive.kmp.android")
    id("matsumo.primitive.kmp.ios")
    id("matsumo.primitive.kmp.jvm")
    id("matsumo.primitive.detekt")
}

android {
    namespace = "me.matsumo.koto.core.datasource"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.proto)
        }

        commonMain.dependencies {
            implementation(project(":core:common"))
            implementation(project(":core:model"))
            implementation(project(":core:resource"))

            api(libs.bundles.filekit)
            api(libs.androidx.datastore.preferences)
        }
    }
}
