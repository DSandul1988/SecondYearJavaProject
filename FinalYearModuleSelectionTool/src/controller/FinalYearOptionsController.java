package controller;
import java.io.FileWriter;
import java.io.IOException;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;

import model.RunPlan;
import model.Module;
import model.Name;

import model.StudentProfile;
import view.FinalYearOptionsRootPane;
import view.ReserveModulesPane;
import view.ReserveModulesPane2;
import view.SelectModulesPane;
import view.SelectionOverPane;
import view.CreateStudentProfilePane;
import view.FinalYearOptionsMenuBar;

public class FinalYearOptionsController {

	//fields to be used throughout class
	private FinalYearOptionsRootPane view;
	private StudentProfile model;
	private SelectModulesPane sm;
	private CreateStudentProfilePane cspp;
	private FinalYearOptionsMenuBar mstmb;
	private SelectionOverPane sov;
private ReserveModulesPane rmp;
private ReserveModulesPane2 rmp2;
	public FinalYearOptionsController(StudentProfile model, FinalYearOptionsRootPane view) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		mstmb.addLoadHandler(new LoadMenuHandler());
		sov =view.getSelectOverPane();
		rmp =view.getReservedModulesPane();
		rmp2 = view.getReservedModulesPane2();
		//add courses to combobox in create student profile pane using the buildModulesAndCourses helper method below
		cspp.addCourseDataToComboBox(buildModulesAndCourses());
		cspp.addCreateStudentProfileHandler(new CreateProfile() );
		sm=view.getSelectModulesPane();	
		mstmb.addSaveHandler(new SaveMenuHandler() );
		sm.addYearLong(buildModulesAndCourses());
		sm.updateTextField1(45);
		sm.updateTextField2(45);
        sm.addAddHandler(new AddModHandler1());
        sm.addAdd2Handler(new AddModHandler2());
        sm.addRemoveHandler(new AddRemoveSelected1());
        sm.addRemove2Handler(new AddRemoveSelected2());
        sm.addSubmitHandler(new SubmitHandler());
        sm.addResetHandler(new AddResetModHandler());
        rmp.addAddHandler(new AddReservedHandler());
        rmp2.addAddHandler(new AddReservedHandler2());
        rmp.addConfirmHandler(new AddConfirmReservedHandler1());
        rmp2.addConfirmHandler(new AddConfirmReservedHandler2());
        rmp.addRemoveHandler(new AddRemoveReservedHandler1());
        rmp2.addRemoveHandler( new AddRemoveReservedHandler2());
		sov.addSave(new AddSaveOverviewHandler2 ());//attach event handlers to view using private helper method
		this.attachEventHandlers();
		this.attachBindings();
	}

	private void attachBindings() {
		//attaches a binding such that the submit button in the view will be disabled whenevehe contents of the ListView is empty
		cspp.submitBtnDisableBind(cspp.getTextEmail().textProperty().isEmpty());
		cspp.submitBtnDisableBind(cspp.getTextFName().textProperty().isEmpty());
		cspp.submitBtnDisableBind(cspp.getTextLName().textProperty().isEmpty());
		sm.submitBtnDisableBind(Bindings.size(sm.getSelectedYear1()).lessThan(3));
		sm.submitBtnDisableBind(Bindings.size(sm.getSelectedYear2()).lessThan(3));
		rmp.submitBtnDisableBind(Bindings.size(rmp.getReservedModules()).lessThan(2));
		rmp2.confirmBtnDisableBind(Bindings.size(rmp2.getReservedModules()).lessThan(2));
	}

	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateProfile());
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
	}
	
	
	private class AddModHandler1 implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {	
			//retrieves name from view
			Module m = sm.getUnSelectedModule1();
		int sum =0 ;
			if(!sm.getSelectedYear1().contains(m)&&sm.getSelectedYear1().size()<=2) {
				sm.getSelectedYear1().add(m);
				sm.getUnselectedModules1().remove(sm.getUnselectedModules1().indexOf(m));}
			 else	alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't add  module ");
		
			for (Module n : sm.getSelectedYear1()) {	
			if (sum>=45) {
			alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Please select no more than 60 credits ");}
			sum= sum + sm.getUnSelectedModule1().getModuleCredits();	
			}
			sm.updateTextField1(sm.getValueTextField1()-15);
	}}
	private class AddResetModHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {	
			//retrieves name from view
			sm.getSelectedYear1().clear();
			sm.getSelectedYear2().clear(); 
			sm.updateTextField1(45);
			sm.updateTextField2(45);
			cspp.getTextEmail().clear();
			cspp.getTextFName().clear();
			cspp.getTextLName().clear();
			cspp.getTextPn().clear();
			view.changeTab(0);
			
	}}
	
	private class AddModHandler2 implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {	
			//retrieves name from view
			Module m = sm.getUnSelectedModule2();
			int sum =0;
			if (!sm.getSelectedYear2().contains(m)&&sm.getSelectedYear2().size()<=2) {
			sm.getSelectedYear2().add(m);
			sm.getUnselectedModules2().remove((m));}
			 else	alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't add  module ");
			for (Module n : sm.getSelectedYear2()) {
			if (sum>=45) {
			alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Please select no more than 60 credits");}
			sum= sum + sm.getUnSelectedModule2().getModuleCredits();
			
			}
			if (sm.getValueTextField2()>=15)
				sm.updateTextField2(sm.getValueTextField2()-15);
			else	sm.updateTextField1(0);
	}}
	
	private class AddRemoveSelected1 implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {	
			//retrieves name from view
			Module m = sm.getSelectedModule1();
			
			sm.getSelectedYear1().remove(m);
			sm.getUnselectedModules1().add(m);
			sm.updateTextField1(sm.getValueTextField1()+15);
	}}
	
	private class AddRemoveSelected2 implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {	
			//retrieves name from view
			Module m = sm.getSelectedModule2();
			
			sm.getSelectedYear2().remove(m);
			sm.getUnselectedModules2().add(m);
			sm.updateTextField2(sm.getValueTextField2()+15);
	}}
	
	//helper method - builds modules and course data and returns courses within an array
	private Course[] buildModulesAndCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, RunPlan.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, RunPlan.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, RunPlan.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, RunPlan.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, RunPlan.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, RunPlan.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, RunPlan.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, RunPlan.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, RunPlan.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, RunPlan.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, RunPlan.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, RunPlan.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, RunPlan.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, RunPlan.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, RunPlan.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, RunPlan.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, RunPlan.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, RunPlan.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}
	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.showAndWait();
	
	}
	private void alertDialogBuilder1(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	
	}
	private class CreateProfile implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//retrieves the selected opponent
			
			
			//creates and sets the default human player
			StudentProfile student = new StudentProfile();
			student.setStudentPnumber(cspp.getStudentPnumber());
			student.setStudentCourse(cspp.getSelectedCourse());
			student.setStudentEmail(cspp.getStudentEmail());
			student.setStudentName(cspp.getStudentName());
			student.setSubmissionDate((java.time.LocalDate.now()));
            for (Module m : rmp.getReservedModules()) {
            	student.addReservedModule(m);
            }
            	
            
           // for(Module m : rmp2.getReservedModules()) {
            //	student.getAllReservedModules().add(m);
           // }
            for(Module n : sm.getSelectedYear1()) {
            	student.addSelectedModule(n);
            }
            
			if (cspp.getSelectedCourse().getCourseName()!=("Computer Science")){
				sm.updateTextField1(30);
				sm.updateTextField2(30);
			sm.addYear1E(buildModulesAndCourses());
			sm.addYear2E(buildModulesAndCourses());}
				else {sm.addYear1(buildModulesAndCourses());
			sm.addYear2(buildModulesAndCourses());
			sm.updateTextField1(30);
			sm.updateTextField2(45);
			}	
			view.changeTab(1);
		    sov.setProfileField("Profile overwiew : \n"+"----------------------\n"+ "Student name  : " +student.getStudentName().getFullName()
		    		+"\n"+ "Student P number  : "+student.getStudentPnumber() +"\n" +"Student Email  : "+student.getStudentEmail() +"\n"+
		    		"Student course : "+student.getStudentCourse() +"\n"+ "Submission date : " +student.getSubmissionDate().toString()
		    +"\n");
		}}
		private class SubmitHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				
				for (Module m : sm.getUnselectedModules1()) {
					rmp.getUnselectedModules().add(m);
					}
				
				for (Module n : sm.getUnselectedModules2()) {
					rmp2.getUnselectedModules().add(n);
					}
				String s = "";
				String p ="";
				
				for (Module m : sm.getSelectedYear1()) {
					s = s + "Module Name  :" + m.getModuleName() +"\n"+
				"Module code  :" + m.getModuleCode() +"\n"+ "Module  delivery term  :" + m.getDelivery().toString()+"\n";
				}
				for (Module n :sm.getSelectedYear2()) {
					p = p+ "Module Name :" + n.getModuleName() +"\n"+
							"Module  code :" + n.getModuleCode() +"\n"+ "Module  delivery term  :" + n.getDelivery().toString()+"\n";
				}
				
				sov.setSelectedField("Selected modules \n" +"-------------\n"+"-------------\n"+s +"\n"+p);
				
				view.changeTab(2);
			  
			}
	}
		private class AddReservedHandler implements EventHandler<ActionEvent> {

			public void handle(ActionEvent e) {	
				//retrieves name from view
				Module m = rmp.getUnselectedModule();
			int sum =0;
				if(!rmp.getReservedModules().contains(m)&&rmp.getReservedModules().size()<=1) {
					rmp.getReservedModules().add(m);
					rmp.getUnselectedModules().remove(m);}
			 else	 alertDialogBuilder(AlertType.ERROR, "Error Dialog",  "Can't add  module ",null);
			
				for (Module n :rmp.getReservedModules()) {	
				if (sum>30) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", "Please select no more than 60 credits ",null);}
				sum= sum + m.getModuleCredits();	
				}
				
		}}
		
		private class AddReservedHandler2 implements EventHandler<ActionEvent> {

			public void handle(ActionEvent e) {	
				//retrieves name from view
				Module m = rmp2.getUnselectedModule();
			int sum =0;
				if(!rmp2.getReservedModules().contains(m)&&rmp2.getReservedModules().size()<=1) {
					rmp2.getReservedModules().add(m);
					rmp2.getUnselectedModules().remove(m);}
			 else	 alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't add  module ");
			
				for (Module n :rmp2.getReservedModules()) {	
				if (sum>30) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog",  "Please select no more than 60 credits ", null);}
				sum= sum + m.getModuleCredits();	
				}
				
		}}
		private class AddConfirmReservedHandler1 implements EventHandler<ActionEvent> {

			public void handle(ActionEvent e) {	
				//retrieves name from view
				alertDialogBuilder(AlertType.CONFIRMATION, "Confirmation", null, "Modules saved");
				view.changeAcc(1);
				}
				
		}
		private class AddConfirmReservedHandler2 implements EventHandler<ActionEvent> {

			public void handle(ActionEvent e) {	
				String s = "";
				for (Module m : rmp.getReservedModules()) {
					s = s+ "Module name :"+m.getModuleName() +"\n"+ "Module credits : "+ m.getModuleCredits()+"\n";
				}
				
				String p ="";
				
				for (Module n : rmp2.getReservedModules()) {
					p= p +  "Module name :"+n.getModuleName() +"\n"+ "Module credits : "+ n.getModuleCredits()+"\n";
				}
			sov.setReservedField("Reserved Modules \n" +"-------------\n"+"---------------\n"+ 
			s +"\n"+p+"\n");
			
					
				
					
				
				alertDialogBuilder(AlertType.CONFIRMATION, "Confirmation", null, "Modules saved");
			view.changeTab(3);
			
				}
				
		}
		private class AddRemoveReservedHandler1 implements EventHandler<ActionEvent> {

			public void handle(ActionEvent e) {	
				//retrieves name from view
				Module m = rmp.getReservedModule();
				rmp.getReservedModules().remove(m);
				rmp.getUnselectedModules().add(m);
				
				}}
			private class AddRemoveReservedHandler2 implements EventHandler<ActionEvent> {

				public void handle(ActionEvent e) {	
					//retrieves name from view
				Module m=	rmp2.getReservedModule();
				rmp2.getReservedModules().remove(m);
					rmp2.getUnselectedModules().add(m);
					}
				
}	
			private class AddSaveOverviewHandler2 implements EventHandler<ActionEvent> {

				public void handle(ActionEvent e) {	
					
					 
				 String student= view.getStudentProfile().toString();
				 try {
			            FileWriter fileWriter = new FileWriter("output.txt");
			            fileWriter.write(student);
			            fileWriter.close();
			           
			        } catch (IOException b) {
			          
			            b.printStackTrace();
			        }
			    }
				
					}
				
	
			private class SaveMenuHandler implements EventHandler<ActionEvent> {
				public void handle(ActionEvent e) {          
					//save the data model
					try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student1.dat"));) {
						
						oos.writeObject(view.getStudentProfile()); //writes the model object to a file
						alertDialogBuilder1(AlertType.CONFIRMATION, "Information Dialog", "Save success", "Student data saved to  file");
						oos.flush();
						
						oos.close();
						
					}
					catch (IOException ioExcep){
						System.out.println("Error saving");
					}
				}
			}
	
			private class LoadMenuHandler implements EventHandler<ActionEvent> {
				public void handle(ActionEvent e) {
					//load in the data model
					try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student1.dat"));) {
						
						model = (StudentProfile) ois.readObject(); //reads the model object back from a file	
						view.setStudentProfile(model);
						alertDialogBuilder1(AlertType.CONFIRMATION, "Information Dialog", "Load success", "Student data loaded from the file");
						
						ois.close();
			        }
			        catch (IOException ioExcep){
			            System.out.println("Error loading");
			        }
					catch (ClassNotFoundException c) {
			            System.out.println("Class Not found");
			        }	

					//refresh the view
				
				}
			}

}

