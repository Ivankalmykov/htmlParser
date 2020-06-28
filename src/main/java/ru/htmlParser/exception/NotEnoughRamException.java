package ru.htmlParser.exception;
/**Ошибка выбрасываемая при недостаточном объёме памяти для загрузки файла*/
public class NotEnoughRamException extends RuntimeException{

    public NotEnoughRamException(String message){
        super(message);
    }
}
