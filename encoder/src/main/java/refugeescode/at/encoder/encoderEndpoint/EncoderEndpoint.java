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

    private String text;
    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping
    String addTextToMorse(@RequestBody String toMorse) {

        return Stream.of(toMorse)
                .map(letter -> connectWithMorse(letter))
                .collect(Collectors.toList())
                .toString();

    }

    private String connectWithMorse(String letter) {
        String cipherString = "";
        restTemplate.postForEntity(morseUrl, letter, String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(morseUrl, String.class);
        cipherString = cipherString + responseEntity.getBody();
        return cipherString;
    }

}
