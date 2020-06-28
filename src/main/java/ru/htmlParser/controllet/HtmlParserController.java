package ru.htmlParser.controllet;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.htmlParser.service.Handler;

import java.util.Map;
/**Контроллер возвращающий в формане JSON уникальные буквы и их количество.
 *  На вход принимается 2 параметра URL и путь сохранения файла(необязательный)*/

@RestController
@RequiredArgsConstructor
public class HtmlParserController {

    private final Handler handler;

    @GetMapping(value = "/words")
    public Map<String, Integer> getWordAndCount(@RequestParam String webLink,
                                                @RequestParam (required = false) String saveDirectory){

        return handler.workWithWordsAndCount(webLink,saveDirectory);
    }
}
