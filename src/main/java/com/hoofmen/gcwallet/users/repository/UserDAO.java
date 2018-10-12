package com.hoofmen.gcwallet.users.repository;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by Osman H. on 9/26/18.
 */
@Data
@Document(collection = "user")
public class UserDAO {
    private ObjectId id;
    private String username;
    private String name;
    @Field("lastname")
    private String lastName;
    private String email;
    private String phone;
}
