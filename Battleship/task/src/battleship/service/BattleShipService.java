package battleship.service;

import battleship.model.ShipType;
import battleship.model.Ships;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BattleShipService {
    private Ships ships;
    private Scanner scanner;
    public static Map<Character, Integer> indexMapping;
    private String playerName;

    public BattleShipService(Ships ships, String playerName) {
        this.ships = ships;
        scanner = new Scanner(System.in);
        indexMapping = new HashMap<>();
        this.playerName = playerName;
        initializeIndexes();
    }

    public String getPlayerName() {
        return playerName;
    }

    private void initializeIndexes() {
        indexMapping.put('A',0);
        indexMapping.put('B',1);
        indexMapping.put('C',2);
        indexMapping.put('D',3);
        indexMapping.put('E',4);
        indexMapping.put('F',5);
        indexMapping.put('G',6);
        indexMapping.put('H',7);
        indexMapping.put('I',8);
        indexMapping.put('J',9);
    }

    private boolean checkFreeSpaceAroundShip(int userRowBeginShip, int userRowEndShip, int userColumnBeginShip, int userColumnEndShip) {
        for (int i = userRowBeginShip; i <= userRowEndShip; i++) {
            for (int j = userColumnBeginShip; j <= userColumnEndShip; j++) {
                if (!checkFreeSpaceAroundField(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkFreeSpaceAroundField(int row, int column) {
        if (row - 1 >= 0 && ships.getGameField()[row - 1][column] == 'O'                        ||
            row - 1 >= 0 && column - 1 >= 0 && ships.getGameField()[row - 1][column - 1] == 'O' ||
            column - 1 >= 0 && ships.getGameField()[row][column - 1] == 'O'                     ||
            row + 1 < 10 && column - 1 >= 0 && ships.getGameField()[row + 1][column - 1] == 'O' ||
            row + 1 < 10 && ships.getGameField()[row + 1][column] == 'O'                        ||
            row + 1 < 10 && column + 1 < 10 && ships.getGameField()[row + 1][column + 1] == 'O' ||
            column + 1 < 10 && ships.getGameField()[row][column + 1] == 'O'                     ||
            row - 1 >= 0 && column + 1 < 10 && ships.getGameField()[row - 1][column + 1] == 'O'
        ){
            return false;
        } else {
            return true;
        }
    }

    public void setShips() {
        System.out.println(playerName + ", place your ships on the game field\n");
        ships.showFields(ships.getGameField());
        String coordinates;
        for (ShipType shipType: ShipType.values()) {
            System.out.println("\nEnter the coordinates of the " + shipType.getShipName() + " (" + shipType.getShipLength() + " cells):");
            while(true) {
                coordinates = scanner.nextLine();
                int userRowBeginShip = indexMapping.get(coordinates.split(" ")[0].charAt(0));
                int userColumnBeginShip = Integer.parseInt(coordinates.split(" ")[0].substring(1)) - 1;
                int userRowEndShip = indexMapping.get(coordinates.split(" ")[1].charAt(0));
                int userColumnEndShip = Integer.parseInt(coordinates.split(" ")[1].substring(1)) - 1;
                int userShipLength = 1 + Math.max(userRowBeginShip, userRowEndShip) - Math.min(userRowBeginShip, userRowEndShip) + Math.max(userColumnBeginShip, userColumnEndShip) - Math.min(userColumnBeginShip, userColumnEndShip) ;

                if (userRowBeginShip != userRowEndShip && userColumnBeginShip != userColumnEndShip) {
                    System.out.println("\nError! Wrong ship location! Try again:\n");
                } else if (userShipLength != shipType.getShipLength()) {
                    System.out.println("\nError! Wrong length of the " + shipType.getShipName() +"! Try again:\n");
                } else if (checkFreeSpaceAroundShip(Math.min(userRowBeginShip, userRowEndShip), Math.max(userRowBeginShip, userRowEndShip), Math.min(userColumnBeginShip, userColumnEndShip), Math.max(userColumnBeginShip, userColumnEndShip))){ // tutaj sprawdzamy czy statek mozna ustawic
                    ships.setShip(shipType.name(), Math.min(userRowBeginShip, userRowEndShip), Math.max(userRowBeginShip, userRowEndShip), Math.min(userColumnBeginShip, userColumnEndShip), Math.max(userColumnBeginShip, userColumnEndShip));
                    ships.showFields(ships.getGameField());
                    break;
                } else {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                }
            }
        }
    }
}
