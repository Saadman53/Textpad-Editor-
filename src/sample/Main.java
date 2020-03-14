package sample;

import com.sun.javafx.logging.PlatformLogger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    MenuBar mainmenu;
    Menu filemenu,editmenu,Helpmenu;
    TextArea tA;
    @Override
    public void start(Stage primaryStage) throws Exception{
        tA = new TextArea();
        tA.setPromptText("Write here..");
        tA.setMinWidth(800);
        tA.setMinHeight(500);


       filemenu = new Menu("_File");
        MenuItem newFile= new MenuItem("New...");

        MenuItem save= new MenuItem("_Save...");

        MenuItem saveAs= new MenuItem("_Save As...");
        saveAs.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                saveTextToFile(tA.getText(), file);
            }
        });

        MenuItem exit= new MenuItem("Exit");

        filemenu.getItems().addAll(newFile,new SeparatorMenuItem(),save,saveAs,new SeparatorMenuItem(),exit);

       editmenu = new Menu("_Edit");

        MenuItem copy= new MenuItem("_Copy");
        MenuItem cut= new MenuItem("Cut");
        MenuItem paste= new MenuItem("Paste");

        editmenu.getItems().addAll(copy,cut,new SeparatorMenuItem(),paste);


      Helpmenu = new Menu("_Help");

      CheckMenuItem showline = new CheckMenuItem("Show Line Numbers");

        Helpmenu.getItems().addAll(showline);

      mainmenu = new MenuBar();
      mainmenu.getMenus().addAll(filemenu,editmenu, Helpmenu);

        BorderPane mainlayout= new BorderPane();
        mainlayout.setTop(mainmenu);

        mainlayout.setCenter(tA);

        Scene scene= new Scene(mainlayout);

        scene.getStylesheets().add("Dark.css");
        primaryStage.setTitle("Text Editor Noob");
 ;       primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



