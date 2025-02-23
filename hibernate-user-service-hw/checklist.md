## Common mistakes (hibernate-user-service-hw)

* Let's make the email unique. We need to check the email during user registration and make the email a unique field in the database.
* Custom `AuthenticationException` and `RegistrationException` shouldn't be `RuntimeException`.
* `findByEmail()` - don't complicate with unnecessary variables, expect to receive only one String param 
  in method arguments.
* `findByEmail()` - should return Optional on both Dao and Service layers.
* Instead of `password.length() == 0` or `password.equals("")` use `isEmpty()` method of String class (if necessary).
* In `findByEmail()` method you can use `uniqueResultOptional()` method to retrieve User from Query.
* Create only one condition for throwing `AuthenticationException` in `login()` method. You may combine two checks: 
  whether the user has been found by login and does passwords match.
* Don't forget to use `transaction.rollback()` in your dao implementations.
* Don't use Dao in `authServiceImpl`.
* You should create salt and hash password in UserService `add()` method.
* Let's add private constructor to HashUtil.
* Don't add password and salt to the toString() method.
* Remember to add `catch` blocks for operations of all types on DAO layer.  
