package net.singlenetwork.farm.purchases.feat.data;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class User {

    private final UUID id;
    private String username;
    private double credits;

}
