package main.java.me.creepsterlgc.nexus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.me.creepsterlgc.nexus.customized.NexusDatabase;
import main.java.me.creepsterlgc.nexus.customized.NexusMute;
import main.java.me.creepsterlgc.nexus.customized.NexusPlayer;
import main.java.me.creepsterlgc.nexus.utils.PermissionsUtils;

import org.spongepowered.api.Game;
import org.spongepowered.api.text.Text;

import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;


public class CommandUnmute implements CommandCallable {

	public Game game;

	public CommandUnmute(Game game) {
		this.game = game;
	}

	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {

		String[] args = arguments.split(" ");

		if(!PermissionsUtils.has(sender, "nexus.unmute")) { sender.sendMessage(Text.builder("You do not have permissions!").color(TextColors.RED).build()); return CommandResult.success(); }

		if(arguments.equalsIgnoreCase("")) { sender.sendMessage(usage); return CommandResult.success(); }
		if(args.length != 1) { sender.sendMessage(usage); return CommandResult.success(); }

		NexusPlayer player = NexusDatabase.getPlayer(NexusDatabase.getUUID(args[0].toLowerCase()));
		if(player == null) { sender.sendMessage(Text.builder("Player not found!").color(TextColors.RED).build()); return CommandResult.success(); }

		NexusMute mute = NexusDatabase.getMute(player.getUUID());

		if(mute == null) {
			sender.sendMessage(Text.builder("Player is not muted!").color(TextColors.RED).build());
			return CommandResult.success();
		}

		mute.delete();
		NexusDatabase.removeMute(player.getUUID());

		sender.sendMessage(Text.of(TextColors.GOLD, player.getName(), TextColors.GRAY, " has been unmuted."));

		return CommandResult.success();

	}

	private final Text usage = Text.builder("Usage: /unmute <player>").color(TextColors.GOLD).build();
	private final Text help = Text.builder("Help: /unmute <player>").color(TextColors.GOLD).build();
	private final Text description = Text.builder("Nexus | Unmute Command").color(TextColors.GOLD).build();
	private List<String> suggestions = new ArrayList<String>();
	private String permission = "";

	@Override
	public Text getUsage(CommandSource sender) { return usage; }
	@Override
	public Optional<Text> getHelp(CommandSource sender) { return Optional.of(help); }
	@Override
	public Optional<Text> getShortDescription(CommandSource sender) { return Optional.of(description); }
	@Override
	public List<String> getSuggestions(CommandSource sender, String args) throws CommandException { return suggestions; }
	@Override
	public boolean testPermission(CommandSource sender) { return permission.equals("") ? true : sender.hasPermission(permission); }

}
