package CS434.ExamPortal.Controller;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import CS434.ExamPortal.Classes.RandomUuidStringCreator;
import CS434.ExamPortal.Classes.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s. it works!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(RandomUuidStringCreator.getRandomUuid(), String.format(template, name));
    }
    @GetMapping("/listGreeting")
    public ArrayList<Greeting> greeting() {
        ArrayList<Greeting> g = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            g.add(new Greeting(RandomUuidStringCreator.getRandomUuid(), "hello"+ (i+1)));
        }
        return g;
    }
}
