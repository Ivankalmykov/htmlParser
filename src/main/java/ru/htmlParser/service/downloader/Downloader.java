package ru.htmlParser.service.downloader;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ru.htmlParser.exception.NotEnoughRamException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**Данный класс выполняет загрузку html файла на жёсткий диск компьютера. Если URL указан неверно, выбрасывается
 *ошибка IOException. Если размер скачиваемого файла превышает доступный размер памяти ОЗУ, выбрасывается ошибка NotEnoughRamException*/

@Slf4j
@Component
public class Downloader {
    public Document downloadWebPage(String... args) {
        LocalDate dateTime = LocalDate.now();
        Document document = null;
        try {
            document = Jsoup.connect(args[0]).get();
            File file = new File(args[1] + dateTime + " " + lineAdjustment(args[0]) + ".html");
            saveFile(file, document);
        } catch (IOException e) {
            log.error("Неверно указан URL или путь сохранения файла ", e);
        }

        return document;
    }

    /**Данный метод записывает файл на жёсткий диск*/
    private void saveFile(File file, Document document) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            if(document.outerHtml().length() < Runtime.getRuntime().freeMemory()){
                writer.write(document.outerHtml());
            }else throw new NotEnoughRamException("Неудачная загрузка файла "+ file);
        }
    }

    /**Корректирует URL для названия файла*/
    private String lineAdjustment(String webPageLink) {
        String finalNameFile = "";
        if (webPageLink.startsWith("https://")) {
            if (webPageLink.endsWith("/")) {
                finalNameFile = webPageLink.substring(8, (webPageLink.length() - 1));
            } else finalNameFile = webPageLink.substring(8);
        }
        if (webPageLink.startsWith("http://")) {
            if (webPageLink.endsWith("/")) {
                finalNameFile = webPageLink.substring(7, webPageLink.length() - 1);
            } else finalNameFile = webPageLink.substring(7);
        }
        return finalNameFile;
    }

}