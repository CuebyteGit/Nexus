package main.java.me.cuebyte.nexus.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;


public class CommandMail implements CommandCallable {

	@Override
	public CommandResult process(CommandSource sender, String arguments) throws CommandException {

		String[] args = arguments.split(" ");

		if(args[0].equalsIgnoreCase("send")) { new CommandMailSend(sender, args); return CommandResult.success(); }
		else if(args[0].equalsIgnoreCase("read")) { new CommandMailRead(sender, args); return CommandResult.success(); }
		else if(args[0].equalsIgnoreCase("clear")) { new CommandMailClear(sender, args); return CommandResult.success(); }
		else if(args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(Text.of(TextColors.YELLOW, "Mail Help"));
			sender.sendMessage(Text.of(TextColors.GOLD, "/mail send <player> <message>"));
			sender.sendMessage(Text.of(TextColors.GOLD, "/mail read"));
			sender.sendMessage(Text.of(TextColors.GOLD, "/mail clear"));
		}
		else {
			sender.sendMessage(Text.of(TextColors.YELLOW, "Mail Help"));
			sender.sendMessage(Text.of(TextColors.GOLD, "/mail send <player> <message>"));
			sender.sendMessage(Text.of(TextColors.GOLD, "/mail read"));
			sender.sendMessage(Text.of(TextColors.GOLD, "/mail clear"));
		}

		return CommandResult.success();

	}

	private final Text usage = Text.builder("Usage: /mail help").color(TextColors.GOLD).build();
	private final Text help = Text.builder("Help: /mail help").color(TextColors.GOLD).build();
	private final Text description = Text.builder("Nexus | Mail Command").color(TextColors.GOLD).build();
	private List<String> suggestions = new ArrayList<String>();
	private String permission = "";

	@Override
	public Text getUsage(CommandSource sender) { return usage; }
	@Override
	public Optional<Text> getHelp(CommandSource sender) { return Optional.of(help); }
	@Override
	public Optional<Text> getShortDescription(CommandSource sender) { return Optional.of(description); }
	@Override
	public List<String> getSuggestions(CommandSource arg0, String arg1,	@Nullable Location<World> arg2) throws CommandException { return suggestions; }
	@Override
	public boolean testPermission(CommandSource sender) { return permission.equals("") ? true : sender.hasPermission(permission); }

}
