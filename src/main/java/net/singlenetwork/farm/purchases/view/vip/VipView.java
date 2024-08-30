package net.singlenetwork.farm.purchases.view.vip;

import lombok.RequiredArgsConstructor;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.context.RenderContext;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.feat.utils.ItemBuilder;
import net.singlenetwork.farm.purchases.view.PurchaseView;
import net.singlenetwork.farm.purchases.view.crates.CratesView;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class VipView extends View {

    private final PurchasesPlugin plugin;

    @Override
    public void onInit(@NotNull ViewConfigBuilder config) {
        config.title("")
                .size(5)
                .cancelOnClick()
                .cancelOnDrag()
                .cancelOnDrop()
                .cancelOnPickup();
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        final Player player = render.getPlayer();

        render.slot(40).withItem(new ItemBuilder(
                Material.SKULL_ITEM)
                .texture("3d4deef18397ded73888f2a44b1dba6e85c953b8afe7221deeff0ceeb6ac5e3")
                .name("§6Voltar")
                .build()).onClick((handler) -> {
            plugin.getViewFrame().open(PurchaseView.class, player);
        });
    }
}
