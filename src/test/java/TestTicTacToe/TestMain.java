package TestTicTacToe;

import MainTicTacToe.Board;
import MainTicTacToe.Main;
import org.junit.Assert;
import org.junit.Test;

public class TestMain {

    @Test
    public void testValidatePosition(){
        Board board = new Board();
        board.setBoardPosition(0,'X');
        Assert.assertFalse(Main.validatePosition(board,0));
        Assert.assertTrue(Main.validatePosition(board,1));
    }

    @Test
    public void testIsWin(){
        Board board = new Board();
        board.setBoardPosition(0, 'X');
        board.setBoardPosition(1, 'X');
        board.setBoardPosition(2, 'X');
        Assert.assertTrue(Main.isWin(board,'X'));
    }

    @Test
    public void testFindBestMove(){
        Board board = new Board();
        board.setBoardPosition(0,'X');
        board.setBoardPosition(4,'O');
        board.setBoardPosition(2,'X');
        Assert.assertEquals(Main.findBestMoves(board),1);
    }

    @Test
    public void testMinMax(){
        Board board = new Board();
        char USER = 'X';
        board.setBoardPosition(4,'X');
        Assert.assertEquals(Main.minimax(board,0,USER),0);
    }
}
