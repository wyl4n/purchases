package net.singlenetwork.farm.purchases.feat.data.factory;

import lombok.val;
import lombok.RequiredArgsConstructor;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.feat.data.User;
import org.bukkit.entity.Player;


@RequiredArgsConstructor
public class UserFactory {
    private final PurchasesPlugin plugin;

    public void create(Player player) {
        val registry = plugin.getUserCache();
        val storage = plugin.getUserStorage();

        val find = storage.find(player.getUniqueId().toString());
        if(find != null) {
            registry.addCachedElement(find);
            return;
        }

        User user = User.builder()
                .id(player.getUniqueId())
                .username(player.getName())
                .credits(0)
                .build();

        storage.insert(user);
        registry.addCachedElement(user);
    }

    public void remove(Player player) {
        val registry = plugin.getUserCache();
        val storage = plugin.getUserStorage();

        val find = registry.getById(player.getUniqueId());
        if(find == null) return;

        storage.update(find);
        registry.removeCachedElement(find);
    }
}