package main.java.me.cuebyte.nexus.commands;

import java.util.HashMap;

import main.java.me.cuebyte.nexus.customized.NexusDatabase;
import main.java.me.cuebyte.nexus.customized.NexusHome;
import main.java.me.cuebyte.nexus.customized.NexusPlayer;
import main.java.me.cuebyte.nexus.utils.PermissionsUtils;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class CommandHomeDelete {

	public CommandHomeDelete(CommandSource sender, String[] args) {

		if(sender instanceof Player == false) { sender.sendMessage(Text.builder("Cannot be run by the console!").color(TextColors.RED).build()); return; }

		if(!PermissionsUtils.has(sender, "nexus.home.delete")) { sender.sendMessage(Text.builder("You do not have permissions!").color(TextColors.RED).build()); return; }

		if(args.length < 1 || args.length > 2) { sender.sendMessage(Text.of(TextColors.GOLD, "Usage: ", TextColors.GRAY, "/home delete [name]")); return; }

		Player player = (Player) sender;
		NexusPlayer p = NexusDatabase.getPlayer(player.getUniqueId().toString());

		String name = "default"; if(args.length == 2) name = args[1].toLowerCase();
		NexusHome home = p.getHome(name);

		if(home == null) { sender.sendMessage(Text.builder("Home does not exist!").color(TextColors.RED).build()); return; }

		home.delete();

		HashMap<String, NexusHome> homes = p.getHomes();
		homes.remove(name);
		p.setHomes(homes);

		sender.sendMessage(Text.of(TextColors.GRAY, "Home ", TextColors.GOLD, name, TextColors.GRAY, " has been removed."));

	}

}
