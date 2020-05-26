DFKI OT Data
============

Speech database for the OT Turkish TTS voices in [MaryTTS].

Data format
-----------

### Audio

The audio data is provided in the losslessly compressed [FLAC] format, which can be played by a [myriad of software](https://xiph.org/flac/links.html#software), including [Praat].
The speaker was recorded at a 44.1 kHz sampling rate, 16 bits per sample, in mono.
No filters of any sort have been applied to this raw data.

### Phonetic segmentation

Annotations are provided as a single [YAML] file. It contains a list of utterances, each of which consists of
- a prompt code (file basename),
- the utterance text,
- the recording date,
- utterance start and end times (in seconds) in the FLAC file,
- the phonetic segments (obtained using the eHMM tool from [FestVox] 2.1), each of which has
  - a label (based on [SAMPA], `_` denotes silence), and
  - its duration (in seconds)

For example,

```yaml
- prompt: ot0798
  text: Resim ticaretine başladı.
  date: 2009-07-10T07:32:17Z
  start: 4553.4053741500
  end: 4556.6553741500
  segments:
  - { lab: _, dur: 0.324969 }
  - { lab: r, dur: 0.1 }
  - { lab: e, dur: 0.1 }
  - { lab: s, dur: 0.135 }
  - { lab: I, dur: 0.035 }
  - { lab: m, dur: 0.08 }
  - { lab: t, dur: 0.095 }
  - { lab: I, dur: 0.05 }
  - { lab: dZ, dur: 0.115 }
  - { lab: a, dur: 0.11 }
  - { lab: r, dur: 0.025 }
  - { lab: e, dur: 0.065 }
  - { lab: t, dur: 0.125 }
  - { lab: I, dur: 0.025 }
  - { lab: n, dur: 0.035 }
  - { lab: e, dur: 0.07 }
  - { lab: b, dur: 0.065 }
  - { lab: a, dur: 0.12 }
  - { lab: S, dur: 0.18 }
  - { lab: l, dur: 0.015 }
  - { lab: a, dur: 0.06 }
  - { lab: d, dur: 0.07 }
  - { lab: '@', dur: 0.17 }
  - { lab: _, dur: 0.595 }
```

Extracting the data
-------------------

### Prerequisites

Java 8 (or later) and [SoX] must be installed.

### Assembling the data

The data processing is delegated to [Gradle] and the [FLAML plugin].

To download and extract all data, run

    ./gradlew downloadAudio extractTextFiles extractLabFiles extractWavFiles

See the [FLAML plugin] documentation for details.

To prepare the data for distribution, run

    ./gradlew assemble

Copyright and license
---------------------

Copyright 2009 [DFKI GmbH].

[![Creative Commons License](https://mirrors.creativecommons.org/presskit/buttons/88x31/svg/by-nc-sa.svg)](https://creativecommons.org/licenses/by-nc-sa/4.0/)

This work is licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License].

[Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License]: https://creativecommons.org/licenses/by-nc-sa/4.0/
[DFKI GmbH]: https://dfki.de/
[FestVox]: http://festvox.org/
[FLAC]: https://xiph.org/flac/
[FLAML plugin]: https://github.com/m2ci-msp/gradle-flaml-plugin
[Gradle]: https://gradle.org/
[MaryTTS]: http://mary.dfki.de/
[Praat]: http://praat.org/
[SAMPA]: http://www.phon.ucl.ac.uk/home/sampa/
[SoX]: http://sox.sourceforge.net/
[YAML]: https://yaml.org/
