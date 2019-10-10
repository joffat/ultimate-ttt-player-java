package players;

import model.Command;
import model.Move;
import model.board.UTTTBoard;

import java.util.Arrays;

public abstract class AbstractPlayer implements Player {

    private UTTTBoard currentBoard;

    public AbstractPlayer() {
        this.currentBoard = new UTTTBoard(3);
    }

    @Override
    public void processInput(String input) {
        if (input == null) return;
        debug(input);
        String[] parts = input.split(" ");
        try {
            Command command = Command.getFromCommandMessage(parts[0]);
            switch (command) {
                case INIT:
                    this.gameStart();
                    break;
                case MOVE:
                    this.writeMove(this.getMove());
                    break;
                case OPPONENT:
                    this.writeMove(this.opponentMove(new Move(parts[1])));
                    break;
            }
        } catch (EnumConstantNotPresentException e) {
            System.err.println("Message \"" + input + "\" Could Not Be Processed - No Matching Command" );
        }
    }

    @Override
    public void writeMove(Move move) {
        this.write(move.toString());
    }

    @Override
    public void write(String message) {
        debug(message);
        System.out.println(message);
    }

    @Override
    public void debug(String message) {
        System.err.println(message);
    }

    protected UTTTBoard getCurrentBoard() {
        return currentBoard;
    }

    protected void newBoard() {
        this.currentBoard = new UTTTBoard(3);
    }

    @Override
    public abstract void matchStart();

    @Override
    public abstract void gameStart();

    @Override
    public abstract Move getMove();

    @Override
    public abstract Move opponentMove(Move opponentsMove);

    @Override
    public abstract void gameOver(boolean youWin);

    @Override
    public abstract void matchOver(boolean youWin);

}
