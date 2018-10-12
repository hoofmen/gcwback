package com.hoofmen.gcwallet.shared;

import com.hoofmen.gcwallet.giftcards.exception.GiftCardNotFoundException;
import com.hoofmen.gcwallet.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Osman H. on 10/1/18.
 */
//TODO: This class needs some refactoring
@EnableWebMvc
@ControllerAdvice
@RestController
public class AppExceptionHandler {
    static final Logger logger = LogUtils.buildLogClient(AppExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(value= HttpStatus.OK)
    @ExceptionHandler(GiftCardNotFoundException.class)
    public @ResponseBody
    AppMessage giftCardNotFound(HttpServletRequest req, GiftCardNotFoundException ex) {
        String errorMessage = messageSource.getMessage(ex.getCode(), null, LocaleContextHolder.getLocale());
        String errorCode = ex.getCode();
        logger.error("No Gift Cards found [{}]", ex.getCode());
        return AppMessageFactory.buildAppMessage(AppConstants.MessageTypes.DATA, errorCode, errorMessage, ex.devMessage);
    }

    @ResponseStatus(value= HttpStatus.OK)
    @ExceptionHandler(CouldNotConnectToDatabaseException.class)
    public @ResponseBody AppMessage couldNotConnectToDataBase(HttpServletRequest req, CouldNotConnectToDatabaseException ex) {
        String errorMessage = messageSource.getMessage(ex.getCode(), null, LocaleContextHolder.getLocale());
        String errorCode = ex.getCode();
        logger.error("Could not connect to the database. {}", ex.getCode());
        return AppMessageFactory.buildAppMessage(AppConstants.MessageTypes.DBCONN, errorCode, errorMessage, ex.devMessage);
    }
}
