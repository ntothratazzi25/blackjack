package com.project.state;

import com.project.game.Game;


/** represents the state where the game has been resolved and the outcome has been determined
 *  this state is entered after the dealer has played their turn and the outcome of the game has been determined (win, lose, push).
 *  this class is mostly a placeholder for the State pattern and to make future UI implementation easier, as all of the resolved logic is held in Game.resolvedOutcome().
 *  
 * @author Nick Toth-Ratazzi
 **/


public class ResolvedState implements GameState
{

    @Override
    public void handleHit(Game game) 
    {
        // resolved state logic is all held in Game.resolvedOutcome(), so nothing is needed here. this class is just a placeholder for the State pattern and to make future UI implementation easier.
    }

    @Override
    public void handleDoubleDown(Game game) 
    {
        // resolved state logic is all held in Game.resolvedOutcome(), so nothing is needed here. this class is just a placeholder for the State pattern and to make future UI implementation easier.
    }

    @Override
    public void handleStand(Game game) 
    {
        // resolved state logic is all held in Game.resolvedOutcome(), so nothing is needed here. this class is just a placeholder for the State pattern and to make future UI implementation easier.
    }

}
