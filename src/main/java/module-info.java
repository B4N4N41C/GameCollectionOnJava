module ru.mochalin.laba6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens ru.mochalin.laba6 to javafx.fxml;
    exports ru.mochalin.laba6;
    exports ru.mochalin.laba6.controllers;
    opens ru.mochalin.laba6.controllers to javafx.fxml;
    exports ru.mochalin.laba6.models;
    opens ru.mochalin.laba6.models to javafx.fxml;
}