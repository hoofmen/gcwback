package com.hoofmen.gcwallet.giftcards;

import com.hoofmen.gcwallet.giftcards.model.GiftCard;
import com.hoofmen.gcwallet.giftcards.service.GiftCardService;
import com.hoofmen.gcwallet.shared.AppException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Osman H. on 9/25/18.
 */
@RestController
public class GiftCardAPI {

    @Autowired
    private GiftCardService giftCardService;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> health(){
        return new HashMap<String, String>(){{
            put("status","ok");
        }};
    }

    @RequestMapping(value = "/giftcard/{id}", method = RequestMethod.GET)
    public @ResponseBody GiftCard getById(@PathVariable(value = "id") ObjectId id) throws AppException {
        return giftCardService.getGiftCard(id);
    }

    @RequestMapping(value = "/giftcard/user/{id}", method = RequestMethod.GET)
    public @ResponseBody List<GiftCard> getByUserId(@PathVariable(value = "id") ObjectId id) throws AppException {
        return giftCardService.getGiftCardsByUserId(id);
    }

    @RequestMapping(value = "/giftcard", method = RequestMethod.POST)
    public @ResponseBody GiftCard saveGiftCard(@RequestBody GiftCard giftCard) throws AppException {
        return giftCardService.saveGiftCard(giftCard);
    }

    @RequestMapping(value = "/giftcard/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, String> deleteGiftCard(@PathVariable(value = "id") ObjectId id) throws AppException {
        giftCardService.deleteGiftCard(id);
        //TODO: What should it return here?
        return new HashMap<String, String>(){{
            put("deleted","done");
        }};
    }

    @RequestMapping(value = "/giftcard/{id}", method = RequestMethod.PUT)
    public @ResponseBody GiftCard updateGiftCard(@PathVariable(value = "id") ObjectId id,
                                                 @RequestBody GiftCard giftCard) throws AppException {
        return giftCardService.updateGiftCard(id, giftCard);
    }

    @RequestMapping(value = "/giftcard/all", method = RequestMethod.GET)
    public @ResponseBody List<GiftCard> getAll() throws AppException {
        return giftCardService.getGiftCardAll();
    }
}
