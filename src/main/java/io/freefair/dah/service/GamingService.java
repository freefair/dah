package io.freefair.dah.service;

import io.freefair.dah.game.Game;
import io.freefair.dah.game.Player;
import io.freefair.dah.game.PlayerCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GamingService
{
    private final List<Game> currentGames = new ArrayList<>();

    private final SimpMessagingTemplate messageingTemplate;

    @Autowired
    public GamingService(SimpMessagingTemplate messageingTemplate) {
        this.messageingTemplate = messageingTemplate;
    }

    public Game createGame() {
        Game g = new Game();
        g.setId(UUID.randomUUID().toString());
        currentGames.add(g);
        return g;
    }

    public Game getGame(String gameId) {
        return currentGames.stream().filter(g -> Objects.equals(g.getId(), gameId)).findFirst().get();
    }

    public void selectCardsAndSendToPlayer(String gameId) {
        Game game = getGame(gameId);
        int mainCard = (int)(Math.random()*100);
        List<Player> users = game.getUsers();
        for (Player player :
                users) {
            PlayerCards playerCards = new PlayerCards();
            for(int i = 0; i < 10; i++) {
                playerCards.getCards().add((int)(Math.random() * 100));
            }
            playerCards.setMainCard(mainCard);
            player.setCards(playerCards);
            messageingTemplate.convertAndSendToUser(player.getId(), "/cards", playerCards);
        }
    }

    public void markPlayerReady(String gameId, String userId) {
        Game game = getGame(gameId);
        Player first = game.getUsers().stream().filter(p -> p.getId().equals(userId)).findFirst().get();
        first.setReady(true);
        if(game.getUsers().stream().allMatch(Player::isReady)){
            sendGameStart(gameId);
            selectCardsAndSendToPlayer(gameId);
        }
    }

    public void sendGameStart(String gameId) {
        Game game = getGame(gameId);
        List<Player> users = game.getUsers();
        for (Player player :
                users) {
            messageingTemplate.convertAndSendToUser(player.getId(), "/start", game);
        }
    }
}
