package me.iRednax.Timer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("pause")) {
				Bukkit.broadcastMessage("Der Timer wurde gestoppt!");
				Main.getMain().paused = true;
			} else if(args[0].equalsIgnoreCase("resume")) {
				Bukkit.broadcastMessage("Der Timer wurde gestartet!");
				Main.getMain().paused = false;
			} else if(args[0].equalsIgnoreCase("reset")) {
				Bukkit.broadcastMessage("Der Timer is nun zurückgesezt!");
				Main.getMain().setTime(0);
			}
		} else if(args.length == 2){ 
			if(args[0].equalsIgnoreCase("setTime")) {
				try {
					int v = Integer.parseInt(args[1]);
					Main.getMain().setTime(v);
					Bukkit.broadcastMessage("Der Timer ist nun auf " + v + "!");
				} catch(Exception e) {
					sender.sendMessage("§cBitte gib eine Zahl ein!!");
				}
				Main.getMain().getRunnable().cancel();
			}
		} else {
			sender.sendMessage("§c/timer <pause | resume | setTime | resume>");
		}
		return false;
	}

}
