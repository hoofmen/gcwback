package com.hoofmen.gcwallet.accounts.repository;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Osman H. on 9/26/18.
 */
@Data
@Document(collection = "accounts")
public class AccountDAO {
    @Id
    private ObjectId id;
    private String username;
    private String password;
}
