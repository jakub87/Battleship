package battleship.service;

import battleship.model.Ships;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TakingShotService {
    private Ships ships;
    private Scanner scanner;

    public TakingShotService() {
        scanner = new Scanner(System.in);
    }

    public void setShips(Ships ships) {
        this.ships = ships;
    }

    public boolean endGame(char [][] playerField) {
        boolean result = false;
        String userInput;
        while(!(userInput = scanner.nextLine()).matches("^[A-J]{1}([1-9]|10){1}$")) { 
            System.out.println("Error! You entered the wrong coordinates! Try again:");
        }

        int userRow = BattleShipService.indexMapping.get(userInput.charAt(0));
        int userColumn = Integer.parseInt(userInput.substring(1)) - 1;

        if (ships.getGameField()[userRow][userColumn] == 'O') {
            ships.getGameField()[userRow][userColumn] = 'X';
            playerField[userRow][userColumn] = 'X';
            List<String> coordinatesShip = null;
            for(Map.Entry<String, List<String>> entry : ships.getShipCoordinates().entrySet()) {
                if (entry.getValue().contains(userRow + "" + userColumn)) {
                    coordinatesShip = entry.getValue();
                    coordinatesShip.remove(userRow + "" + userColumn);
                    if (coordinatesShip.isEmpty()) {
                        ships.getShipCoordinates().remove(entry.getKey());
                    }
                    break;
                }
            }

            if (ships.getShipCoordinates().isEmpty()) {
                System.out.println("\nYou sank the last ship. You won. Congratulations!");
                result = true;
            } else if (coordinatesShip.isEmpty()) {
                System.out.println("\nYou sank a ship!\n");
            } else {
                System.out.println("\nYou hit a ship!\n");
            }
        } else if (ships.getGameField()[userRow][userColumn] == 'X') {
            System.out.println("\nHit\n");
        } else {
            ships.getGameField()[userRow][userColumn] = 'M';
            playerField[userRow][userColumn] = 'M';
            System.out.println("\nYou missed.\n");
        }
        return result;
    }
}
