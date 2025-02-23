## Common mistakes (hibernate-shopping-cart-hw)

* Check the correct relation in MovieSession entity. 
* Model `Ticket` has relationships with `MovieSession` and `User` entities. 
    Be careful choosing the type of these relationships. 
    __Remember__ that one user can buy many tickets, and many users can attend one movie session.
* Don't cast result of `query.getSingleResult()` or `query.uniqueResult()`, it works just fine without it:
    - Wrong:
    ```java
           return (ShoppingCart) query.getSingleResult();
    ```
    - Good:
    ```java
           return query.getSingleResult();
    ```
* Use `@MapsId` with `ShoppingCart`. Think about whether to use an annotation `@GeneratedValue` in `ShoppingCart`.
* For saving ShoppingCart use `save()` method instead of `persist()`. 
The reason is that `ShoppingCart` object will have the `transient` state before the session opened, but its `User` object will be `detached`. 
`persist()` method throws `PersistenceException` if working with detached entities, when `save()` returns it to managed state.
* Use method `registerNewShoppingCart()` in `AuthenticationServiceImpl` class in `register()` method.
* Try to think what should be called first `userService.add(user)` or `registerNewShoppingCart()`.
* Try to think what should you do first in method `addSession`: 
`ticketDao.add(ticket);` or `shoppingCartService.update()`
* Remember to add `catch` blocks for operations of all types on DAO layer.   
