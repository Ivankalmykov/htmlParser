package ru.htmlParser.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import ru.htmlParser.service.counter.Counter;
import ru.htmlParser.service.downloader.Downloader;

import java.util.Map;

/**Сервисный слой, в котором происходит основная бизнес логика*/

@Service
@RequiredArgsConstructor
public class Handler {
    private final Downloader downloader;
    private final Counter counter;

    public Map<String, Integer> workWithWordsAndCount(String webLink, String saveDirectory){
        Document document =  downloader.downloadWebPage(webLink, saveDirectory);
        Map <String,Integer> uniqWordsAndCount = counter.countWords(document);

        for(String key : uniqWordsAndCount.keySet()) {
            System.out.println(key + " - " + uniqWordsAndCount.get(key));
        }

        return uniqWordsAndCount;
    }
}
