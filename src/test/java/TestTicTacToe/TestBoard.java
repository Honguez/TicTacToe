package TestTicTacToe;

import MainTicTacToe.Board;
import org.junit.Assert;
import org.junit.Test;

public class TestBoard {

    @Test
    public void testSetBoardPosition(){
        Board board = new Board();
        board.setBoardPosition(6,'X');
        Assert.assertEquals('X', board.board[6]);
    }

    @Test
    public void testCheckMovesLefttrue(){
        Board board = new Board();
        Assert.assertTrue(board.checkMovesLeft());
    }

    @Test
    public void testCheckMoveLeftFalse(){
        Board board = new Board();
        board.setBoardPosition(0, 'X');
        board.setBoardPosition(4, 'O');
        board.setBoardPosition(8, 'X');
        board.setBoardPosition(1, 'O');
        board.setBoardPosition(7, 'X');
        board.setBoardPosition(6, 'O');
        board.setBoardPosition(2, 'X');
        board.setBoardPosition(5, 'O');
        board.setBoardPosition(3, 'X');
        Assert.assertFalse(board.checkMovesLeft());

    }

    @Test

    public void testInitializeBoard(){
        char[] boards = {'0','1','2','3','4','5','6','7','8'};
        Board board = new Board();
        board.initializeBoard(boards);
        for (int i = 0; i<board.board.length; i++){
            Assert.assertEquals(boards[i], board.board[i]);
        }
    }

}