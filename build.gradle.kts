plugins { java }

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "com.gokulnathp.languageapp.SayHello"
    }
}

repositories { mavenCentral() }

dependencies { testImplementation(libs.junit.jupiter) }

tasks.named<Test>("test") { useJUnitPlatform() }
