package view;



import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;
import model.Name;
import model.RunPlan;

public class SelectModulesPane extends GridPane  {

	
	private ListView<Module> txtModules1, txtModules2,  txtField3, txtField4,txtfield5;
	private Button btnAdd, btnDelete, btnAdd1, btnDelete1, btnReset, btnSubmit;
	 private ObservableList<Module> modules1 ,modules2, yearlong,modSelected,modSelected2 ;
	private TextField barak,dan;
	public SelectModulesPane() {
		//styling
		this.setPadding(new Insets (10,10,10,10));
		this.setVgap(15);
		this.setHgap(15);
		this.setPrefSize(600, 450);
	    this.setAlignment(Pos.CENTER);
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.CENTER);
		column0.setHgrow(Priority.ALWAYS);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHalignment(HPos.CENTER);
		column1.setHgrow(Priority.ALWAYS);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setHalignment(HPos.CENTER);
		column2.setHgrow(Priority.ALWAYS);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setHalignment(HPos.CENTER);
		column3.setHgrow(Priority.ALWAYS);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setHalignment(HPos.CENTER);
		column4.setHgrow(Priority.ALWAYS);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setHalignment(HPos.CENTER);
		column5.setHgrow(Priority.ALWAYS);
		this.getColumnConstraints().addAll(column0,column1,column2,column3,column4,column5);
	
	
		VBox first1 = new VBox();
		Label lblTitle = new Label("Unselected Year One Modules ");
		
		modules1 =FXCollections.observableArrayList();
		txtModules1 =new ListView<Module>(modules1);
		first1.getChildren().addAll(lblTitle,txtModules1);
		VBox.setVgrow(txtModules1, Priority.ALWAYS);
		this.add(first1, 0, 0,2,2);
		
		
		VBox.setVgrow(txtModules1, Priority.ALWAYS);
		HBox first = new HBox();
		Label lblTerm1 = new Label("Term 1: ");
		btnAdd = new Button("Add");
		btnDelete = new Button("Delete");
		first.setSpacing(10);
		first.getChildren().addAll(lblTerm1,btnAdd,btnDelete);
		this.add(first, 0, 2,2,2);
		
		
		VBox second2 = new VBox();
		Label lblModules2 = new Label("Unselected Year Two Modules ");
		modules2 =FXCollections.observableArrayList();
		txtModules2 =new ListView<Module>(modules2);
		second2.getChildren().addAll(lblModules2,txtModules2);
		this.add(second2, 0, 4,2,2);
		
		
		HBox second =new HBox();
		Label term2 = new Label("Term 2:");
		btnAdd1 = new Button("Add");
		btnDelete1 = new Button("Delete");
		second.getChildren().addAll(term2,btnAdd1,btnDelete1);
		second.setSpacing(10);
		this.add(second, 0, 6,2,2);
		
		VBox third =new VBox();
		Label lblModules3 = new Label("Selected yearLong Modules ");
		yearlong =FXCollections.observableArrayList();
		txtField3 = new ListView<Module>(yearlong);
		third.getChildren().addAll(lblModules3,txtField3);
		VBox.setVgrow(txtField3, Priority.ALWAYS);
		this.add(third, 2, 0,4,1);
		
		
		VBox box =new VBox();
		Label selected = new Label("Selected term 1 modules");
		modSelected =FXCollections.observableArrayList();
		txtField4 = new ListView<Module>(modSelected);
		box.getChildren().addAll(selected,txtField4);
		VBox.setVgrow(txtField4,Priority.ALWAYS);
		this.add(box, 2, 1, 4,1);
	
		
		VBox box1 = new VBox();
		Label selected2 = new Label("Selected year 2 modules");
		modSelected2 =FXCollections.observableArrayList();
		txtfield5 = new ListView<Module>(modSelected2);
		box1.getChildren().addAll(selected2,txtfield5);
		VBox.setVgrow(txtfield5, Priority.ALWAYS);
		this.add(box1,2, 4, 4,1);
		
		HBox box4 = new HBox();
		Label myl = new Label(" Current Term1 creits");
		 dan = new TextField ();
		box4.getChildren().addAll(myl,dan);
		VBox.setVgrow(dan, Priority.ALWAYS);
		this.add(box4, 1, 8);
		
		HBox box6 = new HBox();
		Label laaab = new Label(" Current Term 2 creits");
		barak = new TextField ();
		box6.getChildren().addAll(laaab,barak);
		VBox.setVgrow(box6, Priority.ALWAYS);
		this.add(box6, 2, 8);
		
