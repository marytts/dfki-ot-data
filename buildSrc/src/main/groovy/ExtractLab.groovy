import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

import org.yaml.snakeyaml.Yaml

class ExtractLab extends DefaultTask {

    @InputFile
    File yamlFile = project.findProperty('yamlFile')

    @OutputDirectory
    File destDir = project.file("$project.buildDir/lab")

    @TaskAction
    void extract() {
        new Yaml().load(yamlFile.newReader()).each { utterance ->
            if (utterance.segments) {
                project.file("$destDir/${utterance.prompt}.lab").withWriter { writer ->
                    writer.println '#'
                    def end = 0.0
                    utterance.segments.each { segment ->
                        end += segment.dur
                        writer.println "${end.round(6)} 125 $segment.lab"
                    }
                }
            }
        }
    }
}
