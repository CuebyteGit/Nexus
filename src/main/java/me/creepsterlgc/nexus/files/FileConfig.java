package main.java.me.creepsterlgc.nexus.files;

import java.io.File;
import java.io.IOException;

import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class FileConfig {
	
	public static File file = new File("config/nexus/config.conf");
	public static ConfigurationLoader<CommentedConfigurationNode> manager = HoconConfigurationLoader.builder().setFile(file).build();
	public static CommentedConfigurationNode config = manager.createEmptyNode(ConfigurationOptions.defaults());

	public static void setup() {

		try {
			
			if (!file.exists()) {
				
				file.createNewFile();
				
				config.getNode("mysql", "use").setValue(false);
				config.getNode("mysql", "host").setValue("localhost");
				config.getNode("mysql", "port").setValue(3306);
				config.getNode("mysql", "username").setValue("root");
				config.getNode("mysql", "password").setValue("password");
				config.getNode("mysql", "database").setValue("minecraft");
				
				config.getNode("afk", "ENABLE_SYSTEM").setValue(true).setComment("Set players automatically to AFK?");
				config.getNode("afk", "TIMER_IN_SECONDS").setValue(180).setComment("Time in seconds after a player will be set to AFK.");
				config.getNode("afk", "KICK_ENABLE").setValue(false).setComment("Should AFK players be kicked?");
				config.getNode("afk", "KICK_AFTER").setValue(300).setComment("Time in seconds a player can be AFK after the kick happens.");
				
				config.getNode("limits", "MAX_TEMPBAN_TIME_IN_SECONDS").setValue(7200).setComment("Max time in seconds limited mods can ban.");
				config.getNode("limits", "MAX_MUTE_TIME_IN_SECONDS").setValue(600).setComment("Max time in seconds limited mods can mute.");
				
				config.getNode("list", "ORDER_BY_GROUPS").setValue(true).setComment("Should /list be ordered by groups?");
				config.getNode("list", "SHOW_PREFIX").setValue(true).setComment("Should /list display prefixes?");
				config.getNode("list", "SHOW_SUFFIX").setValue(true).setComment("Should /list display suffixes?");
				
				config.getNode("version").setValue(7);
				
		        manager.save(config);
				
			}
			
	        config = manager.load();
		     
		} catch (IOException e) { e.printStackTrace(); }
		
	}
	
	public static boolean MYSQL_USE() { return config.getNode("mysql", "use").getBoolean(); }
	public static String MYSQL_HOST() { return config.getNode("mysql", "host").getString(); }
	public static int MYSQL_PORT() { return config.getNode("mysql", "port").getInt(); }
	public static String MYSQL_USERNAME() { return config.getNode("mysql", "username").getString(); }
	public static String MYSQL_PASSWORD() { return config.getNode("mysql", "password").getString(); }
	public static String MYSQL_DATABASE() { return config.getNode("mysql", "database").getString(); }
	
	public static int LIMITS_MAX_TEMPBAN_TIME_IN_SECONDS() { return config.getNode("limits", "MAX_TEMPBAN_TIME_IN_SECONDS").getInt(); }
	public static int LIMITS_MAX_MUTE_TIME_IN_SECONDS() { return config.getNode("limits", "MAX_MUTE_TIME_IN_SECONDS").getInt(); }

	public static boolean AFK_ENABLE_SYSTEM() { return config.getNode("afk", "ENABLE_SYSTEM").getBoolean(); }
	public static double AFK_TIMER_IN_SECONDS() { return config.getNode("afk", "TIMER_IN_SECONDS").getDouble(); }
	public static boolean AFK_KICK_ENABLE() { return config.getNode("afk", "KICK_ENABLE").getBoolean(); }
	public static double AFK_KICK_AFTER() { return config.getNode("afk", "KICK_AFTER").getDouble(); }
	
	public static boolean CHAT_USE() { return config.getNode("chat", "use").getBoolean(); }
	public static String CHAT_FORMAT() { return config.getNode("chat", "format").getString(); }
	
	public static boolean LIST_ORDER_BY_GROUPS() { return config.getNode("list", "ORDER_BY_GROUPS").getBoolean(); }
	public static boolean LIST_SHOW_PREFIX() { return config.getNode("list", "SHOW_PREFIX").getBoolean(); }
	public static boolean LIST_SHOW_SUFFIX() { return config.getNode("list", "SHOW_SUFFIX").getBoolean(); }
	
	public static int ZONES_CLAIM_DEFAULT_PRIORITY() { return config.getNode("zones", "claim", "DEFAULT_PRIORITY").getInt(); }
	public static String ZONES_CLAIM_OVERLAPPING_LEVEL() { return config.getNode("zones", "claim", "OVERLAPPING_LEVEL").getString(); }
	
}
