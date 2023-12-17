package view;

import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Course;

public class SelectionOverPane  extends VBox {
	private Button addBtn;
	private TextArea profile,selected,reserved;
	
	
	public SelectionOverPane() {
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20,20,20,20));
		profile = new TextArea();
		profile.setPrefSize(300, 400);
		HBox box0 = new HBox();
		HBox.setHgrow(profile, Priority.ALWAYS);
		box0.getChildren().add(profile);
		
		box0.setAlignment(Pos.CENTER);
		box0.setPrefSize(600, 200);
		this.getChildren().add(box0);
		
		
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(20);
		box.setPrefSize(600, 200);
		selected = new TextArea();
		reserved = new TextArea();
		selected.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		selected.setPrefSize(450, 300);
		reserved.setPrefSize(450, 300);
		HBox.setHgrow(selected, Priority.ALWAYS);
		HBox.setHgrow(reserved, Priority.ALWAYS);
		box.getChildren().addAll(selected,reserved);
		this.getChildren().add(box);
		addBtn = new Button ("Save Overview");
		this.getChildren().add(addBtn);
		VBox.setVgrow(box, Priority.ALWAYS);
	}
	public void setProfileField(String s) {
		profile.setText(s);
	}
	public void setSelectedField(String s) {
		selected.setText(s);
	}
	public void setReservedField(String s) {
		reserved.setText(s);
	}
	public void addSave(EventHandler<ActionEvent> handler) {
		addBtn.setOnAction(handler);
	
		}
}
