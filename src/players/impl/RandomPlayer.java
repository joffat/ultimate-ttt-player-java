package players.impl;

import model.Coords;
import model.Move;
import model.Result;
import model.board.Cell;
import model.board.UTTTSubBoard;
import players.AbstractPlayer;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends AbstractPlayer {

    private final static Random randomNum = new Random();

    public RandomPlayer() {
        super();
    }

    @Override
    public void matchStart() {
        this.newBoard();
    }

    @Override
    public void gameStart() {
        this.newBoard();
    }

    @Override
    public Move getMove() {
        List<UTTTSubBoard> unfinishedBoards = this.getCurrentBoard().getUnfinishedBoards();
        UTTTSubBoard board = unfinishedBoards.get(randomNum.nextInt(unfinishedBoards.size()));
        List<Cell> cells = board.getEmptyCells();
        Cell cell = cells.get(randomNum.nextInt(cells.size()));


        Move move = cell.asMove();
        this.getCurrentBoard().addMyMove(move);

        return move;
    }

    @Override
    public Move opponentMove(Move opponentsMove) {
        this.getCurrentBoard().addOpponentMove(opponentsMove);
        if (this.getCurrentBoard().isFinished(opponentsMove.getMove())) return this.getMove();

        List<Cell> cells = this.getCurrentBoard().getEmptyCells(opponentsMove.getMove());
        if (cells.size() == 0) return this.getMove();
        Move myMove = cells.get(randomNum.nextInt(cells.size())).asMove();
        this.getCurrentBoard().addMyMove(myMove);

        return myMove;
    }

    @Override
    public void gameOver(Result result, Move lastMove) {
//        if (lastMove != null)
//            this.getCurrentBoard().addOpponentMove(lastMove);
//        this.debug("GAME OVER: " + result);
    }

    @Override
    public void matchOver(Result result) {
//        this.debug("MATCH OVER: " + result);
    }

}
