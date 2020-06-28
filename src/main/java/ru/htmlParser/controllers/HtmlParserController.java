package ru.htmlParser.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.htmlParser.service.Handler;

import java.util.Map;

/**
 *Контроллер возвращающий в формане JSON уникальные буквы и их количество.
 *  На вход принимается 2 параметра URL и путь сохранения файла(необязательный)
 */
@RestController
@RequiredArgsConstructor
public class HtmlParserController {

    private final Handler handler;

    @GetMapping(value = "/words", params = "webLink")
    public Map<String, Integer> getWordAndCount(@RequestParam String webLink){

        return handler.workWithWordsAndCount(webLink);
    }

    @GetMapping(value = "/words", params = {"webLink", "path"})
    public Map<String, Integer> getWordAndCount(@RequestParam String webLink,
                                                @RequestParam String path){

        return handler.workWithWordsAndCount(webLink,path);
    }
}
