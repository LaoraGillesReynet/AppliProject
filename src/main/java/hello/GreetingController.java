package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by Laora on 02/04/2017.
 */

@RestController
public class GreetingController {

    private static final String template = "Coucou, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Copain") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}