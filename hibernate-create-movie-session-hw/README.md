# Hibernate - create CinemaHall and MovieSession

Let's continue working on our Cinema project.

Your task is to implement the following steps:
- create `src/main/resources/hibernate.cfg.xml`
- create `CinemaHall` class
- create `MovieSession` class - be careful adding relations with `Movie` and `CinemaHall`
- add implementations of `getAll()` method to `MovieDaoImpl` and `MovieServiceImpl`
- create `CinemaHallDaoImpl` and `CinemaHallServiceImpl`.
The service layer should have the following methods:
```java
    CinemaHall add(CinemaHall cinemaHall);

    CinemaHall get(Long id);

    List<CinemaHall> getAll();
```
- create `MovieSessionDaoImpl` and `MovieSessionServiceImpl`.
The service layer should have the following methods:
```java
    MovieSession add(MovieSession movieSession);

    MovieSession get(Long id);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
```
Keep in mind that the method `findAvailableSessions()` expects a `LocalDate` that represents the day 
chosen by a customer to visit our cinema, so your task here is to return all MovieSessions 
that will be running between 00:00 and 23:59:59 on that particular day.
- in the `mate/academy/Main.main()` method create instances of new services 
using injector and test all their methods
- use annotations and the annotation injector located in the `lib` folder

#### [Try to avoid these common mistakes, while solving task](./checklist.md)

### Model structure 
![pic](Hibernate_Cinema_Uml.png)
