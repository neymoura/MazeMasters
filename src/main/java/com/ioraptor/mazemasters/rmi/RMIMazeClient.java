package com.ioraptor.mazemasters.rmi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by ney on 5/23/16.
 */
public class RMIMazeClient implements KeyListener{

    public static void main(String[] args) {

        try {

            Registry registry = LocateRegistry.getRegistry(null); //null for localhost
            RMIMazeInterface maze = (RMIMazeInterface) registry.lookup("MazeService");

            //1 - Escolha seu jogador
            Scanner in = new Scanner(System.in);

            System.out.print("Escolha seu personagem [0-9]: ");
            String personagem = in.nextLine();

            maze.addPlayer(personagem.charAt(0));

            System.out.println();

            while (true) {
                //2 - Mova

                System.out.print("Escolha um movimento [w,a,s,d]: ");
                String movimento = in.nextLine();

                if (movimento.equalsIgnoreCase("w")
                        || movimento.equalsIgnoreCase("a")
                        || movimento.equalsIgnoreCase("s")
                        || movimento.equalsIgnoreCase("d")) {


                    char[][] map = maze.movePlayer(personagem.charAt(0), movimento.charAt(0));

                    if (verificaVitoria(personagem.charAt(0), map)){
                        localMapPrint(map);
                        System.out.println();
                        System.out.println("*************************");
                        System.out.println("*****YOU*FREE*NOW*"+personagem.charAt(0)+"******");
                        System.out.println("*************************");
                        System.out.println();
                        System.exit(0);
                    }else{
                        localMapPrint(map);
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean verificaVitoria(char player, char[][] map){

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if(map[i][j] == player){
                    return false;
                }
            }
        }

        return true;

    }

    public static void localMapPrint(char[][] map) {

        System.out.println();
        System.out.println("*************************");
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("*************************");
        System.out.println();
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("PRA CIMAAAA");
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
