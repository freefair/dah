package io.freefair.dah.game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlayerCards {
    private List<Integer> cards = new ArrayList<>();
    private int mainCard;
}
