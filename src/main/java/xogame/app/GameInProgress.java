package xogame.app;

public class GameInProgress implements GameState {

    private Player player;
    public GameInProgress(Player player) {
        this.player = player;
    }

    @Override
    public void showState() {
        System.out.println(player);
    }

    @Override
    public GameState nextState(String input) {
        return this;
    }
}