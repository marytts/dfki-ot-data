import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

import org.yaml.snakeyaml.Yaml

class ExtractTextGrid extends DefaultTask {

    @InputFile
    File yamlFile = project.findProperty('yamlFile')

    @OutputFile
    File destFile = project.file("$project.buildDir/dfki-$project.name-data.TextGrid")

    @TaskAction
    void extract() {
        destFile.withWriter { writer ->
            def utterances = new Yaml().load(yamlFile.newReader())
            def xmin = utterances.first().start
            def xmax = utterances.last().end
            writer.println '"Praat chronological TextGrid text file"'
            writer.println "$xmin $xmax   ! Time domain."
            writer.println '3   ! Number of tiers.'
            writer.println '"IntervalTier" "prompts"' + " $xmin $xmax"
            writer.println '"IntervalTier" "text"' + " $xmin $xmax"
            writer.println '"IntervalTier" "segments"' + " $xmin $xmax"
            utterances.each { utterance ->
                writer.println '\n! prompts:'
                writer.println "1 $utterance.start $utterance.end"
                writer.println "\"$utterance.prompt\""
                writer.println '\n! prompts:'
                writer.println "2 $utterance.start $utterance.end"
                writer.println "\"$utterance.text\""
                if (utterance.segments) {
                    def start = utterance.start
                    def end
                    utterance.segments.each { segment ->
                        end = start + segment.dur
                        writer.println '\n! segments:'
                        writer.println "3 $start $end"
                        writer.println "\"$segment.lab\""
                        start = end
                    }
                    if (end < utterance.end) {
                        writer.println '\n! segments:'
                        writer.println "3 $end $utterance.end"
                        writer.println '""'
                    }
                } else {
                    writer.println '\n! segments:'
                    writer.println "3 $utterance.start $utterance.end"
                    writer.println '""'
                }
            }
        }
    }
}
