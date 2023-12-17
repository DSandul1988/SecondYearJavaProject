package view;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import model.Course;
import model.Name;

public class CreateStudentProfilePane extends GridPane  implements Serializable{

	private ComboBox<Course> cboCourses;
	private DatePicker inputDate;
	private TextField txtFirstName, txtSurname,  txtPnumber, txtEmail;
	private Button btnCreateProfile;

	public CreateStudentProfilePane() {
		//styling
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);
		this.setPrefSize(600, 450);
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.RIGHT);

		this.getColumnConstraints().addAll(column0);
		
		//create labels
		Label lblTitle = new Label("Select course: ");
		lblTitle.setId("Title");
		Label lblPnumber = new Label("Input P number: ");
		Label lblFirstName = new Label("Input first name: ");
		Label lblSurname = new Label("Input surname: ");
		Label lblEmail = new Label("Input email: ");
		Label lblDate = new Label("Input date: ");
		
		//initialise combobox
		cboCourses = new ComboBox<Course>(); //this is populated via method towards end of class
		
		//setup text fields
		txtFirstName = new TextField();
		txtSurname = new TextField();
		txtPnumber = new TextField();
		txtEmail = new TextField();
		inputDate = new DatePicker();
		
		//initialise create profile button
		btnCreateProfile = new Button("Create Profile");
		
		TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z ]{0,15}")) {
                return change;
            } else {
                return null;
            }
        });
		txtFirstName.setPromptText("Enter your name");
		txtFirstName.setTextFormatter(textFormatter);
		
		TextFormatter<String> textFormatter2 = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z ]{0,15}")) {
                return change;
            } else {
                return null;
            }
        });
		txtPnumber.setPromptText("Enter your P number ");
		txtSurname.setPromptText("Enter your name");
		txtSurname.setTextFormatter(textFormatter2);
		txtPnumber.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[p,P]\\d{0,7}")) {
                return change;
            } else {
                return null;
            }
        }));
		
        /* UnaryOperator<TextFormatter.Change> emailFilter = change -> {
            // Get the new text
            String newText = change.getControlNewText();
            
            // Create a regular expression pattern for email validation
            Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{0,2}");
            
            // Check if the new text matches the email pattern
            if (emailPattern.matcher(newText).matches()) {
                // Accept the new text
                return change;
            } else {
                // Reject the new text
                return null;
            }
        };*/
        
        // Set the email filter to the text formatter
        //TextFormatter<String> emailFormatter = new TextFormatter<>(emailFilter);
		txtEmail.setPromptText("Enter DMU e -mail");
	        
		txtEmail.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[p,P]\\d{0,7}+@{0,1}+[m,y,3,6,5]{0,5}+.{0,1}+[d,m,u]{0,3}+.{0,1}+[a,c]{0,3}+.{0,1}+[u,k]{0,2}")) {
                return change;
            } else {
                return null;
            }
        })); ;
         
		this.add(lblTitle, 0, 0);
		this.add(cboCourses, 1, 0);

		this.add(lblPnumber, 0, 1);
		this.add(txtPnumber, 1, 1);
		
		this.add(lblFirstName, 0, 2);
		this.add(txtFirstName, 1, 2);

		this.add(lblSurname, 0, 3);
		this.add(txtSurname, 1, 3);
		
		this.add(lblEmail, 0, 4);
		this.add(txtEmail, 1, 4);
		
		this.add(lblDate, 0, 5);
		this.add(inputDate, 1, 5);
			
		this.add(new HBox(), 0, 6);
		this.add(btnCreateProfile, 1, 6);
	}
	
	//method to allow the controller to add courses to the combobox
	public void addCourseDataToComboBox(Course[] courses) {
		cboCourses.getItems().addAll(courses);
		cboCourses.getSelectionModel().select(0); //select first course by default
	}
	
	//methods to retrieve the form selection/input
	public Course getSelectedCourse() {
		return cboCourses.getSelectionModel().getSelectedItem();
	}
	
	public String getStudentPnumber() {
		return txtPnumber.getText();
	}
	public void setStudentPnumber(String s) {
		txtPnumber.setText(s);
	}
	
	public Name getStudentName() {
		return new Name(txtFirstName.getText(), txtSurname.getText());
	}
	public void setStudentName(String s, String p) {
		txtFirstName.setText(s); txtSurname.setText(p);
	}
	public String getStudentEmail() {
		return txtEmail.getText();
	}
	public void setStudentEmail(String s) {
		txtEmail.setText(s); 
	}
	public void setCourse(Course s) {
		cboCourses.setValue(s);
	}
	public void setStudentDate(LocalDate s) {
		inputDate.setValue(s);
	}
	
	public LocalDate getStudentDate() {
		return inputDate.getValue();
	}
	public TextField getTextFName() {
		return txtFirstName;
	}
	public TextField getTextLName() {
		return txtSurname;
	}
	public TextField getTextEmail() {
		return txtEmail;
	}
	public TextField getTextPn() {
		return txtPnumber;
	}
	//method to attach the create student profile button event handler
	public void addCreateStudentProfileHandler(EventHandler<ActionEvent> handler) {
		btnCreateProfile.setOnAction(handler);
	}
	public void submitBtnDisableBind(BooleanBinding value) {
		btnCreateProfile.disableProperty().bind(value);
	}
	
}
