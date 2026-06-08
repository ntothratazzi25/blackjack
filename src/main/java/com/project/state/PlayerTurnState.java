package com.project.state;

import com.project.game.Game;


/** represents the state where it is the player's turn to play
 *  this is the initial state of the game, and the player can choose to hit, double down, or stand.
 *  if the player hits and busts, the game will immediately resolve with a loss for the player.
 *  if the player doubles down and busts, the game will immediately resolve with a loss for the player.
 *  if the player stands, the game will transition to the dealer's turn state.
 *  
 * @author Nick Toth-Ratazzi
 **/


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
        else if (game.getPlayer().getHand().calculateTotal() == 21)
        {
            game.setState(new DealerTurnState());
            game.getDealer().flipHoleCard();
            game.getDealer().playTurn(game.getDeck());
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
