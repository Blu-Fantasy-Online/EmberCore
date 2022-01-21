package com.github.Dewynion.embercore.listener;


import com.github.Dewynion.embercore.CoreLoadPriority;
import com.github.Dewynion.embercore.EmberCore;
import com.github.Dewynion.embercore.reflection.PluginLoader;
import com.github.Dewynion.embercore.reflection.annotations.OnEnable;
import com.github.Dewynion.embercore.util.armorstand.ArmorStandUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

@OnEnable(priority = CoreLoadPriority.PLUGIN_EVENT_LISTENER_PRIORITY)
public final class PluginEventListener implements Listener {
    /**
     * Exists to clear out unnecessary assembly information for
     * disabled plugins.
     */
    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin() instanceof JavaPlugin) {
            JavaPlugin plugin = (JavaPlugin) event.getPlugin();
            EmberCore.info("Plugin %s has been disabled. Clearing cached information.",
                    plugin.getName());
            if (PluginLoader.registered(plugin))
                PluginLoader.remove(plugin);
            if (ArmorStandUtil.registered(plugin))
                ArmorStandUtil.removeAll(plugin);
        }
    }
}
