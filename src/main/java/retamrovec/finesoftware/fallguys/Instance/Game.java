package retamrovec.finesoftware.fallguys.Instance;

import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.fallguys.Configs.Config;
import retamrovec.finesoftware.fallguys.Enums.GameState;
import retamrovec.finesoftware.fallguys.Events.FallGuysGameEnd;
import retamrovec.finesoftware.fallguys.FallGuys;
import retamrovec.finesoftware.fallguys.Handlers.ConfigHandler;
import retamrovec.finesoftware.fallguys.Handlers.FunctionsHandler;
import retamrovec.finesoftware.fallguys.Handlers.LanguageHandler;
import retamrovec.finesoftware.fallguys.Managers.FunctionManager;
import retamrovec.finesoftware.fallguys.PAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *
 * You don't have permission to copy this class.
 * @author RETAMROVEC
 *
 */
public class Game implements LanguageHandler, FunctionsHandler, ConfigHandler {

    private final Arena arena;
    private GameTime gameTime;
    public final FunctionManager functionManager;
    private final Config config;
    public final HashMap<UUID, Integer> levels;
    public final List<Integer> level;

    public Game(@NotNull Arena arena) {
        this.arena = arena;
        this.functionManager = new FunctionManager(arena.getId());
        this.gameTime = new GameTime(arena);
        this.levels = new HashMap<>();
        this.config = new Config(FallGuys.instance());
        this.level = new ArrayList<>();
    }

    public void start() {
        arena.setGamemode(GameMode.SURVIVAL);
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("game.start"), true)));
        arena.teleport(config.getArenaSpawn(arena.getId()));
        // LEVELS
        for (UUID uuid : arena.getPlayers()) {
            levels.put(uuid, 0);
        }

        /*

        FUNCTIONS IN-GAME

         */

        functionManager.startAll();
        arena.sendScoreboard();
        arena.sendTablist();
        gameTime.start();
    }

    public void qualify(@NotNull Player player, boolean spectator) {
        int playerLevel = levels.get(player.getUniqueId()) + 1;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.use(getLang().getString("player.qualified"), player)));
        Firework firework = newFirework(player);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder()
                .withColor(Color.YELLOW)
                .withColor(Color.RED)
                .with(Type.STAR)
                .withFlicker().build());
        meta.setPower(1);
        setFireworkMeta(firework, meta);
        levels.replace(player.getUniqueId(), playerLevel);
        if (levels.get(player.getUniqueId()) == 2) {
            FallGuysGameEnd a = new FallGuysGameEnd(arena.getPlayers(), player.getUniqueId(), arena);
            Bukkit.getPluginManager().callEvent(a);
            return;
        }
        if (spectator) {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

    public Firework newFirework(@NotNull Player player) {
        return player.getWorld().spawn(player.getLocation(), Firework.class);
    }

    public void setFireworkMeta(@NotNull Firework firework, @NotNull FireworkMeta meta) {
        firework.setFireworkMeta(meta);
    }

    public int getTime() {
        return gameTime.i;
    }

    public GameTime getGameTime() { return gameTime; }

    public void reset() {
        getGameTime().stop();
        gameTime = new GameTime(arena);
    }

    public HashMap<UUID, Integer> getLevels() {
        return levels;
    }
    public List<Integer> getLevel() {
        return level;
    }
}
