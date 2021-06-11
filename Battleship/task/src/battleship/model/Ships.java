package battleship.model;

import battleship.repository.BattleShipsRepository;

import java.util.*;

public class Ships implements BattleShipsRepository {
    private char [][] gameField;
    private char [][] opponentField;
    private Map<String, List<String>> shipCoordinates;

    public Ships(char[][] gameField) {
        this.gameField = gameField;
        opponentField = new char[gameField.length][gameField[0].length];
        shipCoordinates = new HashMap<>();
        initializeFields();
    }

    @Override
    public void initializeFields() {
        Arrays.stream(gameField).forEach(field -> Arrays.fill(field, '~'));
        Arrays.stream(opponentField).forEach(field -> Arrays.fill(field, '~'));
    }

    @Override
    public void showFields(char [][]gameField) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < gameField.length; i++) {
            System.out.print((char) (65 + i));
            for (int j = 0; j < gameField[0].length; j++) {
                System.out.print(" " + gameField[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void setShip(String shipName, int rowBeginShip, int rowEndShip, int columnBeginShip, int columnEndShip) {
        List<String> shipCoorinates = new ArrayList<>();
        for (int i = rowBeginShip; i <= rowEndShip; i++) {
            for (int j = columnBeginShip; j <= columnEndShip; j++) {
                gameField[i][j] = 'O';
                shipCoorinates.add(i + "" + j);
            }
        }
        shipCoordinates.put(shipName, shipCoorinates);
    }

    public char[][] getGameField() {
        return gameField;
    }

    public char[][] getOpponentField() {
        return opponentField;
    }

    public Map<String, List<String>> getShipCoordinates() {
        return shipCoordinates;
    }
}
