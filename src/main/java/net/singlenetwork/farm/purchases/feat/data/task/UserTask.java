package net.singlenetwork.farm.purchases.feat.data.task;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import org.bukkit.Bukkit;

@RequiredArgsConstructor
public class UserTask implements Runnable {

    private final PurchasesPlugin plugin;


    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach($ -> {
            val user = plugin.getUserCache().getById($.getUniqueId());
            plugin.getUserStorage().update(user);

        });
    }
}
