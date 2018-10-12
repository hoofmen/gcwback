package com.hoofmen.gcwallet.giftcards.repository;

import com.hoofmen.gcwallet.giftcards.exception.GiftCardNotFoundException;
import com.hoofmen.gcwallet.giftcards.model.Code;
import com.hoofmen.gcwallet.giftcards.model.GiftCard;
import com.hoofmen.gcwallet.shared.AppConstants;
import com.hoofmen.gcwallet.shared.CouldNotConnectToDatabaseException;
import com.hoofmen.gcwallet.stores.model.Store;
import com.hoofmen.gcwallet.stores.repository.StoreDAO;
import com.hoofmen.gcwallet.users.model.User;
import com.hoofmen.gcwallet.utils.LogUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osman H. on 9/25/18.
 */
@Service
public class GiftCardServiceRepo {
    static final Logger logger = LogUtils.buildLogClient(GiftCardServiceRepo.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public GiftCard getGiftCard(ObjectId id) throws GiftCardNotFoundException, CouldNotConnectToDatabaseException {
        logger.debug("getGiftCard with id: {}", id.toString());
        GiftCardDAO gcDao;
        try {
            gcDao = mongoTemplate.findById(id, GiftCardDAO.class);
        } catch (Exception ex) {
            logger.error("{} - {}", AppConstants.ErrorMessages.COULD_NOT_CONNECT_TO_DB, ex.toString());
            throw new CouldNotConnectToDatabaseException(AppConstants.ErrorCodes.COULD_NOT_CONNECT_TO_DB, ex.getMessage());
        }
        if (gcDao == null) {
            logger.error(AppConstants.ErrorMessages.NO_GIFT_CARDS_FOUND);
            throw new GiftCardNotFoundException(AppConstants.ErrorCodes.NO_GIFTCARDS_FOUND, "id: " + id.toString());
        }
        GiftCard giftCard = convertGiftCardFromDao(gcDao);
        return giftCard;
    }

    public List<GiftCard> getGiftCardsByUserId(ObjectId id) throws GiftCardNotFoundException, CouldNotConnectToDatabaseException {
        logger.debug("getGiftCardsByUserId with user_id: {}", id.toString());
        List<GiftCardDAO> gcDaoList;
        try {
            Query query = Query.query(Criteria.where("user_id").is(id));
            gcDaoList = mongoTemplate.find(query, GiftCardDAO.class);
        } catch (Exception ex) {
            logger.error("{} - {}", AppConstants.ErrorMessages.COULD_NOT_CONNECT_TO_DB, ex.toString());
            throw new CouldNotConnectToDatabaseException(AppConstants.ErrorCodes.COULD_NOT_CONNECT_TO_DB, ex.getMessage());
        }
        if (CollectionUtils.isEmpty(gcDaoList)) {
            logger.error(AppConstants.ErrorMessages.NO_GIFT_CARDS_FOUND);
            throw new GiftCardNotFoundException(AppConstants.ErrorCodes.NO_GIFTCARDS_FOUND, "user_id: " + id.toString());
        }
        List<GiftCard> giftCardList = new ArrayList<>();
        gcDaoList.forEach(gcDao -> giftCardList.add(convertGiftCardFromDao(gcDao)));
        return giftCardList;
    }

    public GiftCard saveUpdateGiftCard(GiftCard giftCard) throws GiftCardNotFoundException, CouldNotConnectToDatabaseException {
        logger.debug("saveGiftCard");
        // Code to generate a new GiftCard Id if the instance is new
        if (giftCard.getId() == null) {
            giftCard.setId(new ObjectId());
            logger.debug("This is a new GiftCard! No GiftCard Id was present so assigning this one [Id: {}] before saving it!", giftCard.getId());
        } else {
            logger.debug("This is an existing GiftCard! GiftCard with [id: {}] will be updated!", giftCard.getId());
        }

        GiftCardDAO gcDao;
        try {
            // First: Save the GiftCard
            mongoTemplate.save(convertGiftCardToDao(giftCard));
            // Second: Querying for the same GiftCard in the database
            gcDao = mongoTemplate.findById(giftCard.getId(), GiftCardDAO.class);
        } catch (Exception ex) {
            logger.error("{} - {}", AppConstants.ErrorMessages.COULD_NOT_CONNECT_TO_DB, ex.toString());
            throw new CouldNotConnectToDatabaseException(AppConstants.ErrorCodes.COULD_NOT_CONNECT_TO_DB, ex.getMessage());
        }
        if (gcDao == null) {
            logger.error(AppConstants.ErrorMessages.NO_GIFT_CARDS_FOUND);
            throw new GiftCardNotFoundException(AppConstants.ErrorCodes.NO_GIFTCARDS_FOUND, "id: " + giftCard.getId());
        }
        GiftCard gc = convertGiftCardFromDao(gcDao);
        return gc;
    }

    public void deleteGiftCard(ObjectId id) throws CouldNotConnectToDatabaseException {
        logger.debug("deleteGiftCard with id: {}", id.toString());
        try {
            Query query = Query.query(Criteria.where("_id").is(id));
            mongoTemplate.remove(query, GiftCardDAO.class);
        } catch (Exception ex) {
            logger.error("{} - {}", AppConstants.ErrorMessages.COULD_NOT_CONNECT_TO_DB, ex.toString());
            throw new CouldNotConnectToDatabaseException(AppConstants.ErrorCodes.COULD_NOT_CONNECT_TO_DB, ex.getMessage());
        }
    }

    public List<GiftCard> getGiftCardAll() throws GiftCardNotFoundException, CouldNotConnectToDatabaseException {
        logger.debug("getGiftCardAll");
        List<GiftCardDAO> gcDaoList;
        try {
            gcDaoList = mongoTemplate.findAll(GiftCardDAO.class);
        } catch (Exception ex) {
            logger.error("{} - {}", AppConstants.ErrorMessages.COULD_NOT_CONNECT_TO_DB, ex.toString());
            throw new CouldNotConnectToDatabaseException(AppConstants.ErrorCodes.COULD_NOT_CONNECT_TO_DB, ex.getMessage());
        }
        if (CollectionUtils.isEmpty(gcDaoList)) {
            logger.error(AppConstants.ErrorMessages.NO_GIFT_CARDS_FOUND);
            throw new GiftCardNotFoundException(AppConstants.ErrorCodes.NO_GIFTCARDS_FOUND);
        }
        List<GiftCard> giftCardList = new ArrayList<>();
        gcDaoList.forEach(gcDao -> giftCardList.add(convertGiftCardFromDao(gcDao)));
        return giftCardList;
    }

    private GiftCard convertGiftCardFromDao(GiftCardDAO dao) {
        GiftCard giftCard = new GiftCard();
        giftCard.setId(dao.getId());
        giftCard.setValue(dao.getValue());
        Code code = new Code();
        code.setCode(dao.getCode().getCode());
        code.setType(dao.getCode().getType());
        giftCard.setCode(code);
        User user = new User();
        user.setId(dao.getUserId());
        giftCard.setUser(user);
        Store store = new Store();
        store.setId(dao.getStore().getId());
        store.setName(dao.getStore().getName());
        store.setImg(dao.getStore().getImg());
        giftCard.setStore(store);

        return giftCard;
    }

    private GiftCardDAO convertGiftCardToDao(GiftCard giftCard) {
        GiftCardDAO dao = new GiftCardDAO();
        dao.setId(giftCard.getId());
        dao.setUserId(giftCard.getUser().getId());
        dao.setValue(giftCard.getValue());
        com.hoofmen.gcwallet.giftcards.repository.Code code = new com.hoofmen.gcwallet.giftcards.repository.Code();
        code.setCode(giftCard.getCode().getCode());
        code.setType(giftCard.getCode().getType());
        dao.setCode(code);
        StoreDAO store = new StoreDAO();
        store.setId(giftCard.getStore().getId());
        store.setName(giftCard.getStore().getName());
        store.setImg(giftCard.getStore().getImg());
        dao.setStore(store);

        return dao;
    }
}
