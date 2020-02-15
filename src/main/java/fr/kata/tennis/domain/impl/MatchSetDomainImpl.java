package fr.kata.tennis.domain.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.kata.tennis.constant.GameConst;
import fr.kata.tennis.domain.MatchSetDomain;
import fr.kata.tennis.model.Game;
import fr.kata.tennis.model.MatchSet;

public class MatchSetDomainImpl implements MatchSetDomain {

	/**
	 * Logger.
	 */
	private static Logger log = LoggerFactory.getLogger(MatchSetDomainImpl.class);

	/**
	 * play a  set of match
	 * 
	 * @param matchSet
	 * @param game
	 * @return MatchSet
	 */
	public MatchSet playSet(MatchSet matchSet, Game game) {
		if (matchSet != null && matchSet.getMatchWinner() == null && game != null && game.getWinnerName() != null) {
			log.info("play a set of match ");
			if (Boolean.TRUE.equals(matchSet.getTieBreakRuleActive())) {
				applyBreakRule(matchSet, game.getWinnerName());
			} else {
				winSet(matchSet, game.getWinnerName());
			}
			if (matchSet.getCurrentScoreSetPlayerOne().equals(matchSet.getCurrentScoreSetPlayerTwo())
					&& matchSet.getCurrentScoreSetPlayerTwo() == 6) {
				matchSet.setTieBreakRuleActive(Boolean.TRUE);
			}
		}

		return matchSet;
	}

	/**
	 * apply the tie break rule and set the winner name if the tie break of this
	 * match is finished
	 * 
	 * @param matchSet
	 * @param gameWinner
	 */
	private void applyBreakRule(MatchSet matchSet, String gameWinner) {
		if (gameWinner.equals(GameConst.PLAYER_ONE)) {
			Integer currentBreakScore = matchSet.getCurrentBreakScorePlayerOne();
			matchSet.setCurrentBreakScorePlayerOne(++currentBreakScore);
			if (currentBreakScore >= 7 && (currentBreakScore - matchSet.getCurrentBreakScorePlayerTwo()) >= 2)
				matchSet.setMatchWinner(GameConst.PLAYER_ONE);
			log.info("player one tie break score ={}",matchSet.getCurrentBreakScorePlayerOne());
		} else if (gameWinner.equals(GameConst.PLAYER_TWO)) {
			Integer currentBreakScore = matchSet.getCurrentBreakScorePlayerTwo();
			matchSet.setCurrentBreakScorePlayerTwo(++currentBreakScore);
			if (currentBreakScore >= 7 && (currentBreakScore - matchSet.getCurrentBreakScorePlayerOne()) >= 2)
				matchSet.setMatchWinner(GameConst.PLAYER_TWO);
			log.info("player two tie break score ={}",matchSet.getCurrentBreakScorePlayerTwo());
		}
	}

	/**
	 * play a set of match and set the player winner if the match is finished
	 * 
	 * @param matchSet
	 * @param gameWinner
	 */
	private void winSet(MatchSet matchSet, String gameWinner) {
		if (gameWinner.equals(GameConst.PLAYER_ONE)) {
			Integer currentScoreSet = matchSet.getCurrentScoreSetPlayerOne();
			matchSet.setCurrentScoreSetPlayerOne(++currentScoreSet);
			if ((currentScoreSet == 6 && matchSet.getCurrentScoreSetPlayerTwo() <= 4)
					|| (currentScoreSet == 7 && matchSet.getCurrentScoreSetPlayerTwo() < currentScoreSet
							&& matchSet.getCurrentScoreSetPlayerTwo() >= 5)) {
				matchSet.setMatchWinner(GameConst.PLAYER_ONE);
			}
			log.info("player one set score ={}",matchSet.getCurrentScoreSetPlayerOne());
		} else if (gameWinner.equals(GameConst.PLAYER_TWO)) {
			Integer currentScoreSet = matchSet.getCurrentScoreSetPlayerTwo();
			matchSet.setCurrentScoreSetPlayerTwo(++currentScoreSet);
			if ((currentScoreSet == 6 && matchSet.getCurrentScoreSetPlayerOne() <= 4)
					|| (currentScoreSet == 7 && matchSet.getCurrentScoreSetPlayerOne() < currentScoreSet
							&& matchSet.getCurrentScoreSetPlayerOne() >= 5))
			{
				matchSet.setMatchWinner(GameConst.PLAYER_TWO);
			}
			log.info("player two set score ={}",matchSet.getCurrentScoreSetPlayerTwo());

		}
	}

}
