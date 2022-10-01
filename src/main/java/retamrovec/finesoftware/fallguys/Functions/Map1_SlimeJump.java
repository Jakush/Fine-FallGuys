package retamrovec.finesoftware.fallguys.Functions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;

import java.util.ArrayList;
import java.util.Random;

public class Map1_SlimeJump extends BukkitRunnable implements FunctionsHandler {

    private final World world;
    private final double minX;
    private final double minY;
    private final double minZ;
    private final double maxX;
    private final double maxY;
    private final double maxZ;
    private double resultX;
    private double resultY;
    private double resultZ;
    private final long yaw;
    private final long pitch;
    private final Material block;
    private final ArrayList<Location> blocks = new ArrayList<>();
    private final int id;
    public Map1_SlimeJump(World world, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, long yaw, long pitch, int arenaID, int id) {
        this.world = world;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.maxX = maxX;
        this.yaw = yaw;
        this.pitch = pitch;
        this.id = id;
        this.block = Material.matchMaterial(getFunctions().getString("arenas." + arenaID + ".2." + id + ".type"));
        runTaskTimer(FallGuys.instance(), 0, 30);
    }

    @Override
    public void run() {
        Random randomX = new Random();
        Random randomY = new Random();
        Random randomZ = new Random();
        resultX = randomX.nextDouble(minX, maxX);
        resultY = randomY.nextDouble(minY, maxY);
        resultZ = randomZ.nextDouble(minZ, maxZ);
        Location location = new Location(world, resultX, resultY, resultZ, yaw, pitch);
        world.getBlockAt(location).setType(block);
        if (blocks.size() > 1) {
            for (Location value : blocks) {
                world.getBlockAt(value).setType(Material.AIR);
            }
            blocks.clear();
        }
        blocks.add(location);
    }

    public int getId() {return id;}
}
