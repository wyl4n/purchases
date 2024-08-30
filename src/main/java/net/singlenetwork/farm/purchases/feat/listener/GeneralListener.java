package net.singlenetwork.farm.purchases.feat.listener;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class GeneralListener implements Listener {

    private final PurchasesPlugin plugin;

    @EventHandler
    public void on(PlayerJoinEvent event) {
        val player = event.getPlayer();

        plugin.getUserFactory().create(player);

    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        val player = event.getPlayer();

        plugin.getUserFactory().remove(player);
    }

}

