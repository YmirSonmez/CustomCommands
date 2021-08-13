package me.ymir.cc;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class CustomCommands extends PluginBase{

    private static final HashMap<String,Object[]> commands = new HashMap<>();

    @Override
    public void onLoad() {
        saveResource("commands.yml");
        init();
    }

    private void init(){
        Config config = new Config(this.getDataFolder()+"/commands.yml");
        List<ConfigSection> cmds = config.get("commands",new ArrayList<>());
        for(ConfigSection cmd : cmds){
            if(!cmd.exists("name")){
                continue;
            }
            Object[] args = new Object[7];
            args[0] = cmd.getString("name").toLowerCase();
            args[1] = cmd.getString("perm","");
            args[2] = cmd.getString("desc","Server Command");
            args[3] = cmd.getStringList("allies");
            args[4] = cmd.getStringList("messages");
            args[5] = cmd.getStringList("formContent");
            args[6] = cmd.get("particle");
            commands.put((String) args[0],args);
            PluginCommand command = new PluginCommand((String) args[0],this);
            command.setDescription("Server Command");
            command.setPermission((String) args[1]);
            if(args[2] != null){
                for(String arg1 : (List<String>) args[3]){
                    commands.put(arg1.toLowerCase(),args);
                }
            }
            command.setAliases(((List<String>) (args[3])).toArray(new String[(((List<String>) args[3]).size())]));
            this.getServer().getCommandMap().register("Server",command);
            this.getLogger().info(command.getName()+" §2yüklendi...");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(commands.containsKey(commandLabel.toLowerCase())){
            Object[] cm = commands.get(commandLabel.toLowerCase());
            if(cm[4] != null){
                for(String msg : (List<String>) cm[4]){
                    sender.sendMessage(msg);
                }
            }
        }
        return false;
    }
}
