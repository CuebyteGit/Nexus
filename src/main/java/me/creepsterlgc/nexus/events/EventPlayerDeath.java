package main.java.me.creepsterlgc.nexus.events;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DestructEntityEvent;

import main.java.me.creepsterlgc.nexus.customized.NexusDatabase;
import main.java.me.creepsterlgc.nexus.customized.NexusPlayer;
import main.java.me.creepsterlgc.nexus.utils.SerializeUtils;


public class EventPlayerDeath {

    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
    	
    	if(event.getTargetEntity() instanceof Player == false) return;
    	Player player = (Player) event.getTargetEntity();

    	String world = player.getWorld().getName();
    	double x = player.getLocation().getX();
    	double y = player.getLocation().getY();
    	double z = player.getLocation().getZ();
    	double yaw = 0;
    	double pitch = 0;
    	String location = SerializeUtils.location(world, x, y, z, yaw, pitch);
    	NexusPlayer p = NexusDatabase.getPlayer(player.getUniqueId().toString());
    	p.setLastdeath(location);
    	p.update();
    	
    }
	
}
