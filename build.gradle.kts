plugins { java }

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "com.gokulnathp.languageapp.SayHello"
    }
}

repositories { mavenCentral() }

dependencies { testImplementation(libs.junit.jupiter) }

tasks.named<Test>("test") { useJUnitPlatform() }


abstract class HelloTask : DefaultTask() {
    fun hello() = println("Hello!!")

    @TaskAction
    fun hi() = println("Hi")
}

tasks.register<HelloTask>("hello") {
    group = "Greet"

    doFirst { println("First") }

    doLast { hello() }
}

tasks.named<HelloTask>("hello") {
    doLast { hello() }
}

tasks.register("greet") {
    group = "Greet"

    doLast { exec { commandLine("java", "--version") } }

    dependsOn("hello")
}
