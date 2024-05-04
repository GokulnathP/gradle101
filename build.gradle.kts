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


abstract class CreateFileTask : DefaultTask() {
    @get:Input
    abstract var fileText: String

    @Input
    val fileName = "hello.txt"

    @OutputFile
    val myFile: File = File(fileName)

    @TaskAction
    fun action() = myFile.writeText(fileText)
}

abstract class MyPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("createFile", CreateFileTask::class) {
            group = "custom"
            description = "Creates hello.txt in the current directory"

            fileText = "Hello from create file register"
        }
    }
}

apply<MyPlugin>()
