# Assignment 3: Spring Boot paths

Cloned from [jeffreyjseamanppu/gs-spring-boot](https://github.com/jeffreyjseamanppu/gs-spring-boot). My work is in the `complete` folder: `PathsController.java` and `PathsControllerTest.java`.

## Run

```
cd complete
./mvnw spring-boot:run
```

On Windows use `.\mvnw.cmd spring-boot:run`. Requires JDK 17 or later. The app runs on http://localhost:8080.

## Paths

| Path | Conditional logic | Example |
| --- | --- | --- |
| `/greeting?name=Ty` | Greeting changes by time of day (morning, afternoon, evening, night) | `/greeting?name=Ty` |
| `/grade?score=85` | Letter grade A to F, pass or fail message, error if outside 0-100 | `/grade?score=85` |
| `/temperature?degrees=72` | Advice for freezing, chilly, mild, or hot | `/temperature?degrees=72` |
| `/ticket?age=30` | Free, child, adult, or senior ticket price | `/ticket?age=30` |

## Tests

```
cd complete
./mvnw test
```
