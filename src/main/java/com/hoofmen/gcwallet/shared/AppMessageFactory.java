package com.hoofmen.gcwallet.shared;

import lombok.Data;

import java.util.Date;

/**
 * Created by Osman H. on 10/1/18.
 */
public class AppMessageFactory {
    public static AppMessage buildAppMessage(String type, String code, String message, String devMessage) {
        Error error = new Error();
        error.setType(type);
        error.setCode(code);
        error.setMessage(message);
        error.setDevMessage(devMessage);
        AppMessage appMessage = new AppMessage();
        appMessage.setError(error);
        appMessage.setTimestamp(new Date());
        return appMessage;
    }
}

@Data
class AppMessage {
    Error error;
    Date timestamp;
}

@Data
class Error {
    private String type;
    private String code;
    private String message;
    private String devMessage;
}
