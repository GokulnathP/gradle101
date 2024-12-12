import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

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