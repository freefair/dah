package io.freefair.dah.controller;

import io.freefair.dah.controller.messaging.SubscribeObject;
import io.freefair.dah.game.Game;
import io.freefair.dah.controller.messaging.GameUpdate;
import io.freefair.dah.game.Player;
import io.freefair.dah.service.GamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/game")
public class GameController
{
    private final GamingService gamingService;

    @Autowired
    public GameController(GamingService gamingService) {
        this.gamingService = gamingService;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Game createGame() {
        return gamingService.createGame();
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
    public Game updateGame(@PathVariable String gameId, @RequestBody GameUpdate update) {
        Game game = gamingService.getGame(gameId);
        game.setRounds(update.getRounds());
        game.setTimeout(update.getTimeout());
        return game;
    }

    @MessageMapping("/game/subscribe")
    public void subscribeGame(SubscribeObject subscribe) {
        Game game = gamingService.getGame(subscribe.getGameId());
        game.getUsers().add(new Player(subscribe.getUserId()));
    }

    @MessageMapping("/game/ready")
    public void markReady(SubscribeObject subscribeObject){
        gamingService.markPlayerReady(subscribeObject.getGameId(), subscribeObject.getUserId());
    }
}
