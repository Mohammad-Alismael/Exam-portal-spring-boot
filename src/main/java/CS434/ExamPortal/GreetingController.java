package CS434.ExamPortal;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

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
        g.add(new Greeting(RandomUuidStringCreator.getRandomUuid(), "hello"));
        g.add(new Greeting(RandomUuidStringCreator.getRandomUuid(), "hello2"));
        g.add(new Greeting(RandomUuidStringCreator.getRandomUuid(), "hello3"));
        return g;
    }
}
