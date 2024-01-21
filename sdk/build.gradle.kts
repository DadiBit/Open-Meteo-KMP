plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.publication")
}

group = "com.openmeteo.sdk"
version = "1.0.0-alpha.0"

kotlin {
    jvm()

    js {
        browser {
            webpackTask {
                mainOutputFileName = "sdk.js"
            }
        }
        binaries.executable()
    }

    linuxX64 {
        binaries.staticLib {
            baseName = "sdk"
        }
    }


    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.serialization.protobuf)
            implementation(libs.kotlinx.serialization.properties)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.core)
            implementation(project(":flatbuffers"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }

        linuxMain.dependencies {
            implementation(libs.ktor.client.curl)
        }

    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

}
