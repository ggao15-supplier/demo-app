plugins {
    `kotlin-dsl`
    id("org.jetbrains.kotlin.jvm") version ("1.3.72")
}
repositories {
    jcenter()
    mavenCentral()
    google()
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.android.tools.build:gradle:4.1.3")
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}