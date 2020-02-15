package fr.kata.tennis.model;

import lombok.Data;

@Data
public class MatchSet {
	private Integer currentScoreSetPlayerOne;
	private Integer currentScoreSetPlayerTwo;
	private Integer currentBreakScorePlayerOne;
	private Integer currentBreakScorePlayerTwo;
	private Boolean tieBreakRuleActive;
	private String matchWinner;

	public MatchSet() {
		currentScoreSetPlayerOne = 0;
		currentScoreSetPlayerTwo = 0;
		currentBreakScorePlayerOne = 0;
		currentBreakScorePlayerTwo = 0;
		tieBreakRuleActive = Boolean.FALSE;
	}

}
