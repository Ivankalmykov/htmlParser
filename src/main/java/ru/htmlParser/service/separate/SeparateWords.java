package ru.htmlParser.service.separate;

import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ru.htmlParser.service.converter.WordsFromHtmlFile;

import java.util.List;

/**
 * Данный класс разбивает строки на слова по заранее известному разделителю.
 */
@Component
@RequiredArgsConstructor
public class SeparateWords {

    private final static String DELIMITERS = "[ ,.!?\";:\\[\\]()\\n\\r\\t]";

    private final WordsFromHtmlFile wordsFromHtmlFile;

    public String[] separateWordFromFile(Document document) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> lines = wordsFromHtmlFile.pickWordFromHtmlFile(document);
        for(String line : lines){
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString().toUpperCase().split(DELIMITERS);
    }
}
