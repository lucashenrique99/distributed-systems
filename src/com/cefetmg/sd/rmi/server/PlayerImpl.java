package com.cefetmg.sd.rmi.server;

import com.cefetmg.sd.rmi.interfaces.Player;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

public class PlayerImpl extends UnicastRemoteObject implements Player {

    protected PlayerImpl() throws RemoteException {
    }

    @Override
    public List<String> getMusics() {
        return Arrays.asList("Musica 1", "Musica 2", "Musica 3");
    }

    @Override
    public File getMusic(String name) {
        try {
            System.out.println("Musica selecionada: " + name);

            File soundFile = new File("C:\\Users\\lucas\\Downloads\\coldplay_hymn_for_the_weekend_alan_walker_remix_music_video_64kbps.wav");
            return soundFile;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}