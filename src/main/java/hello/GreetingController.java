package hello;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonParser;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.http.HttpClient;

/**
 * Created by Laora on 02/04/2017.
 */

@RestController
public class GreetingController {

    private static final String template = "Lattitude: %s, Longitude:%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="lattitude", defaultValue="0") double lattitude, @RequestParam(value="longitude", defaultValue="0") double longitude) {
        int radius =500;
        String types ="";
        String key="";
        String placeString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lattitude+","+longitude+"&radius="+radius+"&types="+types+"&key="+key;
        String place="";

        return new Greeting(counter.incrementAndGet(), String.format(template, lattitude, longitude));
    }
}