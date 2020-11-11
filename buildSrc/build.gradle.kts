plugins {
  `kotlin-dsl`
}
repositories {
  mavenCentral()
  google()
  jcenter()
  maven {
    setUrl("https://plugins.gradle.org/m2/")
  }
}

dependencies {
  implementation("com.android.tools.build:gradle:4.1.1")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
  implementation("com.hiya:jacoco-android:0.2")
  implementation("org.jlleitschuh.gradle:ktlint-gradle:9.2.1")
  implementation("com.google.apis:google-api-services-androidpublisher:v3-rev129-1.25.0")
  implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.9.1")

  implementation(gradleApi())
  implementation(localGroovy())
}
