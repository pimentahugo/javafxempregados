module com.mycompany.javafxativ2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    requires org.json;
    requires java.sql;

    opens com.mycompany.javafxativ2 to javafx.fxml, xstream;
    opens com.mycompany.javafxativ2.dao to xstream;
    opens com.mycompany.javafxativ2.models to xstream, javafx.base;

    exports com.mycompany.javafxativ2;
}
