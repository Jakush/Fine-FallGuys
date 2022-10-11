package retamrovec.finesoftware.fallguys.Maps.Map1;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author RETAMROVEC
 * @version 1.0
 * @since 2022-10-9
 */
public class SlimeJump extends BukkitRunnable implements FunctionsHandler {

    private final World world;
    private final int minX;
    private final int minY;
    private final int minZ;
    private final int maxX;
    private final int maxY;
    private final int maxZ;
    private final long yaw;
    private final long pitch;
    private final Material block;
    public ArrayList<Location> blocks = new ArrayList<>();
    private final long speed;
    public SlimeJump(World world, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, long yaw, long pitch, int arenaID, int id, long speed) {
        this.world = world;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.maxX = maxX;
        this.yaw = yaw;
        this.pitch = pitch;
        this.speed = speed;
        this.block = Material.matchMaterial(getFunctions().getString("arenas." + arenaID + ".2." + id + ".type"));
    }

    public void start() {
        runTaskTimer(FallGuys.instance(), 20, speed);
    }

    @Override
    public void run() {
        if (blocks.size() > 1) {
            for (Location value : blocks) {
                world.getBlockAt(value).setType(Material.AIR);
            }
            blocks.clear();
        }
        int resultX = minX;
        int resultY = minY;
        int resultZ = minZ;
        if (minX != maxX) {
            resultX = ThreadLocalRandom.current().nextInt(minX, maxX);
        }
        if (minY != maxY) {
            resultY = ThreadLocalRandom.current().nextInt(minY, maxY);
        }
        if (minZ != maxZ) {
            resultZ = ThreadLocalRandom.current().nextInt(minZ, maxZ);
        }
        Location location = new Location(world, resultX, resultY, resultZ, yaw, pitch);
        world.getBlockAt(location).setType(block);
        blocks.add(location);
    }

    public ArrayList<Location> getBlocks() {
        return blocks;
    }
}
