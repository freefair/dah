package io.freefair.dah.game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player
{
    private String id;
    private int points;
    private String username;
    private boolean isMaster;
    private boolean isReady;
    private PlayerCards cards;

    public Player(String userId) {
        this.id = userId;
    }
}
