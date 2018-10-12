package com.hoofmen.gcwallet.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoofmen.gcwallet.giftcards.model.GiftCard;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Osman H. on 9/25/18.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private ObjectId id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private List<GiftCard> giftCards;
}
