package com.cefetmg.sd.socket;
import java.io.*;
import java.net.*;

public class AudioServer {
    public static void main(String[] args) throws IOException {
        File soundFile = new File("C:\\Users\\lucas\\Downloads\\coldplay_hymn_for_the_weekend_alan_walker_remix_music_video_64kbps.wav");

        System.out.println("server: " + soundFile);

        try (ServerSocket serverSocker = new ServerSocket(6666);
             FileInputStream in = new FileInputStream(soundFile)) {
            if (serverSocker.isBound()) {
                Socket client = serverSocker.accept();
                OutputStream out = client.getOutputStream();

                byte buffer[] = new byte[2048];
                int count;
                int sendCount = 0;
                while ((count = in.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                    System.out.println("Send count: " + sendCount);
                    sendCount++;
                }
            }
        }

        System.out.println("server: shutdown");
    }
}