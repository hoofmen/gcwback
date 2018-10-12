package com.hoofmen.gcwallet.stores.model;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 * Created by Osman H. on 9/25/18.
 */
@Data
public class Store {
    private ObjectId id;
    private String name;
    private String img;
}
