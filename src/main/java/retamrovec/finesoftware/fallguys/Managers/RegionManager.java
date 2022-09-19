package retamrovec.finesoftware.fallguys.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class RegionManager {

    public boolean isInRegion(Location location1, Location location2, Location location, World world, World world2) {
        final double x = location.getX();
        final double y = location.getY();
        final double z = location.getZ();
        final double x1 = location1.getX();
        final double y1 = location1.getY();
        final double z1 = location1.getZ();
        final double x2 = location2.getX();
        final double y2 = location2.getY();
        final double z2 = location2.getZ();
        return world == world2 && (x > x1 && x < x2) && (y > y1 && y < y2) && (z > z1 && z < z2);
    }

    public Location transformLocation(World world, double x, double y, double z, long yaw, long pitch) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    public Location transformLocation(String world, double x, double y, double z, long yaw, long pitch) {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
