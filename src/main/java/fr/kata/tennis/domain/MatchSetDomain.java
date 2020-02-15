package fr.kata.tennis.domain;

import fr.kata.tennis.model.Game;
import fr.kata.tennis.model.MatchSet;

public interface MatchSetDomain {
  public MatchSet playSet(MatchSet setMatch,Game game);
}
