plugins { java }

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "com.gokulnathp.languageapp.SayHello"
    }
}

repositories { mavenCentral() }

dependencies { testImplementation(libs.junit.jupiter) }

tasks.named<Test>("test") { useJUnitPlatform() }

tasks.register("hello") {
    group = "Greet"

    doFirst { println("First") }

}

tasks.register("greet") {
    group = "Greet"

    doLast { exec { commandLine("java", "--version") } }

    dependsOn("hello")
}
