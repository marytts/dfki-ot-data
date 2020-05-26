import de.undercouch.gradle.tasks.download.Download

class DownloadAudio extends Download {

    DownloadAudio() {
        super()
        def flacFileName = project.flaml.flacFile.get().asFile.name
        src "https://github.com/marytts/dfki-ot-data/releases/download/v0.1/$flacFileName"
        dest flacFileName
        overwrite false
        acceptAnyCertificate true
    }
}
