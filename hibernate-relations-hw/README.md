# Hibernate relations

### Intro

In this HW you will have the following structure in DAO implementations:
```java
public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
```
Such a structure is a good example of Dependency Injection implementation. Please, do not modify this.
Thus in methods `add(Entity entity)` and `get(Long id)` of the DAO layer you need just to refer to the SessionFactory instance of the parent AbstractDao class:

```java
public Entity add(Entity entity) {
    Session session = null;
    Transaction transaction = null;
    try {
        session = factory.openSession();
        transaction = ...
```

### Requirements

Your task is to implement the following steps:
- add required hibernate dependencies
- complete implementation of all classes in the `mate/academy/hibernate/relations/model` package
- complete implementation of all classes in the `mate/academy/hibernate/relations/dao/impl` package
- create your custom unchecked DataProcessingException and throw it in the catch block on the DAO layer
- complete implementation of all classes in the `mate/academy/hibernate/relations/service/impl/` package
- create a `hibernate.cfg.xml` file and implement `mate/academy/hibernate/relations/util/HibernateUtil` class (this is required for the next step)
- make `mate/academy/Main.java` work (you should be able to run `main()` method without any errors)

#### [Try to avoid these common mistakes, while solving task](./checklist.md)
