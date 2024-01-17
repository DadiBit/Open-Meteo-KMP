# Open-Meteo KMP

Kotlin Multiplatform Library

### Publish to MavenCentral

1) Registering a Sonatype account as described here: 
   https://dev.to/kotlin/how-to-build-and-publish-a-kotlin-multiplatform-library-going-public-4a8k
2) Add developer id, name, email and the project url to
   `/convention-plugins/src/main/kotlin/convention.publication.gradle.kts`
3) Add the secrets to `local.properties`:

```
signing.keyId=...
signing.password=...
signing.secretKeyRingFile=...
ossrhUsername=...
ossrhPassword=...
```

4) Run `./gradlew :dodo:publishAllPublicationsToSonatypeRepository`

### Build platform artifacts

#### JVM jar

- Run `./gradlew :sdk:jvmJar`
- Output: `/sdk/build/libs/sdk-jvm-1.0.jar`

#### JS file

- Run `./gradlew :sdk:jsBrowserProductionWebpack`
- Output: `/sdk/build/dist/js/productionExecutable/sdk.js`

#### Linux static library

- Run `./gradlew :sdk:linkReleaseStaticLinuxX64`
- Output: `/sdk/build/bin/linuxX64/releaseStatic/libsdk.a`
