# DFKI OT Data

Speech database for the OT Turkish TTS voices in [MaryTTS](http://mary.dfki.de/).

## Format

The audio data is provided in a single [FLAC](https://xiph.org/flac/) file per subset, recorded at 44.1 kHz sampling frequency with 16 bit per sample.

The textual data is provided in a single [YAML](http://yaml.org/) file per subset.
These files are a list of utterances, each of which contains
- a **prompt** code (file basename),
- the utterance **text**,
- a **date** timestamp (when the recording was created, in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) format),
- utterance **start** and **end** times (in seconds) in the FLAC file,
- optionally, the phonetic **segments**, each of which has
    - a **lab**el (based on [SAMPA](http://www.phon.ucl.ac.uk/home/sampa/english.htm), `_` denotes silence), and
    - its **dur**ation (in seconds)

For example,
```yaml
- prompt: ot0798
  text: Resim ticaretine başladı.
  date: 2009-07-10T07:32:17Z
  start: 4553.4053741500
  end: 4556.6553741500
  segments:
  - lab: _
    dur: 0.324969
  - lab: r
    dur: 0.1
  - lab: e
    dur: 0.1
  - lab: s
    dur: 0.135
  - lab: I
    dur: 0.035
  - lab: m
    dur: 0.08
  - lab: t
    dur: 0.095
  - lab: I
    dur: 0.05
  - lab: dZ
    dur: 0.115
  - lab: a
    dur: 0.11
  - lab: r
    dur: 0.025
  - lab: e
    dur: 0.065
  - lab: t
    dur: 0.125
  - lab: I
    dur: 0.025
  - lab: n
    dur: 0.035
  - lab: e
    dur: 0.07
  - lab: b
    dur: 0.065
  - lab: a
    dur: 0.12
  - lab: S
    dur: 0.18
  - lab: l
    dur: 0.015
  - lab: a
    dur: 0.06
  - lab: d
    dur: 0.07
  - lab: '@'
    dur: 0.17
  - lab: _
    dur: 0.595
```

## Downloading the data

Use the links on the [releases](../../releases) page, or run the `downloadAudio` task (see below).

## Converting the data

For convenience, the utterances for each subset can be be extracted from the YAML and FLAC files using simple commands to run [Gradle](https://gradle.org/) tasks.
After cloning or downloading and unpacking this repository, run `./gradlew tasks` (or `gradlew tasks` on Windows) for details.

### Prerequisites

You will need [Java](https://www.java.com/) to run the tasks. Extracting the utterances to WAV files also requires [`sox`](http://sox.sourceforge.net/) to be installed.

### Copyright and license

Copyright 2009 [DFKI GmbH](http://dfki.de/).

[![Creative Commons License](http://mirrors.creativecommons.org/presskit/buttons/88x31/svg/by-nc-sa.svg)](http://creativecommons.org/licenses/by-nc-sa/4.0/)

This work is licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-nc-sa/4.0/).
