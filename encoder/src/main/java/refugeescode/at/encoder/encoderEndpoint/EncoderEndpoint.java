package refugeescode.at.encoder.encoderEndpoint;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/encoder")
public class EncoderEndpoint {


    @Value("${morseUrl}")
    private String morseUrl;

    private RestTemplate restTemplate = new RestTemplate();


    @PostMapping
    String addTextToMorse(@RequestBody String message) {
        return Stream.of(message.split(""))
                .map(letter -> toMorse(letter))
                .collect(Collectors.joining());
    }

    private String toMorse(String letter) {
        return restTemplate.postForObject(morseUrl, letter, String.class);
    }

}
