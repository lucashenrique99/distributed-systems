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


        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
