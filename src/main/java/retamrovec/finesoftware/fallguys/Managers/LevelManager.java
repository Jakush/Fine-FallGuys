package retamrovec.finesoftware.fallguys.Managers;

import java.util.UUID;

public class LevelManager {

    private final UUID player;
    private int level = 0;
    public LevelManager(UUID player) {
        this.player = player;
    }

    public UUID getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
