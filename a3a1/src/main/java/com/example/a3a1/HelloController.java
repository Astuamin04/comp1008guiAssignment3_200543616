package com.example.a3a1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private Label mbErrormessage;

    @FXML
    private TextField nEntry;

    @FXML
    private Text nText1, nText2, nText3, nText4, nText5, nText6, nText7, nText8, nText9;

    @FXML
    private Pane p1color, p2color, p3color, p4color, p5color, p6color, p7color, p8color, p9color;

    @FXML
    private ColorPicker pickedColor;

    private Pane[] scolour;
    private Text[] sname;
    private HashSet<Student> students = new HashSet<>();

    private Random random = new Random();

    private class Student {
        String name;
        String color;

        Student(String name, String color) {
            this.name = name;
            this.color = color;
        }
    }

    @FXML
    public void initialize() {
        scolour = new Pane[]{p1color, p2color, p3color, p4color, p5color, p6color, p7color, p8color, p9color};
        sname = new Text[]{nText1, nText2, nText3, nText4, nText5, nText6, nText7, nText8, nText9};
    }

    @FXML
    void addedS(ActionEvent event) {
        String nameTextEntry = nEntry.getText().trim().toLowerCase();
        Color paneColorPicked = pickedColor.getValue();

        if (nameTextEntry.contains(" ")) {
            mbErrormessage.setText("SPACES ARE NOT ALLOWED BETWEEN THE NAMES ");
            mbErrormessage.setStyle("-fx-text-fill: red;");
            return;
        }

        if (nameTextEntry.length() < 1) {
            mbErrormessage.setText("ENTERED NAME IS TOO SHORT .");
            mbErrormessage.setStyle("-fx-text-fill: red;");
            return;
        }
        if (nameTextEntry.length() < 2) {
            mbErrormessage.setText("ENTERED NAME IS TOO SHORT .");
            mbErrormessage.setStyle("-fx-text-fill: yellow;");
            return;
        }

        if (paneColorPicked.equals(Color.WHITE)) {
            mbErrormessage.setText("PLEASE SELECT ANOTHER COLOR AS WHITE IS ALREADY TAKEN.");
            mbErrormessage.setStyle("-fx-text-fill: yellow;");
            return;
        }

        for (Student s : students) {
            if (s.name.equals(nameTextEntry)) {
                mbErrormessage.setText("STUDENT NAME ALREADY EXIST .");
                mbErrormessage.setStyle("-fx-text-fill: yellow;");
                return;
            }

            if (s.color.equals(paneColorPicked.toString())) {
                mbErrormessage.setText("THIS COLOUR IS ALREADY TAKEN .");
                mbErrormessage.setStyle("-fx-text-fill: orange;");
                return;
            }
        }

        int emptySpace = -1;
        List<Integer> availableSpaces = new ArrayList<>();

        for (int i = 0; i < sname.length; i++) {
            if (sname[i].getText().isEmpty()) {
                availableSpaces.add(i);
            }
        }

        if (!availableSpaces.isEmpty()) {
            emptySpace = availableSpaces.get(random.nextInt(availableSpaces.size()));
        }

        if (emptySpace != -1) {
            scolour[emptySpace].setStyle("-fx-background-color: #" + paneColorPicked.toString().substring(2));
            sname[emptySpace].setText(nameTextEntry);
            sname[emptySpace].setVisible(true);

            students.add(new Student(nameTextEntry, paneColorPicked.toString()));
            mbErrormessage.setText("Added successfully!");
            mbErrormessage.setStyle("-fx-text-fill: violet;");
        } else {
            mbErrormessage.setText("Seats full.");
            mbErrormessage.setStyle("-fx-text-fill: blue;");}
}
}
