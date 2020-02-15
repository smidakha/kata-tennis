package fr.kata.tennis.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class Game {
	private Integer currentScorePlayerOne;
	private Integer currentScorePlayerTwo;
	private Boolean deuceRuleActive;
	private String advandgePlayer;
	private String winnerName;

	public Game() {
		this.currentScorePlayerOne = 0;
		this.currentScorePlayerTwo = 0;
		this.deuceRuleActive = Boolean.FALSE;
	}

}
