package net.singlenetwork.farm.purchases.feat.data.cache;

import lombok.RequiredArgsConstructor;
import net.singlenetwork.farm.purchases.feat.data.User;
import net.singlenetwork.farm.purchases.feat.utils.Cache;
import java.util.UUID;

@RequiredArgsConstructor
public class UserCache extends Cache<User> {

    public User getById(UUID id) {
        return getCached(user -> user.getId().equals(id));
    }


}
