public class Player {

    private final int playerNum;

    public Player(int PLAYER_NUM) {
        if(PLAYER_NUM < 1) {
            throw new IllegalArgumentException("Player number must be greater than 0");
        }

        playerNum = PLAYER_NUM;
    }

    public int getPlayerNum() {
        return playerNum;
    }
}
