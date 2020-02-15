package fr.kata.tennis.domain.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import fr.kata.tennis.constant.GameConst;
import fr.kata.tennis.domain.GameDomain;
import fr.kata.tennis.domain.impl.GameDomainImpl;
import fr.kata.tennis.model.Game;

@RunWith(JUnit4.class)
public class GameDomainTest {
	GameDomain gameDomain = new GameDomainImpl();

	@Test
	public void winGamePlayerOneTest() {
		Game game = new Game();
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		Assert.assertEquals(GameConst.PLAYER_ONE, game.getWinnerName());
	}

	@Test
	public void winGamePlayerTwoTest() {
		Game game = new Game();
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		Assert.assertEquals(GameConst.PLAYER_TWO, game.getWinnerName());
	}

	@Test
	public void winPointPlayerOneWithDeuceTest() {
		Game game = new Game();
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		Assert.assertTrue(game.getDeuceRuleActive());
	}

	@Test
	public void playAdvanPlayerOneWithDeuceTest() {
		Game game = new Game();
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		Assert.assertEquals(GameConst.PLAYER_TWO, game.getAdvandgePlayer());
	}

	@Test
	public void playDeucePlayerOneWithDeuceTest() {
		Game game = new Game();
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		Assert.assertEquals(GameConst.SCORE_DEUCE, game.getCurrentScorePlayerTwo());
	}

	@Test
	public void winWithAdvandgePlayerOneTest() {
		Game game = new Game();
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_TWO);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		gameDomain.playGame(game, GameConst.PLAYER_ONE);
		Assert.assertEquals(GameConst.PLAYER_ONE, game.getWinnerName());
	}
}
