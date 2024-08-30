package net.singlenetwork.farm.purchases.feat.configuration.provider;

import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.feat.configuration.message.MessageConfiguration;
import net.singlenetwork.farm.purchases.feat.utils.HelperUtil;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Map;

public class MessageProvider {
    private static final PurchasesPlugin PLUGIN = PurchasesPlugin.getInstance();

    public static void provide(CommandSender sender, String value) {
        final String[] find = find(value);
        if(find == null) return;

        sender.sendMessage(find);
    }

    public static void provide(CommandSender sender, String value, Map<String, String> replace) {
        final String[] find = find(value);
        if(find == null) return;

        HelperUtil.replace(replace, Arrays.asList(find))
                .forEach(sender::sendMessage);
    }

    private static String[] find(String value) {
        final MessageConfiguration configuration = PLUGIN.getMessageConfiguration();
        final Map<String, String[]> cache = configuration.getCache();

        return cache.get(value);
    }
}

