package com.hoofmen.gcwallet.giftcards.repository;

import com.hoofmen.gcwallet.stores.repository.StoreDAO;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by Osman H. on 9/25/18.
 */
@Data
@Document(collection = "giftcards")
public class GiftCardDAO implements Serializable {
    @Id
    private ObjectId id;
    @Field("user_id")
    private ObjectId userId;
    private Double value;
    private Code code;
    private StoreDAO store;
}

@Data
class Code {
    private String code;
    private String type;
}
