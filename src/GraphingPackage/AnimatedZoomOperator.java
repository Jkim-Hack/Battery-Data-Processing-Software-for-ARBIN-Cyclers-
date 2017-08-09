package GraphingPackage;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimatedZoomOperator {

    private Timeline timeline;

    public AnimatedZoomOperator() {         
         this.timeline = new Timeline(60);
    }

    public void zoom(Node node, double factor, double x, double y) {    
        // determine scale
        double oldScale = node.getScaleX();
        double scale = oldScale * factor;
        double f = (scale / oldScale) - 1;

        // determine offset that we will have to move the node
        Bounds bounds = node.localToScene(node.getBoundsInLocal());
        double dx = (x - (bounds.getWidth() / 2 + bounds.getMinX()));
        double dy = (y - (bounds.getHeight() / 2 + bounds.getMinY()));

        // timeline that scales and moves the node
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(200), new KeyValue(node.translateXProperty(), node.getTranslateX() - f * dx)),
            new KeyFrame(Duration.millis(200), new KeyValue(node.translateYProperty(), node.getTranslateY() - f * dy)),
            new KeyFrame(Duration.millis(200), new KeyValue(node.scaleXProperty(), scale)),
            new KeyFrame(Duration.millis(200), new KeyValue(node.scaleYProperty(), scale))
        );
        timeline.play();
    }
}