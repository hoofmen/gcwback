package com.hoofmen.gcwallet.giftcards.exception;

import com.hoofmen.gcwallet.shared.AppException;
import lombok.Data;

/**
 * Created by Osman H. on 10/1/18.
 */
@Data
public class GiftCardNotFoundException extends AppException {
    public GiftCardNotFoundException(String code) {
        this.code = code;
    }

    public GiftCardNotFoundException(String code, String devMessage) {
        this.code = code;
        this.message = message;
        this.devMessage = devMessage;
    }
}