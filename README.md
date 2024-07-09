# Learning Datomic

Code notes while learning [Datomic](https://www.datomic.com/).

## References

- [Datomic/local setup instructions](https://docs.datomic.com/datomic-local.html)
- [Datomic/Client API tutorial](https://docs.datomic.com/client-tutorial/client.html)
- [datomic-pro-docker](https://github.com/gbaptista/datomic-pro-docker)

## Configuration

I created `~/.datomic/local.edn` with the following contents:

```clojure
{:storage-dir "/Users/blairnangle/personal/learning-datomic/storage"}
```

## Concepts

### Entities

Every datum in Datomic includes a database-unique entity id. They never change.

There are three ways to uniquely identify an entity:

- entity id
- ident - this associates a keyword with an entity id
- lookup ref

### Datum (plural: datoms)

TBC

### Squuids

TBC

## License

Distributed under an [MIT License](./LICENSE).
