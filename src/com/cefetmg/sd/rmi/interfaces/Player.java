package com.cefetmg.sd.rmi.interfaces;

import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Player extends Remote {

    List<String> getMusics() throws RemoteException;

    File getMusic(String name) throws RemoteException;

}
