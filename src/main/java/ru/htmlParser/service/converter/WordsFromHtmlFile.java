package ru.htmlParser.service.converter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**Данный класс получает элементы HTML построчно и добавляет их в List*/

@Component
public class WordsFromHtmlFile {
    public List<String> pickWordFromHtmlFile(Document document) {

        List<String> lines = new ArrayList<>();

        Elements elements = document.body().select("*");

        for (Element element : elements) {
            String line = element.ownText();
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }
        return lines;
    }
}
