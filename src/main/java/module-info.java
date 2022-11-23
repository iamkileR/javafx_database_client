/**
 * Lista zawierajaca potrzebne komponenty do rozruchu aplikacji
 */
module org.example {
    requires javafx.controls;
    exports org.example;
    requires java.sql;
    requires ojdbc8;
    requires java.naming;
    requires javafx.fxml;
}
