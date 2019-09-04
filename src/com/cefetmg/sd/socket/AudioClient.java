package com.cefetmg.sd.socket;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class AudioClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 6666);

            if (socket.isConnected()) {

                BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                AudioInputStream stream = AudioSystem.getAudioInputStream(bufferedInputStream);
                AudioFormat format = stream.getFormat();

                SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, stream
                        .getFormat(), ((int) stream.getFrameLength() * format.getFrameSize()));
                SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(stream.getFormat());
                line.start();

                int numRead;
                byte[] buf = new byte[line.getBufferSize()];
                while ((numRead = stream.read(buf, 0, buf.length)) >= 0) {
                    int offset = 0;
                    while (offset < numRead) {
                        offset += line.write(buf, offset, numRead - offset);
                    }
                }
                line.drain();
                line.stop();

            }

        } catch (IOException | UnsupportedAudioFileException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
