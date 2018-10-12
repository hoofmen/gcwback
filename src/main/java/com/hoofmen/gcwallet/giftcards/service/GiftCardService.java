package com.hoofmen.gcwallet.giftcards.service;

import com.hoofmen.gcwallet.giftcards.model.GiftCard;
import com.hoofmen.gcwallet.giftcards.repository.GiftCardServiceRepo;
import com.hoofmen.gcwallet.shared.AppException;
import com.hoofmen.gcwallet.utils.LogUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Osman H. on 9/25/18.
 */
@Service
public class GiftCardService {
    static final Logger logger = LogUtils.buildLogClient(GiftCardService.class);

    //TODO: Validation Service needed

    @Autowired
    private GiftCardServiceRepo giftCardServiceRepo;

    public GiftCard getGiftCard(ObjectId id) throws AppException {
        return giftCardServiceRepo.getGiftCard(id);
    }

    public List<GiftCard> getGiftCardsByUserId(ObjectId id) throws AppException {
        return giftCardServiceRepo.getGiftCardsByUserId(id);
    }

    public GiftCard saveGiftCard(GiftCard giftCard) throws AppException {
        return giftCardServiceRepo.saveUpdateGiftCard(giftCard);
    }

    public void deleteGiftCard(ObjectId id) throws AppException {
        giftCardServiceRepo.deleteGiftCard(id);
    }

    public GiftCard updateGiftCard(ObjectId id, GiftCard giftCard) throws AppException {
        giftCard.setId(id);
        return giftCardServiceRepo.saveUpdateGiftCard(giftCard);
    }

    public List<GiftCard> getGiftCardAll() throws AppException {
        return giftCardServiceRepo.getGiftCardAll();
    }
}
