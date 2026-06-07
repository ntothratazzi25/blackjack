package com.project.state;

import com.project.game.Game;

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
