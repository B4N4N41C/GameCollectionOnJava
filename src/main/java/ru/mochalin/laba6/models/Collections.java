package ru.mochalin.laba6.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Collections {
    long id;
    String photo;
    String name;
    String summary;
    Timestamp created;
}
