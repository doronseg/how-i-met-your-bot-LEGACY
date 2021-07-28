# Oscar Stinson Discord Bot

This repository is for the Oscar Stinson Discord bot from the [Oscar Stinson Discord Server](https://discord.gg/himym). 

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

### Initializing Local Project
To initialize, just clone the repository, In the main folder, which would be named OscarStinson_Bot if you cloned it, you need to create 2 files, `db.db`, and `.env`.

You will also need to create your own bot, on thee [Discord Developer Portal](https://discord.com/developers), and copy the token.

### .env file
```
TOKEN=YOUR TOKEN GOES HERE

DB=jdbc:jdbc:sqlite:db.db
```

### Database
As of right now, we have 2 SQLITE databases, if you want to contribute and make an update that includes a new database, make sure to create an issue first.
```
#AFK System Table
CREATE TABLE "afk" ("UID" TEXT UNIQUE, "REASON" TEXT)
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
