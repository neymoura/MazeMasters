package com.ioraptor.mazemasters.rmi;

import com.ioraptor.mazemasters.game.Maze;

import java.rmi.RemoteException;

/**
 * Created by ney on 5/23/16.
 */
public class RMIMaze implements RMIMazeInterface {

    private Maze maze;

    public RMIMaze(){
        this.maze = new Maze();
    }

    public char[][] addPlayer(char player) throws RemoteException {
        return maze.addPlayer(player);
    }

    public char[][] dropPlayer(char player, boolean asWinner) throws RemoteException {
        return maze.dropPlayer(player, asWinner);
    }

    public char[][] movePlayer(char player, char direction) throws RemoteException {
        return maze.movePlayer(player, direction);
    }

    public void printMap() throws RemoteException {
        maze.printMap();
    }

    public void printMap(char[][] map) throws RemoteException {
        maze.printMap(map);
    }

}
