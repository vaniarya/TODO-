package com.example.demofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;
import java.util.ArrayList;


public class HelloApplication extends Application {

    private final ArrayList<Data> taskList= new ArrayList<>();

    class Data{
        private String tasks;
        private LocalDate dates;
        Data(String tasks, LocalDate dates){
            this.tasks= tasks;
            this.dates= dates;
        }
    }

    @Override
    public void start(Stage stage) {

        //                  SCENE 1
        Pane root1 = new Pane();
        Scene sc = new Scene(root1, 500, 500);
        root1.setStyle("-fx-background-color: pink;");

        // Title Text
        Text tx = new Text("TODO APP");
        tx.setX(100);
        tx.setY(80);
        tx.setFont(Font.font("Georgia", 60));
        root1.getChildren().add(tx);

        // Buttons
        Button bt1 = new Button("Add Task");
        bt1.setLayoutX(180);
        bt1.setLayoutY(150);

        Button bt2 = new Button("Show tasks");
        bt2.setLayoutX(180);
        bt2.setLayoutY(200);

        root1.getChildren().addAll(bt1, bt2);


        //                      SCENE 2
        Pane root2 = new Pane();
        Scene sc2= new Scene(root2, 500, 500);
        root2.setStyle("-fx-background-color: pink;");

        // Title Text
        Text tx1 = new Text("Add Task");
        tx1.setX(100);
        tx1.setY(80);
        tx1.setFont(Font.font("Georgia", 50));
        root2.getChildren().add(tx1);

        DatePicker datePicker = new DatePicker();
        datePicker.setLayoutX(195);
        datePicker.setLayoutY(190);
        root2.getChildren().add(datePicker);


        //textfield
        TextField txField= new TextField();
        txField.setLayoutX(195);
        txField.setLayoutY(150);
        root2.getChildren().add(txField);

        //Enter button
        Button enter= new Button("enter");
        enter.setLayoutX(230);
        enter.setLayoutY(230);
        root2.getChildren().add(enter);

        //Back button
        Image img = new Image(getClass().getResourceAsStream("/images/sample.png"));
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(10);
        imgView.setFitHeight(10);

        Button btImg= new Button();
        btImg.setGraphic(imgView);
        btImg.setStyle("-fx-background-color: transparent;");
        root2.getChildren().add(btImg);


        //                          SCENE 3
        Pane root3 = new Pane();
        Scene sc3= new Scene(root3, 500, 500);
        root3.setStyle("-fx-background-color: pink;");

        // Title Text
        Text tx2 = new Text("Your Tasks");
        tx2.setX(100);
        tx2.setY(80);
        tx2.setFont(Font.font("Georgia", 60));
        root3.getChildren().add(tx2);

        Image img2 = new Image(getClass().getResourceAsStream("/images/sample.png"));
        ImageView imgView2 = new ImageView(img2);
        imgView2.setFitWidth(10);
        imgView2.setFitHeight(10);

        Button btImg2= new Button();
        btImg2.setGraphic(imgView);
        btImg2.setStyle("-fx-background-color: transparent;");
        root3.getChildren().add(btImg2);

        VBox vCont= new VBox(10);
        vCont.setLayoutX(200);
        vCont.setLayoutY(150);
        root3.getChildren().add(vCont);



        //                      EVENTS
        bt1.setOnAction(e-> stage.setScene(sc2));
        bt2.setOnAction(e -> {

            vCont.getChildren().clear();
            int a= taskList.size();
            for(int i=0; i<a; i++){
                final int idx= i;
                CheckBox ch= new CheckBox(taskList.get(i).tasks+"   Due-"+taskList.get(i).dates);
                Button del= new Button("X");

                del.setStyle("-fx-background-color: red;");

                HBox hBox= new HBox(10);
                hBox.getChildren().addAll(ch, del);

                del.setOnAction(e1->{
                    taskList.remove(idx);
                    vCont.getChildren().remove(hBox);
                });

                vCont.getChildren().add(hBox);

            }
            stage.setScene(sc3);
        });

        btImg.setOnAction(e-> stage.setScene(sc));
        btImg2.setOnAction(e-> stage.setScene(sc));
        enter.setOnAction(e-> {
            String task= txField.getText().trim();
            LocalDate date= datePicker.getValue();
            if(!task.isEmpty()){
                taskList.add(new Data(task, date));
            }
            System.out.println("Task added successfully");
            txField.clear();
            datePicker.setValue(null);
        });

        stage.setTitle("Your TODO");
        stage.setScene(sc);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
