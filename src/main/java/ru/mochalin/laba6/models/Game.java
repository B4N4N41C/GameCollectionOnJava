package ru.mochalin.laba6.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Game {
    long id;
    String name;
    String summary;
    int mark;
    String photo;
    Timestamp created;
    public Game() {this(0, "", "", 0, "", new Timestamp(System.currentTimeMillis()));
    }
}
