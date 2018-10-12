
use gcw

db.createCollection("accounts")
db.accounts.save({ _id: ObjectId(), username: "hoofmen", password: "osman123" })

db.createCollection("accounttokens")
db.accounttokens.save({ _id: ObjectId(), account_id: ObjectId("5babcd2e5c63a15a48160130"), username: "hoofmen", token: "thisisaverysecuredtoken", expiration_date: new Date() })

db.createCollection("users")
db.users.save({ _id: ObjectId(), account_id: ObjectId("5babcd2e5c63a15a48160130"), username: "hoofmen", img: 'user.png', details: { name: "Osman", lastname: "Romero", email: "hoofmen@gmail.com", phone: "9252341750"}})

db.createCollection("stores")
db.stores.save({ _id: ObjectId(), name: "Unimarc", img: "unimarc.png"})
db.stores.save({ _id: ObjectId(), name: "Tottus", img: "tottus.png"})
db.stores.save({ _id: ObjectId(), name: "Jumbo", img: "jumbo.png"})
db.stores.save({ _id: ObjectId(), name: "Santa Isabel", img: "santaisabel.png"})
db.stores.save({ _id: ObjectId(), name: "Cencosud", img: "cencosud.png"})
db.stores.save({ _id: ObjectId(), name: "Johnson's", img: "johnsons.png"})
db.stores.save({ _id: ObjectId(), name: "Paris", img: "paris.png"})
db.stores.save({ _id: ObjectId(), name: "Falabella", img: "falabella.png"})
db.stores.save({ _id: ObjectId(), name: "Ripley", img: "ripley.png"})
db.stores.save({ _id: ObjectId(), name: "Google Play", img: "googleplay.png"})
db.stores.save({ _id: ObjectId(), name: "Gap", img: "gap.png"})
db.stores.save({ _id: ObjectId(), name: "iTunes", img: "itunes.png"})

db.createCollection("giftcards")
db.giftcards.save({_id: ObjectId(), user_id: ObjectId("5babce075c63a15a48160132"), value: 25.00, code: { code: "1234 5678 9101 1213 1415", type: "BAR_CODE" }, store:{ _id: ObjectId(), name: "Google Play", img: "googleplay.png"}})
db.giftcards.save({_id: ObjectId(), user_id: ObjectId("5babce075c63a15a48160132"), value: 50.00, code: { code: "1233-5455-9101-000-1", type: "QR_CODE" },      store:{ _id: ObjectId(), name: "iTunes", img: "itunes.png"}})
db.giftcards.save({_id: ObjectId(), user_id: ObjectId("5babce075c63a15a48160132"), value: 15.00, code: { code: "5544 2344 9101 1213 2777", type: "BAR_CODE" }, store:{ _id: ObjectId(), name: "Gap", img: "gap.png"}})

db.createUser({user: "user", pwd: "user123", roles: [{ role: "readWrite", db: "gcw" }]})

mongo localhost:27017/gcw -u user -p user123

X-AUTH-TOKEN  thisisaverysecrettoken

http://localhost:8080/gcw/api/giftcard/all

http://localhost:8080/gcw/api/giftcard/5babce775c63a15a48160141

POST - http://localhost:8080/gcw/api/giftcard
{
	"user": {
      "id": "5babce075c63a15a48160132"
    },
	"value": 10000.00,
	"store": {
		"id": "5babce075c63a15a48160132",
		"name": "CMM Prat"
	}
}
