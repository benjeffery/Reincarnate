package com.fragondemand.MrTrousers.Reincarnate;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;


public class SpawnLocations {
        private static ConcurrentHashMap<String, Location> spawn_locations = new ConcurrentHashMap<String,Location>();
        private static ConcurrentHashMap<String, Location> spawn_sign_block_locations = new ConcurrentHashMap<String,Location>();
        
        public static Location getSpawnLocation(String player_name) {
            return spawn_locations.get(player_name);
        }
        
        public static Location getSpawnSignBlockLocation(String player_name) {
            return spawn_sign_block_locations.get(player_name);
        }
        
        public static void setSpawnLocation(String player_name, Location location, Block sign_block) {
            spawn_locations.put(player_name, location);
            
            //Re-write the signs
            //The new sign is fresh so lets go ahead and add our name to it
            String short_name = player_name;
            if (player_name.length() > 15)
                short_name = player_name.substring(0, 14);
            
            Sign clicked_sign = (Sign)sign_block.getState();
            if (clicked_sign.getLine(1).indexOf(short_name) != -1){
                //Name already there
            } else if (clicked_sign.getLine(2).indexOf(short_name) != -1){
                clicked_sign.setLine(2,clicked_sign.getLine(1));
                clicked_sign.setLine(1,short_name);
                clicked_sign.update();
            } else
            {
                clicked_sign.setLine(3,clicked_sign.getLine(2));
                clicked_sign.setLine(2,clicked_sign.getLine(1));
                clicked_sign.setLine(1,short_name);
                clicked_sign.update();
            }
            
            //Wipe the old sign
            Location old_sign_block_location = spawn_sign_block_locations.put(player_name, sign_block.getLocation());
            if (old_sign_block_location == null) {
                return;
            }
            
            int x = old_sign_block_location.getBlockX();
            int y = old_sign_block_location.getBlockY();
            int z = old_sign_block_location.getBlockZ();
            Block old_sign_block = old_sign_block_location.getWorld().getBlockAt(x, y, z);
            
            System.out.println(spawn_sign_block_locations);
            //Check its still a sign, if so update it!!!
            if (old_sign_block.getType() == Material.WALL_SIGN &&
                  old_sign_block.getState() instanceof Sign &&
                  old_sign_block != sign_block) {
                Sign old_sign = (Sign)old_sign_block.getState();
                if (old_sign.getLine(0).toLowerCase().indexOf("reincarnate") != -1) {
                    if (old_sign.getLine(1).indexOf(short_name) != -1) {
                        old_sign.setLine(1,"");
                        old_sign.update();
                    }
                    if (old_sign.getLine(2).indexOf(short_name) != -1) {
                        old_sign.setLine(2,"");
                        old_sign.update();
                    }
                    if (old_sign.getLine(3).indexOf(short_name) != -1) {
                        old_sign.setLine(3,"");
                        old_sign.update();
                    }
                }
            }                    
        }
}
