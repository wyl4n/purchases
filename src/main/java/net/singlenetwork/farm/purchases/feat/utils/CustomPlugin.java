package net.singlenetwork.farm.purchases.feat.utils;

import lombok.Getter;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewFrame;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public abstract class CustomPlugin extends JavaPlugin {

    private final ViewFrame viewFrame = ViewFrame.create(this);

    public void registerListener(Listener... listeners) {
        final PluginManager pluginManager = Bukkit.getPluginManager();

        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }
    public void registerViews(View... views) {
        viewFrame.with(views).register();
    }
}
