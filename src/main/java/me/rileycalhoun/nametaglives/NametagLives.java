package me.rileycalhoun.nametaglives;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public final class NametagLives extends JavaPlugin implements Listener {

    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        registerHealthBar();
        registerNameTag();

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        scoreboard.getTeam("blue").addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        scoreboard.getTeam("blue").removePlayer(event.getPlayer());
    }

    public void registerHealthBar() {
        if(scoreboard.getObjective("health") != null) scoreboard.getObjective("health").unregister();
        Objective objective = scoreboard.registerNewObjective("health", Criteria.HEALTH, "health");
        objective.setDisplayName(ChatColor.RED + "♥");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    public void registerNameTag() {
        if(scoreboard.getTeam("blue") != null) scoreboard.getTeam("blue").unregister();
        Team team = scoreboard.registerNewTeam("blue");
        team.setPrefix(ChatColor.BLUE + "");
    }

}
