package com.cefetmg.sd.rmi.client;

import com.cefetmg.sd.rmi.interfaces.Player;

import javax.sound.sampled.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class ClientMain {

    public static void main(String[] args) {
        try {
            Player player = (Player) Naming.lookup("rmi://localhost:12345/player");
            System.out.println(player.getMusics());
            File soundFile = player.getMusic("musica 1");

            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(
                            new FileInputStream(soundFile)
                    )
            );

            AudioFormat format = stream.getFormat();

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
            }
            line.drain();
            line.stop();

        } catch (IOException | NotBoundException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
