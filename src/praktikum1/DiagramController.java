/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weinguetediagramm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Prof.Dr.AndreasM
 */
public class DiagramController implements Initializable {

    @FXML
    private VBox diagram;

    @FXML
    private Rectangle rectPast;

    @FXML
    private Rectangle rectNow;

    @FXML
    private Rectangle rectFuture;

    @FXML
    private Rectangle rectToEarly;

    @FXML
    private Rectangle rectRising;

    @FXML
    private Rectangle rectGood;

    @FXML
    private Rectangle rectDecline;

    @FXML
    private Label lbVintage;

    @FXML
    private Label lbDecline;

    @FXML
    private Label lbPast;

    @FXML
    private Label lbNow;

    private static final double DIAGRAM_WIDTH = 0.98;
    private static final double DIAGRAM_HEIGHT = 0.5;

    private static final double TO_EARLY_RATIO = 0.125;
    private static final double GOOD_RATIO = 0.5;
    private static final double RISING_RATIO = 1 - TO_EARLY_RATIO - GOOD_RATIO;

    private static final String DURATION_EXCEPTION = "Duration must be greater than 0.";
    private static final String VINTAGE_EXCEPTION = "Vintage can't be in future.";

    // states
    private double toEarly;
    private double rising;
    private double good;
    private double decline;

    // epochs
    private double past;
    private double now;
    private double future;

    private void showDiagram(int vintage, int duration, int presentYear) {
        if (duration < 1) {
            throw new IllegalArgumentException(DURATION_EXCEPTION);
        }
        if (presentYear < vintage) {
            throw new IllegalArgumentException(VINTAGE_EXCEPTION);
        }
        computeStates(vintage, duration, presentYear);
        bindStates(vintage, duration);
        computeEpochs(vintage, duration, presentYear);
        bindEpochs(presentYear);
    }

    private void computeStates(int vintage, int duration, int presentYear) {
        double yearsInStock = duration + 1;
        double yearsTotal = duration + 2;
        this.toEarly = TO_EARLY_RATIO * yearsInStock / yearsTotal;
        this.rising = RISING_RATIO * yearsInStock / yearsTotal;
        this.good = GOOD_RATIO * yearsInStock / yearsTotal;
        this.decline = 1 / yearsTotal;
    }

    private void bindStates(int vintage, int duration) {
        rectToEarly.widthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * this.toEarly));
        rectToEarly.heightProperty().bind(diagram.heightProperty().multiply(DIAGRAM_HEIGHT));
        rectRising.widthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * this.rising));
        rectRising.heightProperty().bind(diagram.heightProperty().multiply(DIAGRAM_HEIGHT));
        rectGood.widthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * this.good));
        rectGood.heightProperty().bind(diagram.heightProperty().multiply(DIAGRAM_HEIGHT));
        rectDecline.widthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * this.decline));
        rectDecline.heightProperty().bind(diagram.heightProperty().multiply(DIAGRAM_HEIGHT));
        lbVintage.setText("" + vintage);
        lbVintage.prefWidthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * (this.toEarly + this.rising + this.good)));
        lbVintage.minWidthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * (this.toEarly + this.rising + this.good)));
        lbDecline.setText("" + (vintage + duration + 1));
    }

    private void computeEpochs(int vintage, int duration, int presentYear) {
        double yearsInStock = duration + 1;
        double yearsTotal = duration + 2;
        if (presentYear == vintage) {
            this.past = 0.0;
            this.now = 1 / yearsTotal;
        } else if (presentYear > vintage + yearsInStock) {
            this.past = 1.0;
            this.now = 0.0;
        } else {
            this.past = (presentYear - vintage) * 1 / (yearsTotal);
            this.now = 1 / yearsTotal;
        }
        this.future = 1 - this.past - this.now;
    }

    private void bindEpochs(int presentYear) {
        rectPast.widthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * past));
        rectPast.heightProperty().bind(diagram.heightProperty().multiply(DIAGRAM_HEIGHT));
        rectNow.widthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * now));
        rectNow.heightProperty().bind(diagram.heightProperty().multiply(DIAGRAM_HEIGHT));
        rectFuture.widthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * future));
        rectFuture.heightProperty().bind(diagram.heightProperty().multiply(DIAGRAM_HEIGHT));
        lbPast.prefWidthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * past));
        lbPast.minWidthProperty().bind(diagram.widthProperty().multiply(DIAGRAM_WIDTH * past));
        if (now > 0.0) {
            lbNow.setText("" + presentYear);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDiagram(2018, 1, 2018);
    }

}
