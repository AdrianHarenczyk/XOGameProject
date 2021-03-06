package ox.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.ScoreBoard;
import ox.app.io.InputOutput;
import ox.app.languages.Language;
import ox.app.languages.Messenger;
import ox.app.states.GameState;
import ox.app.states.PreEndState;
import ox.app.states.RunState;
import ox.app.utility.PlayerBuffer;

import java.io.ByteArrayInputStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestRunState {
    private static RunState runState;

    @BeforeMethod
    private static void initialize() {
        String resignMessage = "resign\n";
        System.setIn(new ByteArrayInputStream(resignMessage.getBytes()));
        Consumer<String> output = s -> {
        };
        Consumer<String> boardOutput = s -> {
        };
        Supplier<String> input = () -> "string";
        InputOutput inputOutput = new InputOutput(input,output,boardOutput);

        Messenger messenger = new Messenger(Language.EN);
        PlayerBuffer playerBuffer = new PlayerBuffer();
        ScoreBoard scoreBoard = new ScoreBoard(playerBuffer, messenger);
        Board board = Board.newBoard(5, 5);

        runState = new RunState(inputOutput, messenger, playerBuffer, board,
                scoreBoard, 1, 3);
    }

    @Test
    public static void ifWhenUserInputIsResignThanNextStateReturnsPreEndState() throws WrongArgumentException {
        // When
        GameState supposedPreEndState = runState.nextState("resign");
        // Then
        Assert.assertEquals(supposedPreEndState.getClass(), PreEndState.class);
    }
}
