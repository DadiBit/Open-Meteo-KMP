plugins {
    alias(libs.plugins.multiplatform)
}

group = "com.openmeteo.flatbuffers"
version = "1.0.0-alpha.0"

kotlin {
    jvm()

    js {
        browser()
    }

    linuxX64()


    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }

}
