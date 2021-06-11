package battleship.repository;

public interface BattleShipsRepository {
    void initializeFields();
    void showFields(char [][] gameField);
    void setShip(String shipName, int rowBeginShip, int rowEndShip, int columnBeginShip, int columnEndShip);
}
