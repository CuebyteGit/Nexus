package main.java.me.cuebyte.nexus.commands;

import java.util.Map.Entry;

import main.java.me.cuebyte.nexus.customized.NexusDatabase;
import main.java.me.cuebyte.nexus.customized.NexusPlayer;
import main.java.me.cuebyte.nexus.utils.PermissionsUtils;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class CommandNexusDatabase {

	public CommandNexusDatabase(CommandSource sender, String[] args) {

		if(!PermissionsUtils.has(sender, "nexus.nexus.database")) { sender.sendMessage(Text.builder("You do not have permissions!").color(TextColors.RED).build()); return; }

		int homes = 0;
		for(Entry<String, NexusPlayer> e : NexusDatabase.getPlayers().entrySet()) {
			NexusPlayer p = e.getValue(); homes += p.getHomes().size();
		}

		sender.sendMessage(Text.of(TextColors.YELLOW, "Nexus Database:"));
		sender.sendMessage(Text.of(TextColors.GRAY, "Players: ", TextColors.GOLD, NexusDatabase.getPlayers().size()));
		sender.sendMessage(Text.of(TextColors.GRAY, "Bans: ", TextColors.GOLD, NexusDatabase.getBans().size()));
		sender.sendMessage(Text.of(TextColors.GRAY, "Mutes: ", TextColors.GOLD, NexusDatabase.getMutes().size()));
		sender.sendMessage(Text.of(TextColors.GRAY, "Spawns: ", TextColors.GOLD, NexusDatabase.getSpawns().size()));
		sender.sendMessage(Text.of(TextColors.GRAY, "Homes: ", TextColors.GOLD, homes));
		sender.sendMessage(Text.of(TextColors.GRAY, "Warps: ", TextColors.GOLD, NexusDatabase.getWarps().size()));
		sender.sendMessage(Text.of(TextColors.GRAY, "Tickets: ", TextColors.GOLD, NexusDatabase.getTickets().size()));

	}

}
