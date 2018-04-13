package refugeescode.at.morse.morseEndpoint;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping
public class MorseEndpoint {

    private List<String> letters = Stream.of("a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" "))
            .collect(Collectors.toList());

    private List<String> morse = Stream.of("  /.- / -... / -.-. / -.. / . / ..-. / --. / .... / .. / .--- / -.- / .-.. / -- / -. / --- / .--. / --.- / .-. / ... / - / ..- / ...- / .-- / -..- / -.-- / --..".split(" / "))
            .collect(Collectors.toList());

    @PostMapping("/morse")
    String chipher(@RequestBody String letter) {
        if(letter.trim().isEmpty()){
            return " ";
        }
        else return toMorse(letter);
    }

    public String toMorse(String letter) {
        int index = letters.indexOf(letter);
        return morse.get(index);

    }
}

