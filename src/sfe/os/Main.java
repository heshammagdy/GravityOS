package sfe.os;

import apps.FXMediaPlayer;
import apps.ImageViewer;
import apps.Memo;
import apps.WebBrowser;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main extends Application {

    Stage mainStage;
    public static FileSystem fileSystem;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        primaryStage.setTitle("Desktop");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setScene(desktopScene());
        fileSystem = new FileSystem();
        primaryStage.show();
    }

    private Scene desktopScene() {
        BorderPane desktop = new BorderPane();

        desktop.setBackground(new Background(new BackgroundImage(
                new Image("res/gravity.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        desktop.setBottom(taskBar());

        return new Scene(desktop);
    }

    private BorderPane taskBar() {
        BorderPane taskBar = new BorderPane();
        taskBar.setCenter(apps());
        taskBar.setRight(clock());
        taskBar.setLeft(turnOff());

        return taskBar;
    }

    private HBox apps() {
        HBox appsBar = new HBox(10);

        Label fileExplorer = new Label(null, new ImageView("res/FileExplorer.png"));
        fileExplorer.setAlignment(Pos.CENTER);
        fileExplorer.setOnMouseEntered(event1 -> {
            fileExplorer.setScaleX(1.3);
            fileExplorer.setScaleY(1.3);
        });
        fileExplorer.setOnMouseExited(event1 -> {
            fileExplorer.setScaleX(1);
            fileExplorer.setScaleY(1);
        });
        fileExplorer.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(event.getClickCount() == 1) {
                    new Explorer();
                }
            }
        });

        Label imageViewerApp = new Label(null, new ImageView("res/ImageViewer.png"));
        imageViewerApp.setAlignment(Pos.CENTER);
        imageViewerApp.setOnMouseEntered(event1 -> {
            imageViewerApp.setScaleX(1.3);
            imageViewerApp.setScaleY(1.3);
        });
        imageViewerApp.setOnMouseExited(event1 -> {
            imageViewerApp.setScaleX(1);
            imageViewerApp.setScaleY(1);
        });
        imageViewerApp.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(event.getClickCount() == 1) {
                    new ImageViewer(null);
                }
            }
        });

        Label memoApp = new Label(null, new ImageView("res/Memo.png"));
        memoApp.setAlignment(Pos.CENTER);
        memoApp.setOnMouseEntered(event1 -> {
            memoApp.setScaleX(1.3);
            memoApp.setScaleY(1.3);
        });
        memoApp.setOnMouseExited(event1 -> {
            memoApp.setScaleX(1);
            memoApp.setScaleY(1);
        });
        memoApp.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(event.getClickCount() == 1) {
                    new Memo(null);
                }
            }
        });

        Label musicPlayerApp = new Label(null, new ImageView("res/MusicPlayer.png"));
        musicPlayerApp.setAlignment(Pos.CENTER);
        musicPlayerApp.setOnMouseEntered(event1 -> {
            musicPlayerApp.setScaleX(1.3);
            musicPlayerApp.setScaleY(1.3);
        });
        musicPlayerApp.setOnMouseExited(event1 -> {
            musicPlayerApp.setScaleX(1);
            musicPlayerApp.setScaleY(1);
        });
        musicPlayerApp.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(event.getClickCount() == 1) {
                    new FXMediaPlayer(null);
                }
            }
        });

        Label videoPlayerApp = new Label(null, new ImageView("res/VideoPlayer.png"));
        videoPlayerApp.setAlignment(Pos.CENTER);
        videoPlayerApp.setOnMouseEntered(event1 -> {
            videoPlayerApp.setScaleX(1.3);
            videoPlayerApp.setScaleY(1.3);
        });
        videoPlayerApp.setOnMouseExited(event1 -> {
            videoPlayerApp.setScaleX(1);
            videoPlayerApp.setScaleY(1);
        });
        videoPlayerApp.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(event.getClickCount() == 1) {
                    new FXMediaPlayer(null);
                }
            }
        });

        Label browserApp = new Label(null, new ImageView("res/Browser.png"));
        browserApp.setAlignment(Pos.CENTER);
        browserApp.setOnMouseEntered(event1 -> {
            browserApp.setScaleX(1.3);
            browserApp.setScaleY(1.3);
        });
        browserApp.setOnMouseExited(event1 -> {
            browserApp.setScaleX(1);
            browserApp.setScaleY(1);
        });
        browserApp.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(event.getClickCount() == 1) {
                    System.out.println("Opening the WebBrowser...");
                    new WebBrowser(WebBrowser.defaultUrl);
                }
            }
        });

        appsBar.getChildren().addAll(fileExplorer, imageViewerApp, memoApp, musicPlayerApp, videoPlayerApp, browserApp);
        appsBar.setBackground(new Background(new BackgroundFill(Color.web("#000000", 0.3), new CornerRadii(5), new Insets(0, 400, 0, 400))));
        appsBar.setPadding(new Insets(5, 0, 5, 0));
        appsBar.setAlignment(Pos.CENTER);
        return appsBar;
    }

    private HBox turnOff() {
        ImageView turnOff = new ImageView("res/powerOff.png");
        HBox box = new HBox(turnOff);
        box.setOnMouseEntered(event -> turnOff.setEffect(new Glow(0.5)));
        box.setOnMouseExited(event -> turnOff.setEffect(null));
        box.setOnMouseClicked(event -> {
            fileSystem.store();
            System.exit(0);
        });
        box.setBackground(new Background(new BackgroundFill(Color.web("#000000", 0.2), new CornerRadii(5), new Insets(5, 5, 5, 5))));
        box.setPadding(new Insets(5, 5, 5, 5));
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private HBox clock() {
        Label label = new Label();
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), actionEvent -> {
                Calendar time = Calendar.getInstance();
                String hourString = StringUtilities.pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                String minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
                String secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
                String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                label.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString + "\n" + new SimpleDateFormat("dd/MM/yyyy").format(time.getTime()));
            }),
            new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        label.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-text-alignment: center; ");

        HBox box = new HBox(label);
        box.setBackground(new Background(new BackgroundFill(Color.web("#000000", 0.2), new CornerRadii(5), new Insets(2, 2, 2, 2))));
        box.setPadding(new Insets(5, 5, 5, 5));
        box.setAlignment(Pos.CENTER_RIGHT);
        return box;
    }

    static class StringUtilities {

        public static String pad(int fieldWidth, char padChar, String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = s.length(); i < fieldWidth; i++) {
                sb.append(padChar);
            }
            sb.append(s);

            return sb.toString();
        }

    }

}
