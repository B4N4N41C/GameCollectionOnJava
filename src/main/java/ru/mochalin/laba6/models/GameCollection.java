package ru.mochalin.laba6.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс GameCollection со свойствами <b>id</b>,<b>gameId</b> и <b>collectionId</b>.
 * <p>
 * Данный класс позволяет описать экземпляр связи коллекции и игры.
 * Данная связь необходима для того, чтобы нормализовать связь многие ко многим между играми и подборкой.
 * @author Николай Мочалин
 */
@Getter
@Setter
@AllArgsConstructor
public class GameCollection {
    long id;
    long gameId;
    long collectionId;

    public GameCollection() {this(0, 0, 0);}
}
