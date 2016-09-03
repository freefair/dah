package io.freefair.dah.controller.messaging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameUpdate
{
    private int timeout;
    private int rounds;
}
