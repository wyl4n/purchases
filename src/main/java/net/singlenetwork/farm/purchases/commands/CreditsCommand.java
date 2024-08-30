package net.singlenetwork.farm.purchases.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.feat.configuration.provider.MessageProvider;
import net.singlenetwork.farm.purchases.feat.utils.Formatter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CreditsCommand extends BaseCommand {
    private final PurchasesPlugin plugin;

    @CommandAlias("creditos")
    @Subcommand("credits|credits")
    public void execute(Player player) {
        MessageProvider.provide(player, "balance", ImmutableMap.of("<balance>",
                Formatter.priceToString(plugin.getUserCache().getById(player.getUniqueId()).getCredits())));

    }

    @CommandAlias("addcredits")

    public void add(CommandSender sender, Player target, double value) {
        final Player player = (Player) sender;

        plugin.getUserCache().getById(target.getUniqueId()).setCredits(value);

        plugin.getUserCache().getById(target.getUniqueId()).setCredits(plugin.getUserCache().getById(target.getUniqueId()).getCredits());

        MessageProvider.provide(player, "add",  ImmutableMap.of("<balance>",
                Formatter.priceToString(plugin.getUserCache().getById(target.getUniqueId()).getCredits()),
                "<target>", target.getName()));
    }

    @CommandAlias("setcredits")

    public void set(CommandSender sender, Player target, double value) {
        final Player player = (Player) sender;

        plugin.getUserCache().getById(target.getUniqueId()).setCredits(value);

        MessageProvider.provide(player, "set", ImmutableMap.of("<balance>",
                Formatter.priceToString(value),
                "<target>", target.getName()));

        player.sendMessage(
                "§aVocê setou §7"
                        + Formatter.priceToString(value)
                        + " §a créditos para o jogador §7" + target.getName() + "§a."
        );
    }
}
