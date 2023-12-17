package view;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Course;

import model.Module;
import model.Name;
import model.RunPlan;
import model.StudentProfile;



public class FinalYearOptionsRootPane extends BorderPane  {
    private StudentProfile ps;
	private CreateStudentProfilePane cspp;
	private FinalYearOptionsMenuBar mstmb;
	private TabPane tp;
	private SelectModulesPane mp;
	private ReserveModulesPane rp;
	private ReserveModulesPane2 rp1;
	private SelectionOverPane  sp;
	 private Accordion acco;
	public FinalYearOptionsRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		
		//add tabs to tab pane
		tp.getTabs().addAll(t1);
		
		//create menu bar
		mstmb = new FinalYearOptionsMenuBar();
		this.setMinSize(100, 200);
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
		
		
		mp= new SelectModulesPane();
		Tab t2 = new Tab("Select Modules",mp);	
		GridPane.setVgrow(mp, Priority.ALWAYS);
		tp.getTabs().addAll(t2);
		this.setCenter(tp);
		
		rp = new ReserveModulesPane();
		rp1 = new ReserveModulesPane2();
	 acco = new Accordion();
		acco.getPanes().addAll(new TitledPane("Add reserved modules for Term 1",rp), new TitledPane("Add reserved modules for Term 2",rp1));
		Tab t3 = new Tab("Reserve Modules", acco);
		tp.getTabs().addAll(t3);
		this.setCenter(tp);
		
		sp=new SelectionOverPane ();
		 Tab t4 = new Tab("OverView Selection",sp);
		 tp.getTabs().add(t4);
		 this.setCenter(tp);
		 
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}
	public SelectModulesPane getSelectModulesPane() {
		return mp;
	}
	public SelectionOverPane getSelectOverPane() {
		return sp;
	}
	public ReserveModulesPane getReservedModulesPane() {
		return rp;
	}
	public ReserveModulesPane2 getReservedModulesPane2() {
		return rp1;
	}
	public FinalYearOptionsMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}
	public StudentProfile getStudentProfile() {
       Name Name = cspp.getStudentName();
      
       String email =cspp.getStudentEmail();
       String pnum =cspp.getStudentPnumber();
       LocalDate date =cspp.getStudentDate();
       Course c = cspp.getSelectedCourse();
      Set <Module> select = new TreeSet<Module>();
       for (Module m : mp.getSelectedModulesYear1()) {
    	  select.add(m);
       }
       for (Module n : mp.getSelectedModulesYear2()) {
    	   select.add(n);
       }
       Set <Module> reserv = new TreeSet<Module>();
      for (Module p : rp.getReservedModules()) {
      reserv.add(p);
       }
      for (Module p : rp1.getReservedModules()) {
      reserv.add(p);
       }
     
        return new StudentProfile(pnum,email,Name,date,c, select,reserv);
    }
	public void setStudentProfile(StudentProfile s) {
	       cspp.setStudentEmail(s.getStudentEmail());
	       cspp.setStudentPnumber(s.getStudentPnumber());
	       cspp.setStudentName(s.getStudentName().getFirstName(), s.getStudentName().getFamilyName());
	       cspp.setStudentDate(s.getSubmissionDate());
	       cspp.setCourse(s.getStudentCourse());
	      	for (Module m: s.getAllSelectedModules())    {
	      		if (m.getDelivery() ==RunPlan.TERM_1) {
	      			mp.getSelectedModulesYear1().add(m);
	      		}
	      	}
	      	for (Module n :s.getAllSelectedModules()) {
	      		if (n.getDelivery()==RunPlan.TERM_2) {
	      			mp.getSelectedYear2().add(n);
	      		}
	      	}
	      	for (Module m : s.getAllReservedModules()) {
	      		if (m.getDelivery()==RunPlan.TERM_1){
	      			rp.getReservedModules().add(m);
	      		}
	      	}
	      	for (Module n : s.getAllReservedModules()) {
	      		if (n.getDelivery()==RunPlan.TERM_2) {
	      			rp1.getReservedModules().add(n);
	      		}
	      	}
	    }
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
	public void changeAcc(int index) {
		acco.getPanes().get(index);
		
	}
}
