package fr.kata.tennis.domain.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.kata.tennis.constant.GameConst;
import fr.kata.tennis.domain.MatchSetDomain;
import fr.kata.tennis.domain.impl.MatchSetDomainImpl;
import fr.kata.tennis.model.Game;
import fr.kata.tennis.model.MatchSet;

@RunWith(JUnit4.class)
public class SetMatchDomainTest {
	MatchSetDomain setMatchDomain = new MatchSetDomainImpl();

	@Test
	public void winPointInSetPlayerOneTest() {
		MatchSet setMatch = new MatchSet();
		Game game = new Game();
		game.setWinnerName(GameConst.PLAYER_ONE);
		setMatchDomain.playSet(setMatch, game);
		Assert.assertEquals(new Integer(1), setMatch.getCurrentScoreSetPlayerOne());
	}

	@Test
	public void winSetPlayerTwoTest() {
		MatchSet setMatch = new MatchSet();
		for (int index = 0; index < 4; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_ONE);
			setMatchDomain.playSet(setMatch, game);
		}
		for (int index = 0; index < 6; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_TWO);
			setMatchDomain.playSet(setMatch, game);
		}
		Assert.assertEquals(GameConst.PLAYER_TWO, setMatch.getMatchWinner());
	}

	@Test
	public void playSetWithSevenPlayerTwoTest() {
		MatchSet setMatch = new MatchSet();
		for (int index = 0; index < 5; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_ONE);
			setMatchDomain.playSet(setMatch, game);
		}
		for (int index = 0; index < 6; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_TWO);
			setMatchDomain.playSet(setMatch, game);
		}
		Assert.assertNull(setMatch.getMatchWinner());
	}

	@Test
	public void winSetWithSevenPlayerTwoTest() {
		MatchSet setMatch = new MatchSet();
		for (int index = 0; index < 5; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_ONE);
			setMatchDomain.playSet(setMatch, game);
		}
		for (int index = 0; index < 7; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_TWO);
			setMatchDomain.playSet(setMatch, game);
		}
		Assert.assertEquals(GameConst.PLAYER_TWO, setMatch.getMatchWinner());
	}

	@Test
	public void winSetWithBreakRuleTest() {
		MatchSet setMatch = new MatchSet();
		for (int index = 0; index < 5; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_ONE);
			setMatchDomain.playSet(setMatch, game);
		}
		for (int index = 0; index < 6; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_TWO);
			setMatchDomain.playSet(setMatch, game);
		}
		Game game = new Game();
		game.setWinnerName(GameConst.PLAYER_ONE);
		setMatchDomain.playSet(setMatch, game);
		Assert.assertTrue(setMatch.getTieBreakRuleActive());
	}

	@Test
	public void activateBreakRuleTest() {
		MatchSet setMatch = new MatchSet();
		for (int index = 0; index < 5; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_ONE);
			setMatchDomain.playSet(setMatch, game);
		}
		for (int index = 0; index < 6; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_TWO);
			setMatchDomain.playSet(setMatch, game);
		}
		for (int index = 0; index < 6; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_ONE);
			setMatchDomain.playSet(setMatch, game);
		}
		for (int index = 0; index < 7; index++) {
			Game game = new Game();
			game.setWinnerName(GameConst.PLAYER_TWO);
			setMatchDomain.playSet(setMatch, game);
		}
		Assert.assertEquals(GameConst.PLAYER_TWO, setMatch.getMatchWinner());
	}
}
