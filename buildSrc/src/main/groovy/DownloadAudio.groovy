import de.undercouch.gradle.tasks.download.Download

class DownloadAudio extends Download {

    DownloadAudio() {
        super()
        src "https://github.com/marytts/dfki-ot-data/releases/download/v0.1/${project.findProperty('flacFile').name}"
        dest project.findProperty('flacFile')
        overwrite false
        acceptAnyCertificate true
    }
}
