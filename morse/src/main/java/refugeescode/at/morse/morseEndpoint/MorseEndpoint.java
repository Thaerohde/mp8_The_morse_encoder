package refugeescode.at.morse.morseEndpoint;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/morse")
@RestController
public class MorseEndpoint {

    String morseString = "";

    @PostMapping("/morse")
    String chipher(@RequestBody String message) {
        morseString = morseString + toMorse(message);
        return morseString;
    }

    @GetMapping("/morse")
    String get() {
        String getMappingString = "";
        getMappingString = getMappingString + morseString;
        return getMappingString.replaceAll("\"", "").trim();
    }

    public String toMorse(String input) {
        List<String> letters = Stream.of("a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" "))
                .collect(Collectors.toList());

        List<String> morse = Stream.of(".- / -... / -.-. / -.. / . / ..-. / --. / .... / .. / .--- / -.- / .-.. / -- / -. / --- / .--. / --.- / .-. / ... / - / ..- / ...- / .-- / -..- / -.-- / --..".split(" / "))
                .collect(Collectors.toList());

        String collect = Stream.of(input).map(e -> input.indexOf(e))
                .map(e1 -> morse.indexOf(e1))
                .collect(Collectors.toList())
                .toString();


        return collect;

    }
}

