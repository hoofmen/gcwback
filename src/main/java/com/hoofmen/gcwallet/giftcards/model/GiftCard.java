package com.hoofmen.gcwallet.giftcards.model;

import com.hoofmen.gcwallet.stores.model.Store;
import com.hoofmen.gcwallet.users.model.User;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * Created by Osman H. on 9/25/18.
 */
@Data
public class GiftCard {
    private ObjectId id;
    private Double value;
    private Code code;
    private User user;
    private Store store;
}

