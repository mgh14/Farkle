package main.engine.driver;

import main.engine.Player;
import main.engine.PlayerManager;
import main.engine.Roll;
import main.engine.RollManager;

public class Farkle {

    public static void main(String[] args) {
        //PropertiesManager.loadSettingsProfile("profiles/4playerProfile.xml");

      RollManager rollManager = new RollManager();
      PlayerManager playerManager = new PlayerManager();

      Player me = new Player(1);
      playerManager.addPlayer(me);

      while(true) {

        Roll roll = rollManager.beginTurn(playerManager.getCurrentPlayer());
      }

    }
}
