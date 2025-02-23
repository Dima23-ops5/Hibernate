# Common mistakes

* Check all your exceptions:
    - Wrong:
    ```java
         } catch (Exception e) {
            ...
            throw new RuntimeException("Can't create phone");
         }
     ```
     - Good:
     ```java
         } catch (Exception e) {
            ...
            throw new RuntimeException("Can't create phone " + phone, e);
         }
      ```

* Don't create redundant variables.

* Use `entrySet()` instead of `keySet()` if you use only one `for` loop.
  
* Be attentive with `Map<String, String[]> params`. Can we call `params.toString()`? What will be the output?
