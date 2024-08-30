package net.singlenetwork.farm.purchases.feat.data.storage;

import lombok.val;
import net.singlenetwork.farm.purchases.PurchasesPlugin;
import net.singlenetwork.farm.purchases.database.MySQLProvider;
import net.singlenetwork.farm.purchases.feat.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserStorage {
    private final MySQLProvider service = PurchasesPlugin.getInstance().getMySQLProvider();

    public void insert(User user) {
        try (Connection connection = service.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `system_purchases` VALUES(?,?,?);")) {
                statement.setString(1, user.getId().toString());
                statement.setString(2, user.getUsername());
                statement.setDouble(3, user.getCredits());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void update(User user) {
        try (Connection connection = service.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE `system_purchases` SET credits=? WHERE user_id=?;")) {
                statement.setDouble(1, user.getCredits());
                statement.setString(2, user.getId().toString());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public User find(String id) {
        try (Connection connection = service.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `system_purchases` WHERE user_id=?;")) {
                statement.setString(1, id);
                try (ResultSet set = statement.executeQuery()) {
                    if (!set.next()) return null;

                    val uid = UUID.fromString(set.getString("user_id"));
                    val name = set.getString("user_name");
                    val credits = set.getDouble("credits");

                    return User.builder()
                            .id(uid).username(name)
                            .credits(credits)
                            .build();

                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}