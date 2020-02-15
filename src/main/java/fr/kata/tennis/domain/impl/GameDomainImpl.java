package fr.kata.tennis.domain.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.kata.tennis.constant.GameConst;
import fr.kata.tennis.domain.GameDomain;
import fr.kata.tennis.model.Game;

public class GameDomainImpl implements GameDomain {

	/**
	 * Logger.
	 */
	private static Logger log = LoggerFactory.getLogger(GameDomainImpl.class);

	/**
	 * play a a game and apply all the rules and set the winner name if the game is
	 * finished
	 * 
	 * @param game
	 * @param playerName
	 * @return Game
	 */
	public Game playGame(Game game, String playerName) {
		
		if (game != null && game.getWinnerName() == null && playerName != null && (playerName.equals(GameConst.PLAYER_ONE) || playerName.equals(GameConst.PLAYER_TWO)) ) {
			log.info("play a round of a game player ");
			winGame(game, playerName);
			applyDeuceRule(game, playerName);
			if (Boolean.FALSE.equals(game.getDeuceRuleActive())) {
				winPoint(game, playerName);
			}
		}
		return game;
	}

	/**
	 * apply the deuce rules and set the winner name if the game is finished
	 * 
	 * @param game
	 * @param playerName
	 * @return Game
	 */
	private Game applyDeuceRule(Game game, String playerName) {
		Integer scoreBeforeLast = GameConst.SCORES_GAME.get(GameConst.SCORES_GAME.size() - 2);
		int maxScoreIndex = GameConst.SCORES_GAME.size() - 1;
		Boolean isDeucePlayerTwo = GameConst.SCORES_GAME.get(maxScoreIndex).equals(game.getCurrentScorePlayerOne())
				&& playerName.equals(GameConst.PLAYER_TWO) && game.getCurrentScorePlayerTwo().equals(scoreBeforeLast);
		Boolean isDeucePlayerOne = GameConst.SCORES_GAME.get(maxScoreIndex).equals(game.getCurrentScorePlayerTwo())
				&& playerName.equals(GameConst.PLAYER_ONE) && game.getCurrentScorePlayerOne().equals(scoreBeforeLast);
		Boolean isDisadvangeActive = game.getDeuceRuleActive() && game.getAdvandgePlayer() == null;
		Boolean isDisadvangeInactive = game.getDeuceRuleActive() && game.getAdvandgePlayer() != null
				&& !game.getAdvandgePlayer().equals(playerName);
		Integer playerOneLastScore = GameConst.SCORES_GAME.get(maxScoreIndex);
		Integer playerTwoLastScore = GameConst.SCORES_GAME.get(maxScoreIndex);
		if (Boolean.TRUE.equals(isDisadvangeActive)) {
			game.setAdvandgePlayer(playerName);
			if (playerName.equals(GameConst.PLAYER_ONE)) {
				playerOneLastScore = GameConst.SCORE_ADVANDGE;
			} else if (playerName.equals(GameConst.PLAYER_TWO)) {
				playerTwoLastScore = GameConst.SCORE_ADVANDGE;
			}
			game.setCurrentScorePlayerOne(playerOneLastScore);
			game.setCurrentScorePlayerTwo(playerTwoLastScore);
			log.info("advandge player ={}",playerName);
		} else if (Boolean.TRUE.equals(isDisadvangeInactive)) {
			game.setAdvandgePlayer(null);
			game.setCurrentScorePlayerOne(GameConst.SCORE_DEUCE);
			game.setCurrentScorePlayerTwo(GameConst.SCORE_DEUCE);
			log.info("deuce player");
		} else if (isDeucePlayerOne || isDeucePlayerTwo) {
			game.setDeuceRuleActive(Boolean.TRUE);
			game.setCurrentScorePlayerOne(GameConst.SCORES_GAME.get(maxScoreIndex));
			game.setCurrentScorePlayerTwo(GameConst.SCORES_GAME.get(maxScoreIndex));
			log.info("active DEUCE rule");
		}
		return game;
	}

	/**
	 * set the winner name if the game is finished
	 * 
	 * @param game
	 * @param playerName
	 * @return Game
	 */
	private Game winGame(Game game, String playerName) {
		int maxScoreIndex = GameConst.SCORES_GAME.size() - 1;
		Boolean winGameWithoutDeuce = !game.getDeuceRuleActive()
				&& ((GameConst.SCORES_GAME.get(maxScoreIndex).equals(game.getCurrentScorePlayerOne())
						&& playerName.equals(GameConst.PLAYER_ONE))
						|| (GameConst.SCORES_GAME.get(maxScoreIndex).equals(game.getCurrentScorePlayerTwo())
								&& playerName.equals(GameConst.PLAYER_TWO)));
		Boolean winGameWithDeuce = game.getDeuceRuleActive() && game.getAdvandgePlayer() != null
				&& game.getAdvandgePlayer().equals(playerName);
		if (winGameWithoutDeuce || winGameWithDeuce) {
			log.info("game is finished and the winner ={}",playerName);
			game.setWinnerName(playerName);
			game.setCurrentScorePlayerOne(0);
			game.setCurrentScorePlayerTwo(0);
		}
		return game;
	}

	/**
	 * play one round and change the current score
	 * 
	 * @param game
	 * @param playerName
	 * @return Game
	 */
	private Game winPoint(Game game, String playerName) {
		int maxScoreIndex = GameConst.SCORES_GAME.size() - 1;
		Integer playerOneLastScore = game.getCurrentScorePlayerOne();
		Integer playerTwoLastScore = game.getCurrentScorePlayerTwo();
		if (playerName.equals(GameConst.PLAYER_ONE)) {
			int nextIndexPlayerOne = GameConst.SCORES_GAME.indexOf(playerOneLastScore) + 1;
			nextIndexPlayerOne = nextIndexPlayerOne > maxScoreIndex ? maxScoreIndex : nextIndexPlayerOne;
			game.setCurrentScorePlayerOne(GameConst.SCORES_GAME.get(nextIndexPlayerOne));
		} else if (playerName.equals(GameConst.PLAYER_TWO)) {
			int nextIndexPlayerTwo = GameConst.SCORES_GAME.indexOf(playerTwoLastScore) + 1;
			nextIndexPlayerTwo = nextIndexPlayerTwo > maxScoreIndex ? maxScoreIndex : nextIndexPlayerTwo;
			game.setCurrentScorePlayerTwo(GameConst.SCORES_GAME.get(nextIndexPlayerTwo));
		}
		log.info("player one score ={}",game.getCurrentScorePlayerOne());
		log.info("player two score ={}",game.getCurrentScorePlayerTwo());
		return game;
	}

}
