package view;

import java.io.Serializable;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;

public class ReserveModulesPane  extends VBox {
	private Button addBtn, removeBtn,confirmBtn;
	private ListView<Module> unselected1,reserved1;
	 private ObservableList<Module> modules1 ,modules2;
	
	 public ReserveModulesPane() {
		//set the style for this pane 
	this.setPadding(new Insets (20,20,20,20));
	
		HBox box = new HBox();
		box.setSpacing(50);
		 modules1= FXCollections.observableArrayList();
		 unselected1 =new ListView(modules1);
		 modules2= FXCollections.observableArrayList();
		reserved1 = new ListView(modules2);
		HBox.setHgrow(unselected1, Priority.ALWAYS);
		HBox.setHgrow(reserved1, Priority.ALWAYS);
		box.getChildren().addAll( unselected1,reserved1);		
		this.getChildren().add(box);
		
		Label labl1 = new Label ("Reserve 30 credits worth of term 1 modules");
		addBtn = new Button("Add");
		removeBtn = new Button ("Remove");
		confirmBtn = new Button ("Confirm");
        HBox box1 = new HBox();
        box1.setPadding(new Insets (10,10,10,10));
        box1.setSpacing(15);
        box1.setAlignment(Pos.CENTER_LEFT);
       box1.getChildren().addAll(labl1,addBtn, removeBtn,confirmBtn);
		this.getChildren().add(  box1);

	}
	public ObservableList<Module> getUnselectedModules(){
		return modules1;
	}
	public ObservableList<Module> getReservedModules(){
		return modules2;
	}
	public Module getUnselectedModule() {
	return	unselected1.getSelectionModel().getSelectedItem();
	}
	public Module getReservedModule() {
		return	reserved1.getSelectionModel().getSelectedItem();
		}
	public void addAddHandler(EventHandler<ActionEvent> handler) {
		addBtn.setOnAction(handler);
	}

	public void addRemoveHandler(EventHandler<ActionEvent> handler) {
		removeBtn.setOnAction(handler);
	
		}
	public void addConfirmHandler(EventHandler<ActionEvent> handler) {
		confirmBtn.setOnAction(handler);
	
		}
	public void submitBtnDisableBind(BooleanBinding value) {
		confirmBtn.disableProperty().bind(value);
	}
}
