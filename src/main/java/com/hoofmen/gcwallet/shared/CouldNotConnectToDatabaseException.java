package com.hoofmen.gcwallet.shared;

/**
 * Created by Osman H. on 10/1/18.
 */
public class CouldNotConnectToDatabaseException extends AppException {
    public CouldNotConnectToDatabaseException(String code, String devMessage) {
        this.code = code;
        this.message = message;
        this.devMessage = devMessage;
    }
}
