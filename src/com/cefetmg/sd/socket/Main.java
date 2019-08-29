package com.cefetmg.sd.socket;

import javax.sound.sampled.*;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor ouvindo a porta 12345");
            while (true) {
                // o método accept() bloqueia a execução até que
                // o servidor receba um pedido de conexão
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                saida.flush();
                saida.writeObject(
                        Arrays.asList("Musica 1", "Musica 2", "Musica 3")
                );
//                saida.close();


                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                String selected = (String) entrada.readObject();
                System.out.println("Selected music: " + selected);

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
//                entrada.close();

                System.out.println("Sending music");
                saida.flush();
                saida.writeObject(
                        info
                );


            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            System.out.println("FIM - SOCKET");
        }
    }
}
