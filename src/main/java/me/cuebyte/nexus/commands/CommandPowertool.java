package main.java.me.cuebyte.nexus.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import main.java.me.cuebyte.nexus.customized.NexusDatabase;
import main.java.me.cuebyte.nexus.customized.NexusPlayer;
import main.java.me.cuebyte.nexus.utils.PermissionsUtils;

import org.spongepowered.api.Game;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class CommandPowertool implements CommandCallable {

	public Game game;

	public CommandPowertool(Game game) {
		this.game = game;
	}

	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {

		if(sender instanceof Player == false) { sender.sendMessage(Text.builder("Cannot be run by the console!").color(TextColors.RED).build()); return CommandResult.success(); }

		if(!PermissionsUtils.has(sender, "nexus.powertool")) { sender.sendMessage(Text.builder("You do not have permissions!").color(TextColors.RED).build()); return CommandResult.success(); }

		Player player = (Player) sender;
		NexusPlayer p = NexusDatabase.getPlayer(player.getUniqueId().toString());

		if(!player.getItemInHand(HandTypes.MAIN_HAND).isPresent()) {
			sender.sendMessage(Text.of(TextColors.RED, "You need to have an item in your hand!"));
			return CommandResult.success();
		}

		ItemStack i = player.getItemInHand(HandTypes.MAIN_HAND).get();

		String id = i.getItem().getId().replaceAll("minecraft:", "");

		HashMap<String, String> powertools = p.getPowertools();

		if(arguments.equalsIgnoreCase("")) {
			if(!powertools.containsKey(id)) {
				sender.sendMessage(Text.of(TextColors.RED, "This item is not bound!"));
				return CommandResult.success();
			}

			powertools.remove(id);
			p.setPowertools(powertools);
			NexusDatabase.addPlayer(p.getUUID(), p);

			sender.sendMessage(Text.of(TextColors.GOLD, id, TextColors.GRAY, " is not a powertool anymore."));

			return CommandResult.success();
		}

		String command = arguments;
		command = command.replaceAll("/", "");

		sender.sendMessage(Text.of(TextColors.GOLD, id, TextColors.GRAY, " is now a powertool."));
		sender.sendMessage(Text.of(TextColors.GRAY, "Command: ", TextColors.GOLD, "/", command));

		powertools.put(id, command);
		p.setPowertools(powertools);
		NexusDatabase.addPlayer(p.getUUID(), p);

		return CommandResult.success();

	}

	 @Override
	public Text getUsage(CommandSource sender) { return null; }
	 @Override
	public Optional<Text> getHelp(CommandSource sender) { return null; }
	 @Override
	public Optional<Text> getShortDescription(CommandSource sender) { return null; }
	 @Override
	public List<String> getSuggestions(CommandSource arg0, String arg1,	@Nullable Location<World> arg2) throws CommandException { return null; }
	 @Override
	public boolean testPermission(CommandSource sender) { return false; }

}