		HBox box5 = new HBox();
		 btnSubmit = new Button ("Submit");
		 btnReset = new Button ("Reset");
		 box5.setAlignment(Pos.CENTER);
		 box5.setSpacing(30);
		box5.getChildren().addAll(btnSubmit,btnReset);
		this.add(box5, 1, 9,2,2);
		
		GridPane.setVgrow(box, Priority.ALWAYS );
		GridPane.setVgrow(box5, Priority.ALWAYS );
		GridPane.setVgrow(box4, Priority.ALWAYS );
		GridPane.setVgrow(box6, Priority.ALWAYS );
		GridPane.setVgrow(third, Priority.ALWAYS );
		GridPane.setVgrow(second, Priority.ALWAYS );
	}
	
	public void addYear1(Course[] c) {
	Course m = c[0];
	Collection<Module> n = m.getAllModulesOnCourse();	
	for (Module i : n) {
		if (i.getDelivery()==RunPlan.TERM_1) {
			modules1.add(i);
			if (i.getModuleCode()=="IMAT3423") {
				modSelected.add(i)	;
			}
		}}}
	public void addYear1E(Course[] c) {
		Course m = c[1];
		Collection<Module> n = m.getAllModulesOnCourse();	
		for (Module i : n) {
			if (i.getDelivery()==RunPlan.TERM_1) {
				modules1.add(i);
				if (i.getModuleCode()=="IMAT3423") {
					modSelected.add(i)	;}
			}}}
		
	public void addYear2(Course[] c) {	
		Course m = c[0];
		Collection<Module> n = m.getAllModulesOnCourse();	
		for (Module i : n) {
			if (i.getDelivery()==RunPlan.TERM_2) {
				modules2.add(i);
			}}}
	public void addYear2E(Course[] c) {	
		Course m = c[1];
		Collection<Module> n = m.getAllModulesOnCourse();	
		for (Module i : n) {
			if (i.getDelivery()==RunPlan.TERM_2) {
				modules2.add(i);
				if (i.getModuleCode()=="CTEC3902") {
					modSelected2.add(i)	;}
			}}}
	public void addYearLong(Course[] c) {	
		Course m = c[0];
		Collection<Module> n = m.getAllModulesOnCourse();	
		for (Module i : n) {
			if (i.getDelivery()==RunPlan.YEAR_LONG) {
				yearlong.add(i);
			}}}
	
	public ObservableList<Module>  getUnselectedModules1 (){
		return modules1;
	}
	public Module getUnSelectedModule1() {
		return txtModules1 .getSelectionModel().getSelectedItem();
	}
	public ObservableList<Module> getUnSelectedModules1() {
		return txtModules1 .getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module>  getUnselectedModules2 (){
		return modules2;
	}
	public Module getUnSelectedModule2() {
		return txtModules2 .getSelectionModel().getSelectedItem();
	}
	
	public ObservableList<Module>  getYearLongModules (){
		return yearlong;
	}
	public ObservableList<Module>  getSelectedModulesYear1 (){
		return txtField4.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module>  getSelectedYear1 (){
		return modSelected;
	}
	public Module getSelectedModule1() {
		return txtField4.getSelectionModel().getSelectedItem();
	}
	
	public ObservableList<Module>  getSelectedYear2 (){
		return modSelected2;
	}
		public Module getSelectedModule2() {
			return txtfield5.getSelectionModel().getSelectedItem();
		}
		
		public ObservableList<Module>  getSelectedModulesYear2 (){
			return txtfield5.getSelectionModel().getSelectedItems();
		}
	public void addAddHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}

	public void addRemoveHandler(EventHandler<ActionEvent> handler) {
		btnDelete.setOnAction(handler);
	}
	public void addAdd2Handler(EventHandler<ActionEvent> handler) {
		btnAdd1.setOnAction(handler);
	}

	public void addRemove2Handler(EventHandler<ActionEvent> handler) {
		btnDelete1.setOnAction(handler);
	}

	public void addResetHandler(EventHandler<ActionEvent> handler) {
		btnReset.setOnAction(handler);
	}
	
	public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	public void updateTextField1(int count) {
		dan.setText(String.valueOf(count));
		if (count<0)
			dan.setText(String.valueOf(0));
	}
	public int getValueTextField1() {
		 return Integer.valueOf(dan.getText());
	}
	public void updateTextField2(int count) {
		barak.setText(String.valueOf(count));
		if (count<0)
			barak.setText(String.valueOf(0));
	}
	public int getValueTextField2() {
		 return Integer.valueOf(barak.getText());
	}
	public void submitBtnDisableBind(BooleanBinding value) {
		btnSubmit.disableProperty().bind(value);
	}
}
