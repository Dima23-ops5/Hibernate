## The first and the most important rule: DO NOT COMMIT YOUR REAL DB CREDENTIALS
Better commit some common credentials. For example, for the password field: `1234` or `password` or `root`, etc.

# Common mistakes

* Don't just copy-paste code from other dao (make sure your exception messages and variable names are correct for a particular class.
* You don't need to set an ID for your entity explicitly. It'll be done by Hibernate.
* Don't add dependencies that you don't use to `pom.xml`.
* Don't add an alfa version of dependencies, they might work unpredictably and cause problems.
* Use try with finally, where you use transaction and try with resources with Read operations.
* Add a private default constructor to the `HibernateUtil` class in order to prevent creating `HibernateUtil` objects.
* Don't create a default constructor when it is not needed.
* Remember to add `catch` blocks for operations of all types on the DAO layer.  
* Do not push redundant files or folders (iml, .idea, target, etc).
* Run checkstyle and fix issues before push.
