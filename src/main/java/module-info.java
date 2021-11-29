module com.zychp.Wires
{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    opens com.zychp.Wires to javafx.fxml;
    exports com.zychp.Wires;
    exports com.zychp.Wires.Elements;
}