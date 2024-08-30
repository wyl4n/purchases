package net.singlenetwork.farm.purchases.view.crates;

import lombok.RequiredArgsConstructor;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.context.RenderContext;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.feat.utils.ItemBuilder;
import net.singlenetwork.farm.purchases.view.PurchaseView;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class CratesView extends View {

    private final PurchasesPlugin plugin;

    @Override
    public void onInit(@NotNull ViewConfigBuilder config) {
        config.title("")
                .size(4)
                .cancelOnClick()
                .cancelOnDrag()
                .cancelOnDrop()
                .cancelOnPickup();
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        final Player player = render.getPlayer();

        render.slot(31).withItem(new ItemBuilder(
                Material.SKULL_ITEM)
                .texture("3d4deef18397ded73888f2a44b1dba6e85c953b8afe7221deeff0ceeb6ac5e3")
                .name("§6Voltar")
                .build()).onClick((handler) -> {
            plugin.getViewFrame().open(PurchaseView.class, player);
        });

        render.slot(13).withItem(new ItemBuilder(
                Material.STONE_BUTTON)
                .name("§6Abertura automática!")
                .lore(
                        "§7Abra suas caixas de",
                        "§7forma automática e",
                        "§7sem nenhum esforço!",
                        "",
                        "§f Preço: §76,500 créditos",
                        "",
                        "" + (plugin.getUserCache().getById(player.getUniqueId()).getCredits() < 6500 ? "§cSaldo insuficiente." : "§6Clique para adquirir!")
                )
                .build() ).onClick((handler) -> {
            if (!(plugin.getUserCache().getById(player.getUniqueId()).getCredits() < 6500)) {
                plugin.getUserCache().getById(player.getUniqueId()).setCredits(plugin.getUserCache().getById(player.getUniqueId()).getCredits() - 6500);
                Bukkit.dispatchCommand(player, "mochila crate automatic give " + player.getName());
                player.playSound(player.getLocation(), Sound.PORTAL_TRAVEL, 2.0F, 2.0F);
                player.sendMessage("§aVocê adquiriu 1x abertura automática, parabéns!");
                player.closeInventory();
            }
        });
    }
}


