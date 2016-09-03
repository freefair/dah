package io.freefair.dah.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player
{
    private String id;
    private int points;
    private String username;
    private boolean isMaster;

    public Player(String userId) {
        this.id = userId;
    }
}
