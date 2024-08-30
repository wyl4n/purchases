package net.singlenetwork.farm.purchases.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.view.PurchaseView;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SiteCommand extends BaseCommand {
    private final PurchasesPlugin plugin;

    @CommandAlias("site")
    @Subcommand("comprar")

    public void execute(Player player) {
        plugin.getViewFrame().open(PurchaseView.class, player);
    }
}