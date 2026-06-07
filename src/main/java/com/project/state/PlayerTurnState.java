package com.project.state;

import com.project.game.Game;

public class PlayerTurnState implements GameState
{

    @Override
    public void handleHit(Game game) 
    {
        game.getPlayer().hit(game.getDeck());
        
        if (game.getPlayer().getHand().isBust())
        {
            game.getDealer().flipHoleCard();
            game.resolveOutcome();
        }
    }

    @Override
    public void handleDoubleDown(Game game) 
    {
        game.getPlayer().doubleDown(game.getDeck());

        if (game.getPlayer().getHand().isBust())
        {
            game.getDealer().flipHoleCard();
            game.resolveOutcome();
        }
        else
        {
            game.setState(new DealerTurnState());
            game.getDealer().playTurn(game.getDeck());
            game.resolveOutcome();
        }
    }

    @Override
    public void handleStand(Game game) 
    {
        game.setState(new DealerTurnState());
        game.getDealer().flipHoleCard();
        game.getDealer().playTurn(game.getDeck());
        game.resolveOutcome();
    }
}
