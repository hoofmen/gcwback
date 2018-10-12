package com.hoofmen.gcwallet.shared;

import lombok.Data;

/**
 * Created by Osman H. on 10/1/18.
 */
@Data
public class AppException extends Exception {
    protected String code;
    protected String message;
    protected String devMessage;
}
