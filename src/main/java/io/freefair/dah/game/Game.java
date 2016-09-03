package io.freefair.dah.game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game
{
    private String id = "";
    private List<Player> users = new ArrayList<>();
    private int timeout = 30;
    private int currentRound = 0;
    private int rounds = 10;
    private List<Hand> winnerHand = new ArrayList<>();
}
