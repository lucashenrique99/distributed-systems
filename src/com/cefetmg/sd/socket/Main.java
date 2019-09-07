package com.cefetmg.sd.socket;

import javax.sound.sampled.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor ouvindo a porta 12345");
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                saida.flush();
                saida.writeObject(
                        Arrays.asList("Musica 1", "Musica 2", "Musica 3")
                );


                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                String selected = (String) entrada.readObject();
                System.out.println("Musica selecionada: " + selected);
                cliente.getOutputStream().flush();

                File soundFile = new File("C:\\Users\\lucas\\Downloads\\coldplay_hymn_for_the_weekend_alan_walker_remix_music_video_64kbps.wav");

                System.out.println("Musica: " + soundFile);

                FileInputStream in = new FileInputStream(soundFile);
                OutputStream out = cliente.getOutputStream();

                byte[] buffer = new byte[2048];
                int count;
                int sendCount = 0;
                while ((count = in.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                    System.out.println("Send count: " + sendCount);
                    sendCount++;
                    Thread.sleep(10);
                }


                System.out.println("Servidor: finalizada transferencia");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            System.out.println("FIM - SOCKET");
        }
    }
}
