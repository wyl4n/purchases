package net.singlenetwork.farm.purchases.feat.hook;

import lombok.SneakyThrows;
import lombok.val;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.feat.utils.Formatter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderHook extends PlaceholderExpansion {
    @NotNull
    @Override
    public String getIdentifier() {
        return "purchases";
    }

    @NotNull
    @Override
    public String getAuthor() {
        return "wylan";
    }

    @NotNull
    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    @SneakyThrows
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("credits")) {
            val user = PurchasesPlugin.getPlugin(PurchasesPlugin.class).getUserCache().getById(player.getUniqueId());
            if (user == null) return "0";

            return Formatter.priceToString(user.getCredits());
        }

        return null;
    }
}

