var db = connect('127.0.0.1:27017/wifted');

print('* Wifted Database created');

db.createCollection("stores");
db.stores.save({ _id: ObjectId(), name: "Unimarc", img: "unimarc.png"});
db.stores.save({ _id: ObjectId(), name: "Tottus", img: "tottus.png"});
db.stores.save({ _id: ObjectId(), name: "Jumbo", img: "jumbo.png"});
db.stores.save({ _id: ObjectId(), name: "Santa Isabel", img: "santaisabel.png"});
db.stores.save({ _id: ObjectId(), name: "Cencosud", img: "cencosud.png"});
db.stores.save({ _id: ObjectId(), name: "Johnson's", img: "johnsons.png"});
db.stores.save({ _id: ObjectId(), name: "Paris", img: "paris.png"});
db.stores.save({ _id: ObjectId(), name: "Falabella", img: "falabella.png"});
db.stores.save({ _id: ObjectId(), name: "Ripley", img: "ripley.png"});
db.stores.save({ _id: ObjectId(), name: "Google Play", img: "googleplay.png"});
db.stores.save({ _id: ObjectId(), name: "Gap", img: "gap.png"});
db.stores.save({ _id: ObjectId(), name: "iTunes", img: "itunes.png"});

db.createCollection("giftcards");
db.giftcards.save({_id: ObjectId(), user_id: ObjectId("5babce075c63a15a48160132"), value: 25.00, code: { code: "1234 5678 9101 1213 1415", type: "BAR_CODE" }, store:{ _id: ObjectId(), name: "Google Play", img: "googleplay.png"}});
db.giftcards.save({_id: ObjectId(), user_id: ObjectId("5babce075c63a15a48160132"), value: 50.00, code: { code: "1233-5455-9101-000-1", type: "QR_CODE" },      store:{ _id: ObjectId(), name: "iTunes", img: "itunes.png"}});
db.giftcards.save({_id: ObjectId(), user_id: ObjectId("5babce075c63a15a48160132"), value: 15.00, code: { code: "5544 2344 9101 1213 2777", type: "BAR_CODE" }, store:{ _id: ObjectId(), name: "Gap", img: "gap.png"}});

db.createUser({user: "user", pwd: "user123", roles: [{ role: "readWrite", db: "wifted" }]});