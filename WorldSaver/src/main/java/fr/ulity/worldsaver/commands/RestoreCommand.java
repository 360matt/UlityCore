package fr.ulity.worldsaver.commands;

import fr.ulity.core.api.CommandManager;
import fr.ulity.worldsaver.api.methods.restore.ReadJson;
import fr.ulity.worldsaver.api.methods.Warner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class RestoreCommand extends CommandManager {
    public RestoreCommand(CommandMap commandMap, JavaPlugin plugin) {
        super(plugin, "restore");
        addDescription("§etest");
        addUsage("§etest");

        registerCommand(commandMap);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Warner warner = new Warner(sender, Warner.Type.RESTORE);



        ReadJson.build("test", "world", warner);
        sender.sendMessage("§eWola");
        return true;
    }

}