package retamrovec.finesoftware.fallguys.Managers;

import retamrovec.finesoftware.fallguys.Enums.GameState;

public class Arena {

    private GameState state;
    private int id;
    public Arena(int id) {
        this.id = id;
        this.state = GameState.RECRUITING;
    }
}
