package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import retamrovec.finesoftware.fallguys.Functions.Map1_SlimeJump;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;

import java.util.ArrayList;
import java.util.List;

public class FunctionManager implements FunctionsHandler {
    private List<Map1_SlimeJump> slimeJumps = new ArrayList<>();

    public FunctionManager(int ArenaID) {
        for (String str : getFunctions().getConfigurationSection("arenas." + ArenaID + ".2").getKeys(false)) {
            slimeJumps.add(
                    new Map1_SlimeJump(Bukkit.getWorld(getFunctions().getString("arenas." + ArenaID + ".2." + str + ".from.world")),
                            getFunctions().getDouble("arenas." + ArenaID + ".2." + str + ".from.x"),
                            getFunctions().getDouble("arenas." + ArenaID + ".2." + str + ".from.y"),
                            getFunctions().getDouble("arenas." + ArenaID + ".2." + str + ".from.z"),
                            getFunctions().getDouble("arenas." + ArenaID + ".2." + str + ".to.x"),
                            getFunctions().getDouble("arenas." + ArenaID + ".2." + str + ".to.y"),
                            getFunctions().getDouble("arenas." + ArenaID + ".2." + str + ".to.z"),
                            getFunctions().getLong("arenas." + ArenaID + ".2." + str + ".to.z"),
                            getFunctions().getLong("arenas." + ArenaID + ".2." + str + ".to.z"),
                            ArenaID, Integer.parseInt(str)));
        }
    }

    public List<Map1_SlimeJump> getSlimeJumps() {return slimeJumps;}

    public Map1_SlimeJump getSlimeJump(int id) {
        for (Map1_SlimeJump slimeJump : slimeJumps) {
            if (slimeJump.getId() == id) {
                return slimeJump;
            }
        }
        return null;
    }
}
