package com.project.state;

import com.project.game.Game;


/** represents the state of the game
 *  this is the main interface for the State pattern, and defines the methods that will be implemented by the different game states (PlayerTurnState, DealerTurnState, ResolvedState).
 *  each state will handle the player's actions (hit, double down, stand) differently based on the current state of the game.
 *  
 * @author Nick Toth-Ratazzi
 **/


public interface GameState 
{
    void handleHit(Game game);
    void handleDoubleDown(Game game);
    void handleStand(Game game);
}
