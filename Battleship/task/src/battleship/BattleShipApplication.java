package battleship;

import battleship.model.Ships;
import battleship.service.BattleShipService;
import battleship.service.TakingShotService;

import java.io.IOException;

public class BattleShipApplication {
    private char [][] player1Field;
    private char [][] player2Field;
    private Ships player1Ships;
    private Ships player2Ships;
    private BattleShipService player1Service;
    private BattleShipService player2Service;
    private TakingShotService takingShotService;

    public BattleShipApplication() {
        player1Field = new char[10][10];
        player2Field = new char[10][10];
        player1Ships = new Ships(player1Field);
        player2Ships = new Ships(player2Field);
        player1Service = new BattleShipService(player1Ships, "Player 1");
        player2Service = new BattleShipService(player2Ships, "Player 2");
        takingShotService = new TakingShotService();
    }

    private void promptEnterKey() {
        System.out.println("\nPress Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareGame() {
        player1Service.setShips();
        promptEnterKey();
        player2Service.setShips();
        promptEnterKey();
    }

    private void startBattle() {
          while(true) {
              player1Ships.showFields(player1Ships.getOpponentField());
              System.out.println("---------------------");
              player1Ships.showFields(player1Ships.getGameField()); // first player ships
              System.out.println(player1Service.getPlayerName() + ", it's your turn:");
              takingShotService.setShips(player2Ships); // set enemy ship
              if (takingShotService.endGame(player1Ships.getOpponentField())) {
                  break;
              }
              promptEnterKey();
              //second player
              player2Ships.showFields(player2Ships.getOpponentField());
              System.out.println("---------------------");
              player2Ships.showFields(player2Ships.getGameField());
              System.out.println(player2Service.getPlayerName() + ", it's your turn:");
              takingShotService.setShips(player1Ships);
              if (takingShotService.endGame(player2Ships.getOpponentField())) {
                  break;
              }
              promptEnterKey();
          }
    }

    public void startGame() {
        prepareGame();
        startBattle();
    }
}
