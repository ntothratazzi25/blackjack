package com.project.state;

import com.project.game.Game;

public interface GameState 
{
    void handleHit(Game game);
    void handleDoubleDown(Game game);
    void handleStand(Game game);
}
