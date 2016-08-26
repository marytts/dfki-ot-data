import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

import org.yaml.snakeyaml.Yaml

class ExtractText extends DefaultTask {

    @InputFile
    File yamlFile = project.findProperty('yamlFile')

    @OutputDirectory
    File destDir = project.file("$project.buildDir/text")

    @TaskAction
    void extract() {
        new Yaml().load(yamlFile.newReader()).each { utterance ->
            project.file("$destDir/${utterance.prompt}.txt").withWriter { writer ->
                writer.println utterance.text
            }
        }
    }
}
