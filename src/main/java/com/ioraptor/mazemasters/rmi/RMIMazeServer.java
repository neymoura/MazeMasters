package com.ioraptor.mazemasters.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by ney on 5/23/16.
 */
public class RMIMazeServer {

    private static RMIMaze rmiMaze;

    public static void main(String[] args){
        startServer();
    }

    private static void startServer(){

        Registry registry = null;

        try {
            rmiMaze = new RMIMaze();
            RMIMazeInterface stub = (RMIMazeInterface) UnicastRemoteObject.exportObject(rmiMaze, 0);
            registry = LocateRegistry.getRegistry();
            registry.bind("MazeService", stub);
        } catch (Exception e) {
            System.err.println("Maybe you should run the '/MazeMasters/target/classes $ rmiregistry &' command using the terminal tab first?");
            e.printStackTrace();
            System.exit(0);
        }

    }

}
