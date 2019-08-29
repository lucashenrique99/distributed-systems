package com.cefetmg.sd.socket;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost",12345);
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            List<String> list = (List<String>) entrada.readObject();
            System.out.println(list.toString());
//            entrada.close();

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            System.out.println("Selecione uma das opções acima:");
            String option = bufferedReader.readLine();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
            objectOutputStream.flush();
            objectOutputStream.writeObject(option);
//            objectOutputStream.close();


            System.out.println("Recebendo musica");
//            SourceDataLine.Info info = (SourceDataLine.Info) entrada.readObject();
//            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
//            line.open(stream.getFormat());
//            line.start();
//
//            int numRead = 0;
//            byte[] buf = new byte[line.getBufferSize()];
//            while ((numRead = stream.read(buf, 0, buf.length)) >= 0) {
//                int offset = 0;
//                while (offset < numRead) {
//                    offset += line.write(buf, offset, numRead - offset);
//                }
//                Thread.sleep(800);
//            }
//            line.drain();
//            line.stop();

        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
