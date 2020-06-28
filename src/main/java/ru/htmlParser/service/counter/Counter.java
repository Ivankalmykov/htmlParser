package ru.htmlParser.service.counter;

import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ru.htmlParser.service.separate.SeparateWords;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Данный класс считает количетво повторений того или иного слова на HTML странице
 */
@Component
@RequiredArgsConstructor
public class Counter {

    private final SeparateWords separateWords;

    public Map<String, Integer> countWords(Document document){
        Map<String, Integer> wordAndCount = new LinkedHashMap<>();
        String[] words = separateWords.separateWordFromFile(document);
        for(String word : words) {
            if(!wordAndCount.containsKey(word)){
                wordAndCount.put(word, 1);
            } else {
                Integer count = wordAndCount.get(word);
                count++;
                wordAndCount.put(word, count);
            }
        }
        return wordAndCount;
    }
}
