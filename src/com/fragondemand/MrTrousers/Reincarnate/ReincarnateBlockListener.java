package com.fragondemand.MrTrousers.Reincarnate;
import com.fragondemand.MrTrousers.Reincarnate.SpawnLocations;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRightClickEvent;

/**
 * Reincarnate block listener
 * @author MrTrousers
 */
public class ReincarnateBlockListener extends BlockListener {
//    private final Reincarnate plugin;

    public ReincarnateBlockListener(final Reincarnate plugin) {
//        this.plugin = plugin;
    }

    public void onBlockRightClick(BlockRightClickEvent event)
	{
        Block clicked = event.getBlock();
    	if (clicked.getType() != Material.WALL_SIGN)
    	    return;
    	if (!(clicked.getState() instanceof Sign))
    	    return;
        Sign clicked_sign = (Sign)clicked.getState();
        if (clicked_sign.getLine(0).toLowerCase().indexOf("reincarnate") == -1)
            return;
        String name = event.getPlayer().getName();
        SpawnLocations.setSpawnLocation(name, event.getPlayer().getLocation(), clicked);
        
        
	}
}
