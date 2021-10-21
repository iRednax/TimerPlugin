package me.iRednax.Timer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public final class Main extends JavaPlugin {
	
	public int time;
	public boolean paused;
	private BukkitRunnable runnable;
	private static Main main;
	
	@Override
	public void onEnable() {
		main = this;
		
		getCommand("timer").setExecutor(new TimerCommand());
		
		time = 0;
		runnable = new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(player -> {
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§e§l" + shortInteger(time)));
				});
				
				
				if(!paused) {
					time++;
				}
				
				if(paused) {
					return;
				}
			}
		};
		runnable.runTaskTimer(this, 0, 20);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public String shortInteger(int duration) {
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1)
            seconds = duration;
        if (hours <= 9) {
            string = String.valueOf(string) + "0" + hours + ":";
        } else {
            string = String.valueOf(string) + hours + ":";
        }
        if (minutes <= 9) {
            string = String.valueOf(string) + "0" + minutes + ":";
        } else {
            string = String.valueOf(string) + minutes + ":";
        }
        if (seconds <= 9) {
            string = String.valueOf(string) + "0" + seconds;
        } else {
            string = String.valueOf(string) + seconds;
        }
        return string;
    }

	public static Main getMain() {
		return main;
	}
	
	public BukkitRunnable getRunnable() {
		return runnable;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
}
