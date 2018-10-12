package com.hoofmen.gcwallet.shared;

/**
 * Created by Osman H. on 9/25/18.
 */
public interface AppConstants {
    String HEADER_TOKEN = "X-AUTH-TOKEN";
    String TOKEN = "thisisaverysecrettoken";

    interface SuccessCodes {
        String GIFTCARD_SAVED = "ok-001";
    }
    interface ErrorCodes {
        String UNKNOWN = "err-000";
        String NO_GIFTCARDS_FOUND = "err-001";
        String URL_PARAM_NOT_FOUND = "err-002";
        String COULD_NOT_CONNECT_TO_DB = "err-003";
    }
    interface MessageTypes {
        String DATA = "Data";
        String AUTH = "Authorization";
        String DBCONN = "Database Connection";
    }

    interface SuccessMessages {
        String GIFTCARD_SAVED = "GiftCard saved correctly!";
    }
    interface ErrorMessages {
        String UNKNOWN = "An unknown error occurred.";
        String NO_GIFT_CARDS_FOUND = "No Gift Cards found!";
        String URL_PARAM_NOT_FOUND = "URL Parameters missing";
        String COULD_NOT_CONNECT_TO_DB = "Could not connect to the database, please try again later";
    }
}
