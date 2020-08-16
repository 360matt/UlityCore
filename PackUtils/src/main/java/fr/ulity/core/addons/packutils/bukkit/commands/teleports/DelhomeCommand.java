package fr.ulity.core.addons.packutils.bukkit.commands.teleports;

import fr.ulity.core.addons.packutils.bukkit.MainBukkitPackUtils;
import fr.ulity.core.addons.packutils.bukkit.methods.HomeMethods;
import fr.ulity.core.api.bukkit.CommandManager;
import fr.ulity.core.api.bukkit.LangBukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

import static org.bukkit.Bukkit.getPluginManager;

public class DelhomeCommand extends CommandManager.Assisted implements Listener {
    public DelhomeCommand(CommandMap commandMap, JavaPlugin plugin) {
        super(plugin, "delhome");
        addPermission("ulity.packutils.delhome");
        addArrayTabbComplete(0, "ulity.packutils.delhome", new String[]{},  new String[]{"§Homes"});
        if (MainBukkitPackUtils.enabler.canEnable(getName())) {
            getPluginManager().registerEvents(this, getPlugin());
            registerCommand(commandMap);
        }
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e) {
        String request = e.getBuffer();
        String[] args = request.split(" ");

        if (args[0].replace("/", "").equalsIgnoreCase(getName()))
            if (e.getCompletions().contains("§Homes"))
                e.setCompletions(Arrays.asList(HomeMethods.getHomeListName((Player) e.getSender())));
    }

    @Override
    public void exec(CommandSender sender, Command command, String label, String[] args) {
        if (requirePlayer()) {
            if (arg.inRange(1, 1)) {
                Player player = (Player) sender;

                if (HomeMethods.isHomeExist(player, args[0])) {
                    HomeMethods.delHome(player, args[0]);
                    LangBukkit.prepare("commands.delhome.expressions.deleted")
                            .variable("home", args[0])
                            .sendPlayer(player);
                } else
                    LangBukkit.prepare("commands.delhome.expressions.unknown_home")
                            .variable("home", args[0])
                            .sendPlayer(player);

            } else
                setStatus(Status.SYNTAX);
        }
    }

}