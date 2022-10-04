package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import retamrovec.finesoftware.fallguys.Maps.Map1.SlimeJump;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;

import java.util.ArrayList;
import java.util.List;

public class FunctionManager implements FunctionsHandler {
    private final List<SlimeJump> slimeJumps = new ArrayList<>();

    public FunctionManager(int ArenaID) {
        for (String str : getFunctions().getConfigurationSection("arenas." + ArenaID + ".2").getKeys(false)) {
            int minX = Math.min(getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".from.x"), getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".to.x"));
            int minY = Math.min(getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".from.y"), getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".to.y"));
            int minZ = Math.min(getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".from.z"), getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".to.z"));
            int maxX = Math.max(getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".from.x"), getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".to.x"));
            int maxY = Math.max(getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".from.y"), getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".to.y"));
            int maxZ = Math.max(getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".from.z"), getFunctions().getInt("arenas." + ArenaID + ".2." + str + ".to.z"));
            slimeJumps.add(
                    new SlimeJump(Bukkit.getWorld(getFunctions().getString("arenas." + ArenaID + ".2." + str + ".from.world")),
                            minX, minY, minZ, maxX, maxY, maxZ,
                            getFunctions().getLong("arenas." + ArenaID + ".2." + str + ".to.z"),
                            getFunctions().getLong("arenas." + ArenaID + ".2." + str + ".to.z"),
                            ArenaID, Integer.parseInt(str), speedValue(ArenaID, str)));
        }
    }

    private long speedValue(int ArenaID, String str) {
        long speed = 20;
        if (speed != getFunctions().getLong("arenas." + ArenaID + ".2." + str + ".speed") && !(getFunctions().getLong("arenas." + ArenaID + ".2." + str + ".speed") == 0.0)) {
            return getFunctions().getLong("arenas." + ArenaID + ".2." + str + ".speed");
        }
        return speed;
    }

    public void startAll() {
        for (SlimeJump slimeJump : slimeJumps) {
            slimeJump.start();
        }
    }

    public ArrayList<Location> getSlimeJumps(int id) {
        return slimeJumps.get(id).getBlocks();
    }

    public List<SlimeJump> getSlimeJumps() {
        return slimeJumps;
    }
}
