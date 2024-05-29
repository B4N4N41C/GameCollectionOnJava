package ru.mochalin.laba6.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameCollection {
    long id;
    long gameId;
    long collectionId;

    public GameCollection() {this(0, 0, 0);}
}
