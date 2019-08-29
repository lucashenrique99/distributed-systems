package com.cefetmg.sd.socket;

import javax.sound.sampled.*;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ExemploStreamingMain {

    public static void main(String[] argv) throws Exception {
        AudioInputStream stream = AudioSystem.getAudioInputStream(new File("C:\\Users\\lucas\\Downloads\\coldplay_hymn_for_the_weekend_alan_walker_remix_music_video_64kbps.wav")
        );
//    stream = AudioSystem.getAudioInputStream(new URL(
        //      "http://hostname/audiofile"));

        AudioFormat format = stream.getFormat();
        if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format
                    .getSampleRate(), format.getSampleSizeInBits() * 2, format
                    .getChannels(), format.getFrameSize() * 2, format.getFrameRate(),
                    true); // big endian
            stream = AudioSystem.getAudioInputStream(format, stream);
        }

        SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, stream
                .getFormat(), ((int) stream.getFrameLength() * format.getFrameSize()));
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(stream.getFormat());
        line.start();

        int numRead = 0;
        byte[] buf = new byte[line.getBufferSize()];
        while ((numRead = stream.read(buf, 0, buf.length)) >= 0) {
            int offset = 0;
            while (offset < numRead) {
                offset += line.write(buf, offset, numRead - offset);
            }
            Thread.sleep(800);
        }
        line.drain();
        line.stop();
    }
}
