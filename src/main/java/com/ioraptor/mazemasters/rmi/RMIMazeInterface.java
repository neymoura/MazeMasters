package com.ioraptor.mazemasters.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by ney on 5/23/16.
 */
public interface RMIMazeInterface extends Remote {

    char[][] addPlayer(char player) throws RemoteException;

    char[][] dropPlayer(char player, boolean asWinner) throws RemoteException;

    char[][] movePlayer(char player, char direction) throws RemoteException;

    void printMap() throws RemoteException;

    void printMap(char[][] map) throws RemoteException;

}
