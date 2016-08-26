import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

class Publish extends DefaultTask {

    @InputFile
    File yamlFile = project.findProperty('yamlFile')

    @InputFile
    File flacFile = project.findProperty('flacFile')

    @TaskAction
    void upload() {
        [yamlFile, flacFile].each { file ->
            project.exec {
                commandLine 'github-release', 'upload',
                        '--user', 'marytts',
                        '--repo', project.rootProject.name,
                        '--tag', "v$project.rootProject.version",
                        '--name', file.name,
                        '--file', file
            }
        }
    }
}
