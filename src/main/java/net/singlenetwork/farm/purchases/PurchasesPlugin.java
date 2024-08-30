package net.singlenetwork.farm.purchases;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.singlenetwork.farm.purchases.commands.CreditsCommand;
import net.singlenetwork.farm.purchases.commands.SiteCommand;
import net.singlenetwork.farm.purchases.database.MySQLProvider;
import net.singlenetwork.farm.purchases.feat.configuration.message.MessageConfiguration;
import net.singlenetwork.farm.purchases.feat.data.cache.UserCache;
import net.singlenetwork.farm.purchases.feat.data.factory.UserFactory;
import net.singlenetwork.farm.purchases.feat.data.storage.UserStorage;
import net.singlenetwork.farm.purchases.feat.data.task.UserTask;
import net.singlenetwork.farm.purchases.feat.hook.PlaceholderHook;
import net.singlenetwork.farm.purchases.feat.utils.CustomPlugin;
import net.singlenetwork.farm.purchases.view.PurchaseView;
import net.singlenetwork.farm.purchases.view.crates.CratesView;
import net.singlenetwork.farm.purchases.view.vip.VipView;
import org.bukkit.Bukkit;

@Getter
public class PurchasesPlugin extends CustomPlugin {

    private MySQLProvider mySQLProvider;
    private UserStorage userStorage;
    private UserFactory userFactory;
    private UserCache userCache;
    private MessageConfiguration messageConfiguration;
    private UserTask userTask;


    public void onLoad() {
        saveDefaultConfig();

        mySQLProvider = new MySQLProvider(
                getConfig().getString("mysql.host"),
                getConfig().getInt("mysql.port"),
                getConfig().getString("mysql.database"),
                getConfig().getString("mysql.username"),
                getConfig().getString("mysql.password")
        );
        messageConfiguration = new MessageConfiguration(this);

        userStorage = new UserStorage();

        userFactory = new UserFactory(this);

        userCache = new UserCache();


        userTask = new UserTask(this);

    }

    public void onEnable() {
        mySQLProvider.init();
        userTask.run();
        registry();

        getLogger().info("Purchases plugin enabled.");

    }

    public void onDisable() {
        mySQLProvider.closeConnection();
        Bukkit.getOnlinePlayers().forEach(userFactory::remove);
    }

    void registry() {
        registerViews(
                new PurchaseView(this),
                new VipView(this),
                new CratesView(this)
        );

        CommandManager commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new CreditsCommand(this));
        commandManager.registerCommand(new SiteCommand(this));

        new PlaceholderHook().register();
    }
    public static PurchasesPlugin getInstance() {
        return PurchasesPlugin.getPlugin(PurchasesPlugin.class);
    }
}
