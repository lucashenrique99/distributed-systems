package com.cefetmg.sd.rmi.server;

import com.cefetmg.sd.rmi.interfaces.Player;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServerMain {

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(12345);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Player player;
        try {
            player = new PlayerImpl();
            Naming.rebind("rmi://localhost:12345/player", player);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
