package com.hoofmen.gcwallet.accounts.repository;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

/**
 * Created by Osman H. on 9/26/18.
 */
@Data
@Document(collection = "accounttokens")
public class AccountTokenDAO {
    @Id
    private ObjectId id;
    private String username;
    private String token;
    @Field("expiration_date")
    private Timestamp expirationDate;
}
