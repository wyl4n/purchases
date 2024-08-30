package net.singlenetwork.farm.purchases.view;

import lombok.RequiredArgsConstructor;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.context.RenderContext;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.feat.utils.Formatter;
import net.singlenetwork.farm.purchases.feat.utils.ItemBuilder;
import net.singlenetwork.farm.purchases.view.crates.CratesView;
import net.singlenetwork.farm.purchases.view.vip.VipView;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class PurchaseView extends View {

    private final PurchasesPlugin plugin;

    @Override
    public void onInit(@NotNull ViewConfigBuilder config) {
        config.title("Site")
                .size(5)
                .cancelOnClick()
                .cancelOnDrag()
                .cancelOnDrop()
                .cancelOnPickup();
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        final Player player = render.getPlayer();

        render.slot(19).withItem(new ItemBuilder(
                Material.SKULL_ITEM)
                .name("§6Informações")
                .lore(
                        "§7Créditos é uma moeda",
                        "§7universal do servidor.",
                        "",
                        "§f Você possui: §7" + plugin.getUserCache().getById(player.getUniqueId()).getCredits() + "§7 créditos",
                        "",
                        "§7Adquira-os em:",
                        "§floja.singlenetwork.net"
                )
                .skullOwner(player.getName())
                .build());

        render.slot(12).withItem(new ItemBuilder(
                Material.DIAMOND)
                .name("§6Vips")
                .lore(
                        "§7Adquira VIPs e tenha",
                        "§7diversas vantagens.",
                        "",
                        "§6Clique para acessar."
                )
                .build()).onClick((handler) -> {
            plugin.getViewFrame().open(VipView.class, player);

        });

        render.slot(13).withItem(new ItemBuilder(
                Material.TRIPWIRE_HOOK)
                .name("§6Caixas")
                .lore(
                        "§7Adquira itens que",
                        "§7lhe ajudarão nas",
                        "§7caixas.",
                        "",
                        "§6Clique para acessar."
                )
                .build()).onClick((handler) -> {
            plugin.getViewFrame().open(CratesView.class, player);
        });
    }

}
