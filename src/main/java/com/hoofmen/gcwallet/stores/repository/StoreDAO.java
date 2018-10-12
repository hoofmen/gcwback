package com.hoofmen.gcwallet.stores.repository;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Osman H. on 9/25/18.
 */
@Data
@Document(collection = "stores")
public class StoreDAO {
    private ObjectId id;
    private String name;
    private String img;
}
