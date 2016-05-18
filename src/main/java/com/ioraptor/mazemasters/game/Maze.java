package com.ioraptor.mazemasters.game;

/**
 * Created by ney on 5/17/16.
 */
public class Maze {

    private static final char MAP_FREE = ' ';
    private static final char MAP_EXIT = '*';

    private static final int BLUR_RANGE = 1;
    private static final char BLUR_CHAR = ' ';

    private static final char MOVE_UP = 'w';
    private static final char MOVE_DOWN = 's';
    private static final char MOVE_LEFT = 'a';
    private static final char MOVE_RIGHT = 'd';

    private static char[][] map;

    /**
     * Inicia o labirinto
     */
    public Maze() {

        map = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', ' ', 'x', ' ', 'x', ' ', ' ', 'x', ' ', '*'},
                {'x', ' ', ' ', ' ', ' ', ' ', 'x', 'x', ' ', 'x'},
                {'x', ' ', 'x', ' ', 'x', 'x', 'x', ' ', ' ', 'x'},
                {'x', ' ', 'x', 'x', 'x', ' ', 'x', ' ', 'x', 'x'},
                {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', ' ', 'x'},
                {'x', ' ', ' ', ' ', ' ', ' ', 'x', 'x', ' ', 'x'},
                {'x', 'x', ' ', 'x', 'x', ' ', ' ', 'x', ' ', 'x'},
                {'x', ' ', ' ', ' ', 'x', 'x', ' ', ' ', ' ', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}
        };

    }

    /**
     * Adiciona um certo jogador ao mapa
     * @param player
     * @return
     */
    public static char[][] addPlayer(char player) {

        boolean shouldContinue = true;

        for (int i = 0; (i < map.length) && shouldContinue; i++) {
            for (int j = 0; (j < map.length) && shouldContinue; j++) {
                if (Character.isWhitespace(map[i][j])) {
                    map[i][j] = player;
                    shouldContinue = false;
                }
            }
        }

        return map;

    }

    /**
     * Remove jogador do mapa
     *
     * @param player
     * @param asWinner
     * @return
     */
    public static char[][] dropPlayer(char player, boolean asWinner) {

        if(asWinner){
            System.out.println();
            System.out.println("*************************");
            System.out.println("******YOU*FREE*NOW*******");
            System.out.println("*************************");
            System.out.println();
        }

        boolean shouldContinue = true;

        for (int i = 0; (i < map.length) && shouldContinue; i++) {
            for (int j = 0; (j < map.length) && shouldContinue; j++) {
                if (map[i][j] == player) {
                    map[i][j] = MAP_FREE;
                    shouldContinue = false;
                }
            }
        }

        return map;

    }

    /**
     * Move a posição de um jogador
     *
     * @param player
     * @param direction
     * @return
     */
    public static char[][] movePlayer(char player, char direction) {

        Integer newPlayerPositionI = null;
        Integer newPlayerPositionJ = null;

        boolean shouldContinue = true;

        for (int i = 0; (i < map.length) && shouldContinue; i++) {
            for (int j = 0; (j < map.length) && shouldContinue; j++) {

                if (map[i][j] == player) {

                    try {

                        switch (direction) {
                            case MOVE_UP:
                                if (map[i - 1][j] == MAP_FREE) {
                                    map[i - 1][j] = player;
                                    map[i][j] = MAP_FREE;
                                    newPlayerPositionI = i - 1;
                                    newPlayerPositionJ = j;
                                } else if (map[i - 1][j] == MAP_EXIT) {
                                    return dropPlayer(player, true);
                                }
                                break;

                            case MOVE_DOWN:
                                if (map[i + 1][j] == MAP_FREE) {
                                    map[i + 1][j] = player;
                                    map[i][j] = MAP_FREE;
                                    newPlayerPositionI = i + 1;
                                    newPlayerPositionJ = j;
                                } else if (map[i + 1][j] == MAP_EXIT) {
                                    return dropPlayer(player, true);
                                }
                                break;

                            case MOVE_RIGHT:
                                if (map[i][j + 1] == MAP_FREE) {
                                    map[i][j + 1] = player;
                                    map[i][j] = MAP_FREE;
                                    newPlayerPositionI = i;
                                    newPlayerPositionJ = j + 1;
                                } else if (map[i][j + 1] == MAP_EXIT) {
                                    return dropPlayer(player, true);
                                }
                                break;

                            case MOVE_LEFT:
                                if (map[i][j - 1] == MAP_FREE) {
                                    map[i][j - 1] = player;
                                    map[i][j] = MAP_FREE;
                                    newPlayerPositionI = i;
                                    newPlayerPositionJ = j - 1;
                                } else if (map[i][j - 1] == MAP_EXIT) {
                                    dropPlayer(player, true);
                                }
                                break;

                        }

                    } catch (Exception e) {
                        //invalid movement
                    } finally {
                        shouldContinue = false;
                    }

                }
            }
        }

        char[][] playerMap = getPlayerMap(newPlayerPositionI, newPlayerPositionJ);

        return playerMap;

    }

    /**
     * Gera neblina para posicao especificada
     *
     * @param newPlayerPositionI
     * @param newPlayerPositionJ
     * @return
     */
    private static char[][] getPlayerMap(Integer newPlayerPositionI, Integer newPlayerPositionJ) {
        char[][] playerMap = new char[map.length][map.length];

        copyMap(map, playerMap);

        //blur player map
        for (int i = 0; (i < playerMap.length); i++) {
            for (int j = 0; (j < playerMap.length); j++) {

                if (i > newPlayerPositionI + BLUR_RANGE || i < newPlayerPositionI - BLUR_RANGE
                        || j > newPlayerPositionJ + BLUR_RANGE || j < newPlayerPositionJ - BLUR_RANGE) {
                    playerMap[i][j] = BLUR_CHAR;
                }

            }
        }
        return playerMap;
    }

    /**
     * Imprime o mapa
     */
    public static void printMap() {

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

    /**
     * Imprime mapa
     * @param map
     */
    public static void printMap(char[][] map) {

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

    /**
     * Copia mapa
     * @param from
     * @param to
     */
    private static void copyMap(char[][] from, char[][] to) {
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

}
