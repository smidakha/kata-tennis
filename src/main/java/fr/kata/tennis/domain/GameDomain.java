package fr.kata.tennis.domain;

import fr.kata.tennis.model.Game;

public interface GameDomain {
  public Game playGame(Game game, String playerName);
}
