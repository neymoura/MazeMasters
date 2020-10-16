package com.ioraptor.mazemasters.main;

import com.ioraptor.mazemasters.game.Maze;

import java.util.Scanner;

/**
 * Created by ney on 5/17/16.
 */
public class Main {

    public static void main(String args[]){

        Maze maze = new Maze();

        maze.printMap();

        maze.addPlayer('1');
        maze.addPlayer('2');
        maze.addPlayer('3');
        maze.addPlayer('4');

        maze.printMap();

        Scanner scanner = new Scanner(System.in);

        while(true){

            try {
                String line = scanner.nextLine();

                char player = line.charAt(0);
                char direction = line.charAt(1);

                maze.movePlayer(player, direction);
                maze.printMap();

                maze.printMap(maze.movePlayer(player, direction));

            }catch (Exception e){
                //none
            }

        }


    }

}
