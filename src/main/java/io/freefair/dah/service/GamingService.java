package io.freefair.dah.service;

import io.freefair.dah.game.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GamingService
{
    private final List<Game> currentGames = new ArrayList<>();

    public Game createGame() {
        Game g = new Game();
        g.setId(UUID.randomUUID().toString());
        currentGames.add(g);
        return g;
    }

    public Game getGame(String gameId) {
        return currentGames.stream().filter(g -> Objects.equals(g.getId(), gameId)).findFirst().get();
    }
}
