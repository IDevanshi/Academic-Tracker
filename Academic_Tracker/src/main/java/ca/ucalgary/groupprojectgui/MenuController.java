package ca.ucalgary.groupprojectgui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static ca.ucalgary.groupprojectgui.Menu.*;

/**
 * @author Amy Wiebe, Tutorial 13
 * @author Cassandra Rodberg, Tutorial 17
 * @author Devanshi Iragavarapu, Tutorial 08
 * @date April 15th, 2024
 * @presenting Tutorial 13
 */

public class MenuController {

    /**
     * All the GUI elements created locally to be used in functions.
     */
    private Alert alert = new Alert( Alert.AlertType.NONE );

    @FXML
    private Label outputLabel;

    @FXML
    private TextField aCourse;

    @FXML
    private TextField aName;

    @FXML
    private DatePicker aDate;

    @FXML
    private TextField aWeight;

    @FXML
    private TextArea aNotes;

    @FXML
    private TextField aGrade;

    @FXML
    private TextField aProgress;

    @FXML
    private TextField tCourse;

    @FXML
    private TextField tName;

    @FXML
    private DatePicker tDate;

    @FXML
    private TextField tWeight;

    @FXML
    private TextArea tNotes;

    @FXML
    private TextField tGrade;

    @FXML
    private TextField tTime;

    @FXML
    private TextField tLocation;

    @FXML
    private Label secondaryInputLabel1;

    @FXML
    private Label secondaryInputLabel2;

    @FXML
    private Label infoInputLabel;

    @FXML
    private TextField secondaryIP1;

    @FXML
    private TextField secondaryIP2;

    @FXML
    private Button saveAssignmentChangesButton;

    @FXML
    private Button getAssignmentChangesButton;

    @FXML
    private Label statusLabel;

    /**
     * Creates an assignment using the GUI fields, and updates the status label accordingly.
     */
    @FXML
    private void createAssignment() {
        //Setting default values for floats and initializing variables
        String stringCourse = aCourse.getText();
        String name = aName.getText();
        String date = String.valueOf( aDate.getValue() );
        float weight = -1.0f;
        float grade = 0;
        String notes = aNotes.getText();
        float progress = 0;
        if (aCourse.getText().isBlank()){
            statusLabel.setText( "Course needed to add the assignment." );
        }
        else if (aName.getText().isBlank()) {
            statusLabel.setText( "Assignment name needed to add the assignment." );
        }
        else if (aDate.getValue() == null){
            statusLabel.setText( "Assignment date needed to add the assignment." );
        }
        else{ // Error catching to ensure the right information in inputted and received.
            try {
                if (!aWeight.getText().isBlank()) {
                    weight = Float.parseFloat( aWeight.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for assignment weight." ); // Updates status label.
            }
            try {
                if (!aGrade.getText().isBlank()) {
                    grade = Float.parseFloat( aGrade.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for assignment grade." );
            }
            try {
                if (!aProgress.getText().isBlank()) {
                    progress = Float.parseFloat( aProgress.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for assignment progress." );
            }
            //Setting if statements to make sure an assignment isn't added with default values set for progress, weight, and grade.
            if (weight >= 0 && grade > -1 && progress > -1) {
                if (stringCourse.isBlank()) {
                    statusLabel.setText( "Missing course name for assignment." );
                } else if (name.isBlank()) {
                    statusLabel.setText( "Missing assignment name." );
                } else if (date == null) {
                    statusLabel.setText( "Missing assignment date." );
                } else {
                    Course course = checkCourse( stringCourse );
                    if (validSubmissionName( course, name )) { // Checks if the submission is valid and then adds it, and clears the text fields.
                        course.addAssignment( course, name, date, weight, notes, grade, progress );
                        statusLabel.setText( "Assignment added!" );
                        aCourse.clear();
                        aName.clear();
                        aDate.setValue( null );
                        aWeight.clear();
                        aGrade.clear();
                        aNotes.clear();
                        aProgress.clear();
                    } else {
                        statusLabel.setText( "Assignment name already in use!" ); // Does not create assignment if it already exists.
                    }
                }
            } else {
                statusLabel.setText( "Assignment numerical values must be positive to add assignment" );
            }
        }


    }

    /**
     * Creates a test using the GUI fields, and updates the status label accordingly.
     */

    @FXML
    private void createTest() {
        //Setting default values for floats and initializing variables
        String stringCourse = tCourse.getText();
        String name = tName.getText();
        String date = String.valueOf( tDate.getValue() );
        float weight = -1.0f;
        float grade = 0;
        String notes = tNotes.getText();
        String time = tTime.getText();
        String location = tLocation.getText();
        if (tCourse.getText().isBlank()){
            statusLabel.setText( "Course needed to add the test." );
        }
        else if (tName.getText().isBlank()) {
            statusLabel.setText( "Test name needed to add the test." );
        }
        else if (tDate.getValue() == null){
            statusLabel.setText( "Test date needed to add the test." );
        }
        else if (tTime.getText().isBlank()) {
            statusLabel.setText( "Test time needed to add the test." );
        }
        else if (tLocation.getText().isBlank()){
            statusLabel.setText( "Test location needed to add the test." );
        }
        else {
            // Error catching based on user input.
            try {
                if (!tWeight.getText().isBlank()) {
                    weight = Float.parseFloat( tWeight.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for test weight." );
            }
            try {
                if (!tGrade.getText().isBlank()) {
                    grade = Float.parseFloat( tGrade.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for test grade." );
            }

            //Setting if statements to make sure an assignment isn't added with default values set for progress, weight, and grade.
            if (weight > 0 && grade > -1) {
                if (stringCourse.isBlank()) {
                    statusLabel.setText( "Missing course name for test." );
                } else if (name.isBlank()) {
                    statusLabel.setText( "Missing test name." );
                } else if (date == null) {
                    statusLabel.setText( "Missing test date." );
                } else if (time.isBlank()) {
                    statusLabel.setText( "Missing test time." );
                } else if (location.isBlank()) {
                    statusLabel.setText( "Missing test location." );
                } else {
                    Course course = checkCourse( stringCourse );
                    if (validSubmissionName( course, name )) {
                        // Adds test and clears fields once it's ensured the test is valid.
                        course.addTest( course, name, date, weight, notes, grade, time, location );
                        statusLabel.setText( "Test added!" );
                        tCourse.clear();
                        tName.clear();
                        tDate.setValue( null );
                        tWeight.clear();
                        tGrade.clear();
                        tNotes.clear();
                        tTime.clear();
                        tLocation.clear();
                    } else {
                        statusLabel.setText( "Test name already in use!" ); // Shows this status if there already exists an identical test.
                    }
                }
            } else {
                statusLabel.setText( "Test numerical values must be positive to add assignment" );
            }
        }
    }

    /**
     * Allows user to change the details of an assignment previously entered by user.
     * Uses existing assignments and allows user to accordingly update.
     */
    @FXML
    private void changeAssignmentData() {
        // Makes necessary fields visible and adds text to it accordingly.
        outputLabel.setVisible(false);
        outputLabel.setText( "" );
        infoInputLabel.setVisible( true );
        secondaryInputLabel1.setText( "Course" );
        secondaryInputLabel1.setVisible( true );
        secondaryInputLabel2.setText( "Assign. Name" );
        secondaryInputLabel2.setVisible( true );
        secondaryIP1.setVisible( true );
        secondaryIP2.setVisible( true );
        getAssignmentChangesButton.setVisible( true );
        getAssignmentChangesButton.setText( "Get Assignment" );
        saveAssignmentChangesButton.setOnMouseClicked( mouseEvent-> getChangedAssignment() ); // When user changes some detail(s) of
        // an assignment, they click the button and then it calls function to change it internally.

        infoInputLabel.setText( "Please enter the name of the course and name of assignment for the submission search." );
        if (!secondaryIP1.getText().isBlank()) {
            if (!secondaryIP2.getText().isBlank()) {
                String course = secondaryIP1.getText();
                String name = secondaryIP2.getText();
                statusLabel.setText( "Searching for submission..." );
                infoInputLabel.setText( "Please press Get Assignment button" ); // Gets the changed assignment after search if it exists.
            } else {
                statusLabel.setText( "No submission name entered for search" ); // Status if submission does not exist.
            }
        } else {
            statusLabel.setText( "No course entered for search." ); // Status if course of submission search is not entered.
        }
    }

    /**
     * Retrieves the changed assignment.
     */
    @FXML
    private void getChangedAssignment() {
                Assignment assignment = null;
                String course = "";
                String name = "";

                try {
                    // Check whether the secondary input texts are blank.
                    if (!secondaryIP1.getText().isBlank()) {
                        if (!secondaryIP2.getText().isBlank()) {
                            course = secondaryIP1.getText();
                            name = secondaryIP2.getText();
                            statusLabel.setText( "Searching for submission..." ); // Status to search for submission only if user has
                                                                                // entered all necessary values.
                        } else {
                            statusLabel.setText( "No submission name entered for search" );
                        }
                    } else {
                        statusLabel.setText( "No course entered for search." );
                    }

                    // Check whether course and name are not blank
                    if(!course.isBlank() && !name.isBlank()) {
                        Course courseName = returnCourse( course );
                        if (courseName == null) {
                            statusLabel.setText( "Course does not exist" ); // Status if course does not exist.
                        } else if (validSubmissionName( courseName, name )) {
                            statusLabel.setText( "Assignment does not exist" );
                        } else {
                            assignment = (Assignment) checkSubName( courseName, name );
                            aCourse.setText( assignment.returnCourseName() ); // Sets texts accordingly for assignment.
                            aName.setText( assignment.returnName() );
                            aDate.setValue( LocalDate.parse( assignment.returnDate() ) );
                            aWeight.setText( String.valueOf( assignment.returnWeight() ) );
                            aNotes.setText( assignment.returnNote() );
                            aGrade.setText( String.valueOf( assignment.returnGrade() ) );
                            aProgress.setText( String.valueOf( assignment.returnProgress() ) );
                            saveAssignmentChangesButton.setVisible( true );
                            saveAssignmentChangesButton.setText( "Save Assignment" );
                            saveAssignmentChangesButton.setOnMouseClicked( mouseEvent->saveChangedAssignment() ); // Saves changed assignment.
                        }
                    }
                } catch (Exception e) {
                    statusLabel.setText( "Submission name does not belong to an assignment." );
                }
    }

    /**
     * Saves updated assignment details.
     */
    @FXML
    private void saveChangedAssignment(){
        // Retrieve course name and assignment name
        Course courseName = returnCourse(secondaryIP1.getText());
        String name = secondaryIP2.getText();

        // Retrieve assignment object
        Assignment assignment = (Assignment) checkSubName( courseName, name );

        // Set assignment details
        if (aCourse.getText().isBlank()){
            statusLabel.setText( "Course needed to add the assignment." );
        }
        else if (aName.getText().isBlank()) {
            statusLabel.setText( "Assignment name needed to add the assignment." );
        }
        else if (aDate.getValue() == null){
            statusLabel.setText( "Assignment date needed to add the assignment." );
        }
        else {
            assignment.setCourse( aCourse.getText() );
            assignment.setName( aName.getText() );
            assignment.setDate( String.valueOf( aDate.getValue() ) );

            // Initialize variables for weight, grade, and progress
            float weight = 0;
            float grade = 0;
            float progress = 0;

            // Parse weight value
            try {
                if (!aWeight.getText().isBlank()) {
                    weight = Float.parseFloat( aWeight.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for assignment weight." );
            }

            // Parse grade value
            try {
                if (!aGrade.getText().isBlank()) {
                    grade = Float.parseFloat( aGrade.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for assignment grade." );
            }

            // Parse progress value
            try {
                if (!aProgress.getText().isBlank()) {
                    progress = Float.parseFloat( aProgress.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for assignment progress." );
            }

            // Set weight, note, grade, and progress for the assignment
            assignment.setWeight( weight );
            assignment.setNote( aNotes.getText() );
            assignment.setGrade( grade );
            assignment.setProgress( progress );

            // Update status label
            statusLabel.setText( "Assignment changed successfully." );

            // Clear input fields and hide input labels and buttons
            aCourse.clear();
            aName.clear();
            aDate.setValue( null );
            aWeight.clear();
            aGrade.clear();
            aNotes.clear();
            aProgress.clear();
            infoInputLabel.setVisible( false );
            secondaryInputLabel1.setVisible( false );
            secondaryInputLabel1.setText( "" );
            secondaryInputLabel2.setVisible( false );
            secondaryInputLabel2.setText( "" );
            secondaryIP1.setVisible( false );
            secondaryIP2.setVisible( false );
            secondaryIP1.clear();
            secondaryIP2.clear();
            saveAssignmentChangesButton.setVisible( false );
            getAssignmentChangesButton.setVisible( false );
        }
    }


    /**
     * changeTestData is function and is called when the user presses the Change Test Button.
     * It is for when the user wants to change an existing test,
     * It calls all set functions from TestObject
     * it also calls checkCourse, checkSubName
     */
    @FXML
    private void changeTestData() {
        // Display necessary input labels and fields
        outputLabel.setVisible(false);
        outputLabel.setText( "" );
        infoInputLabel.setVisible( true );
        secondaryInputLabel1.setText( "Course" );
        secondaryInputLabel1.setVisible( true );
        secondaryInputLabel2.setText( "Test Name" );
        secondaryInputLabel2.setVisible( true );
        secondaryIP1.setVisible( true );
        secondaryIP2.setVisible( true );
        getAssignmentChangesButton.setVisible( true );
        getAssignmentChangesButton.setText( "Get Test" );

        // Set behavior for Get Test button
        getAssignmentChangesButton.setOnMouseClicked(mouseEvent->getChangedTest());

        // Set instructional message
        infoInputLabel.setText( "Please enter the name of the course and name of test for the submission search." );

        // Check if inputs are not blank
        if (!secondaryIP1.getText().isBlank()) {
            if (!secondaryIP2.getText().isBlank()) {
                // If inputs are provided, set status and update instructional message
                String course = secondaryIP1.getText();
                String name = secondaryIP2.getText();
                statusLabel.setText( "Searching for submission..." );
                infoInputLabel.setText( "Please press Get Test button" );
            } else {
                // Inform user if submission name is missing
                statusLabel.setText( "No submission name entered for search" );
            }
        } else {
            // Inform user if course is missing
            statusLabel.setText( "No course entered for search." );
        }
    }

    /**
     * getChangedTest is called when the user has entered.
     * It is for when the user wants to change an existing test,
     * It calls all the set functions for the test,
     * it also calls returnCourse, validSubmissionName.
     */
    @FXML
    private void getChangedTest() {
        // Initialize variables
        TestObject test = null;
        String course = "";
        String name = "";
        try {
            // Check if secondary input fields are not blank
            if (!secondaryIP1.getText().isBlank()) {
                if (!secondaryIP2.getText().isBlank()) {
                    // Retrieve course and name from secondary input fields
                    course = secondaryIP1.getText();
                    name = secondaryIP2.getText();
                    // Set status for search initiation
                    statusLabel.setText( "Searching for submission..." );
                } else {
                    // Inform user if submission name is missing
                    statusLabel.setText( "No submission name entered for search" );
                }
            } else {
                // Inform user if course is missing
                statusLabel.setText( "No course entered for search." );
            }
            // Check if course and name are not blank
            if(!course.isBlank() && !name.isBlank()) {
                // Retrieve course object
                Course courseName = returnCourse( course );
                if (courseName == null) {
                    // Inform user if course does not exist
                    statusLabel.setText( "Course does not exist" );
                } else if (validSubmissionName( courseName, name )) {
                    // Inform user if test does not exist
                    statusLabel.setText( "Test does not exist" );
                } else {
                    // Retrieve test object
                    test = (TestObject) checkSubName( courseName, name );
                    // Set test details to UI elements
                    tCourse.setText( test.returnCourseName() );
                    tName.setText( test.returnName() );
                    tDate.setValue( LocalDate.parse( test.returnDate() ) );
                    tWeight.setText( String.valueOf( test.returnWeight() ) );
                    tNotes.setText( test.returnNote() );
                    tGrade.setText( String.valueOf( test.returnGrade() ) );
                    tTime.setText( test.returnTime() );
                    tLocation.setText( test.returnLocation() );
                    // Make save button visible and set its behavior
                    saveAssignmentChangesButton.setText( "Save Test" );
                    saveAssignmentChangesButton.setVisible( true );
                    saveAssignmentChangesButton.setOnMouseClicked( mouseEvent->saveChangedTest() );
                }
            }
        } catch (Exception e) {
            // Inform user if submission name does not belong to a test
            statusLabel.setText( "Submission name does not belong to a test." );
        }
    }

    /**
     * saveChangedTest is called when the user has presses the Save Test button.
     * It is for when the user wants to save changes for an existing test,
     * It calls all the set functions for the test,
     * it also calls checkSubName.
     */
    @FXML
    private void saveChangedTest(){
        // Retrieve course name and test name
        Course courseName = returnCourse(secondaryIP1.getText());
        String name = secondaryIP2.getText();

        // Retrieve test object
        TestObject test = (TestObject) checkSubName( courseName, name );
        //Check for blank inputs
        if (tCourse.getText().isBlank()){
            statusLabel.setText( "Course needed to add the test." );
        }
        else if (tName.getText().isBlank()) {
            statusLabel.setText( "Test name needed to add the test." );
        }
        else if (tDate.getValue() == null){
            statusLabel.setText( "Test date needed to add the test." );
        }
        else if (tTime.getText().isBlank()) {
            statusLabel.setText( "Test time needed to add the test." );
        }
        else if (tLocation.getText().isBlank()){
            statusLabel.setText( "Test location needed to add the test." );
        }
        else {
            // Set test details
            test.setCourse( tCourse.getText() );
            test.setName( tName.getText() );
            test.setDate( String.valueOf( tDate.getValue() ) );

            // Initialize variables for weight, grade, and progress
            float weight = 0;
            float grade = 0;
            float progress = 0;

            // Parse weight value
            try {
                if (!tWeight.getText().isBlank()) {
                    weight = Float.parseFloat( tWeight.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for test weight." );
            }

            // Parse grade value
            try {
                if (!tGrade.getText().isBlank()) {
                    grade = Float.parseFloat( aGrade.getText() );
                }
            } catch (NumberFormatException e) {
                statusLabel.setText( "Invalid numeric value for test grade." );
            }

            // Set weight, note, grade, time, and location for the test
            test.setWeight( weight );
            test.setNote( tNotes.getText() );
            test.setGrade( grade );
            test.setTime( tTime.getText() );
            test.setLocation( tLocation.getText() );

            // Update status label
            statusLabel.setText( "Test changed successfully." );

            // Clear input fields and hide input labels and buttons
            tCourse.clear();
            tName.clear();
            tDate.setValue( null );
            tWeight.clear();
            tGrade.clear();
            tNotes.clear();
            tTime.clear();
            tLocation.clear();
            infoInputLabel.setVisible( false );
            secondaryInputLabel1.setVisible( false );
            secondaryInputLabel1.setText( "" );
            secondaryInputLabel2.setVisible( false );
            secondaryInputLabel2.setText( "" );
            secondaryIP1.setVisible( false );
            secondaryIP2.setVisible( false );
            secondaryIP1.clear();
            secondaryIP2.clear();
            saveAssignmentChangesButton.setVisible( false );
            getAssignmentChangesButton.setVisible( false );
        }
    }


    /**
     * findAllTasks is a function made to make submissions more accessible.
     *
     * @return submissions arraylist, which is filled with arrays of various submissions that can be easily accessed through this function.
     */
    private static ArrayList<String[]> findAllTasks() {
        ArrayList<String[]> submissions = new ArrayList<>();
        for (Course course : Data.courses) {
            for (Submission submission : course.submissions) {
                String strSubmission = submission.toString();
                String[] arraySub = strSubmission.split(","); // Splits each submission's details by a ',' (comma).
                submissions.add(arraySub); // Adds the submissions to the arraylist line-by-line.
            }
        }
        return submissions;
    }

    /**
     * getAllTasks prints all assignments and tests for the user to peruse.
     */
    @FXML
    private void getAllTasks() {
        outputLabel.setVisible(true);
        infoInputLabel.setVisible( false );
        secondaryInputLabel1.setVisible( false );
        secondaryInputLabel1.setText("");
        secondaryInputLabel2.setVisible( false );
        secondaryInputLabel2.setText( "" );
        secondaryIP1.setVisible(false);
        secondaryIP2.setVisible(false);
        secondaryIP1.clear();
        secondaryIP2.clear();
        saveAssignmentChangesButton.setVisible(false);
        getAssignmentChangesButton.setVisible( false );
        statusLabel.setText("");

        if (Data.courses.isEmpty()) { // Message printed if there are no assignments or tests saved.
            outputLabel.setText("No submissions to show! Please enter a submission to see results.");
        } else {
            StringBuilder outputText = new StringBuilder("Submissions are listed below: \n\n");
            ArrayList<String[]> submissions = findAllTasks();
            for (String[] submission : submissions) {
                if (submission.length == 7) { // Checks whether the specific submission is an assignment or a test, and prints accordingly.
                    outputText.append("Assignment - Course: ").append(submission[0]).append("\nName: ").append(submission[1]).append("\nDate: ").append(submission[2]).append("\nWeight: ").append(submission[3]).append("\nNote: ").append(submission[4]).append("\nGrade: ").append(submission[5]).append("\nProgress: ").append(submission[6]).append("\n\n");
                } else {
                    outputText.append("Test - Course: ").append(submission[0]).append("\nName: ").append(submission[1]).append("\nDate: ").append(submission[2]).append("\nWeight: ").append(submission[3]).append("\nNote: ").append(submission[4]).append("\nGrade: ").append(submission[5]).append("\nTime: ").append(submission[6]).append("\nLocation: ").append(submission[7]).append("\n\n");
                }
            }
            outputLabel.setText(outputText.toString());
            statusLabel.setText("Loaded all tasks successfully!");
        }
    }


    /**
     * getLowestClassGrade goes through all classes and prints the class with the lowest grade.
     * Runs the getGrade function for each class to obtain this information.
     */
    @FXML
    private void getLowestClassGrade() {
        outputLabel.setVisible(true);
        infoInputLabel.setVisible( false );
        secondaryInputLabel1.setVisible( false );
        secondaryInputLabel1.setText("");
        secondaryInputLabel2.setVisible( false );
        secondaryInputLabel2.setText( "" );
        secondaryIP1.setVisible(false);
        secondaryIP2.setVisible(false);
        secondaryIP1.clear();
        secondaryIP2.clear();
        saveAssignmentChangesButton.setVisible(false);
        getAssignmentChangesButton.setVisible( false );
        statusLabel.setText("");

        float minGrade = 0;
        Course lowestClass;
        if (Data.courses.size() != 0) {
            Course className1 = Data.courses.getFirst(); // Gets the first class.
            minGrade = className1.getGrade(); // Sets the minimum grade to the grade of the first class.
            lowestClass = className1;
            for (Course className : Data.courses) { // Searches through the classes arraylist in Data.java.
                if(className.getGrade()<minGrade){
                    minGrade = className.getGrade(); // Sets a new minimum if there exists one lower.
                    lowestClass = className;
                }
            }
            outputLabel.setText("The lowest class grade is for " + lowestClass + " and is " + minGrade + ".");
            statusLabel.setText("Loaded lowest class grade successfully!");
        } else {
            outputLabel.setText("There are no classes to consider for lowest class grade.");
        }
    }

    /**
     * getClassGrade takes input on which course the user would like the grade for
     * and then to show the grade the function calculates it by calling course.getGrade
     * the function also calls returnCourse
     */
    @FXML
    private void getClassGrade() {
        outputLabel.setVisible(true);
        infoInputLabel.setVisible( false );
        secondaryInputLabel1.setVisible( false );
        secondaryInputLabel1.setText("");
        secondaryInputLabel2.setVisible( false );
        secondaryInputLabel2.setText( "" );
        secondaryIP1.setVisible(false);
        secondaryIP2.setVisible(false);
        secondaryIP1.clear();
        secondaryIP2.clear();
        saveAssignmentChangesButton.setVisible(false);
        getAssignmentChangesButton.setVisible( false );
        //Hiding unnecessary input box
        secondaryInputLabel2.setText( "" );
        secondaryIP2.clear();
        secondaryInputLabel2.setVisible( false );
        secondaryIP2.setVisible( false );
        getAssignmentChangesButton.setVisible( false );
        //Initializing the necessary input box
        infoInputLabel.setVisible(true);
        infoInputLabel.setText( "" );
        secondaryInputLabel1.setText( "" );
        secondaryInputLabel1.setVisible(true);
        secondaryIP1.clear();
        secondaryIP1.setVisible(true);

        infoInputLabel.setText("What class would you like to find the grade for: ");
        secondaryInputLabel1.setText("Course: ");

        // Handling the user input asynchronously to prevent freezing.
        secondaryIP1.setOnAction(event -> {
            String courseName = secondaryIP1.getText();
            if (!courseName.isEmpty()) {
                Course course = returnCourse(courseName); // Calls returnCourse function to output the course specified.
                if (course != null) {
                    float courseGrade = course.getGrade(); // Calls get grade function to get the grade of the course the user wants the check the grade of.
                    outputLabel.setText("The grade for " + courseName + " is " + courseGrade + ".");
                    statusLabel.setText("Loaded class grade successfully!");
                } else {
                    outputLabel.setText("Class does not exist.");
                    statusLabel.setText("Failed to get class grade.");
                }
            }
        });
    }


    /**
     * getGPA() function uses get grade function and calculates user's overall GPA.
     * Used in output options function.
     */
    @FXML
    private void getGPA() {
        outputLabel.setVisible(true);
        infoInputLabel.setVisible( false );
        secondaryInputLabel1.setVisible( false );
        secondaryInputLabel1.setText("");
        secondaryInputLabel2.setVisible( false );
        secondaryInputLabel2.setText( "" );
        secondaryIP1.setVisible(false);
        secondaryIP2.setVisible(false);
        secondaryIP1.clear();
        secondaryIP2.clear();
        saveAssignmentChangesButton.setVisible(false);
        getAssignmentChangesButton.setVisible( false );
        statusLabel.setText("");

        float[] grades = new float[Data.courses.size()];
        int gradesInd = 0;
        double GPA=0, totalGPA=0; // totalGPA adds up the GPA of every course, and GPA is the
                                // overall GPA (average of the sum GPA of courses).
        if(!Data.courses.isEmpty()){ // Calculates GPA only if there exists some task(s).
            for (Course course : Data.courses) {
                float grade = course.getGrade();
                grades[gradesInd] = grade;
                gradesInd++;
            }
            for (float grade : grades) {
                if(grade>=0 && grade<45) {
                    GPA = 0.00;
                    totalGPA = GPA+totalGPA;
                } else if(45<=grade && grade<50){ // Grade D
                    GPA = 1.00;
                    totalGPA = GPA+totalGPA;
                } else if (50<=grade && grade<55) { // D+
                    GPA = 1.30;
                    totalGPA = GPA+totalGPA;
                } else if (55<=grade && grade<60) { // C-
                    GPA = 1.70;
                    totalGPA = GPA+totalGPA;
                } else if (60<=grade && grade<65) { // C
                    GPA = 2.00;
                    totalGPA = GPA+totalGPA;
                } else if (65<=grade && grade<70) { // C+
                    GPA = 2.30;
                    totalGPA = GPA+totalGPA;
                } else if(70<=grade && grade<75) { // B-
                    GPA = 2.70;
                    totalGPA = GPA+totalGPA;
                } else if (75<=grade && grade<80) { // B
                    GPA = 3.00;
                    totalGPA = GPA+totalGPA;
                } else if (80<=grade && grade<85) { // B+
                    GPA = 3.30;
                    totalGPA = GPA+totalGPA;
                } else if (85<=grade && grade<90) { // A-
                    GPA = 3.70;
                    totalGPA = GPA+totalGPA;
                } else if (90<=grade && grade<95) { // A
                    GPA = 4.00;
                    totalGPA = GPA+totalGPA;
                } else if (95<=grade && grade<=100) { // A+
                    GPA = 4.00;
                    totalGPA = GPA+totalGPA;
                }
                GPA = totalGPA/grades.length;
            }
            outputLabel.setText("Your GPA is " + GPA + ".");
            statusLabel.setText("GPA calculated successfully!");
        }
        else{ // Print statement if there are no tasks saved.
            outputLabel.setText("No entries to check for.");
            statusLabel.setText("Failed to calculate GPA.");
        }
    }

    /**
     * Asks the user for a date that they would like to see what submissions they have due on.
     * Runs through the assignment arraylist due dates and adds assignments due that day to a separate arraylist.
     * Runs through the test arraylist due dates and adds tests due that day to a separate arraylist.
     * Prints all the assignments and tests that are due on the chosen day.
     */
    @FXML
    private void dailyDues() {
        outputLabel.setVisible(true);
        infoInputLabel.setVisible( false );
        secondaryInputLabel1.setVisible( false );
        secondaryInputLabel1.setText("");
        secondaryInputLabel2.setVisible( false );
        secondaryInputLabel2.setText( "" );
        secondaryIP1.setVisible(false);
        secondaryIP2.setVisible(false);
        secondaryIP1.clear();
        secondaryIP2.clear();
        saveAssignmentChangesButton.setVisible(false);
        getAssignmentChangesButton.setVisible( false );
        //Hiding unnecessary input box
        secondaryInputLabel2.setText( "" );
        secondaryIP2.clear();
        secondaryInputLabel2.setVisible( false );
        secondaryIP2.setVisible( false );
        getAssignmentChangesButton.setVisible( false );

        //Initializing the necessary input box
        statusLabel.setText("");
        infoInputLabel.setText("");
        secondaryInputLabel1.setText("");
        secondaryIP1.clear();
        secondaryIP1.setVisible(true);

        infoInputLabel.setText("Enter the date you would like to see " +
                "\nsubmissions required in the format yyyy-mm-dd: ");
        secondaryInputLabel1.setText("Date: ");
        infoInputLabel.setVisible( true );
        secondaryInputLabel1.setVisible(true);

        secondaryIP1.setOnAction(event -> {
            String chosenDate = secondaryIP1.getText();
            if (!chosenDate.isEmpty()) {
                ArrayList<String[]> dueOnDate = new ArrayList<>();
                for (int courseCounter = 0; courseCounter < Data.courses.size(); courseCounter++) {
                    for (Submission submission : Data.courses.get(courseCounter).submissions) {
                        if (submission.returnDate().equals(chosenDate)) {
                            String strSubmission = submission.toString();
                            String[] arraySub = strSubmission.split(",");
                            dueOnDate.add(arraySub);
                        }
                    }
                }
                if (!dueOnDate.isEmpty()) {
                    StringBuilder outputText = new StringBuilder("The submissions required that day are: \n\n");
                    for (String[] submissionPart2 : dueOnDate) {
                        if (submissionPart2.length == 7) {
                            outputText.append("Assignment - Course: ").append(submissionPart2[0]).append("\nName: ").append(submissionPart2[1]).append("\nDate: ").append(submissionPart2[2]).append("\nWeight: ").append(submissionPart2[3]).append("\nNote: ").append(submissionPart2[4]).append("\nGrade: ").append(submissionPart2[5]).append("\nProgress: ").append(submissionPart2[6]).append("\n\n");
                        } else {
                            outputText.append("Test - Course: ").append(submissionPart2[0]).append("\nName: ").append(submissionPart2[1]).append("\nDate: ").append(submissionPart2[2]).append("\nWeight: ").append(submissionPart2[3]).append("\nNote: ").append(submissionPart2[4]).append("\nGrade: ").append(submissionPart2[5]).append("\nTime: ").append(submissionPart2[6]).append("\nLocation: ").append(submissionPart2[7]).append("\n\n");
                        }
                    }
                    outputLabel.setText(outputText.toString());
                    statusLabel.setText("Loaded tasks due on asked date!");
                } else {
                    outputLabel.setText("There are no submissions due that day.");
                    statusLabel.setText("No tasks due on asked date!");
                }
            }
        });
    }

    /**
     * printClasses prints all stored Classes for the user.
     * If there are no classes to print, informs the user.
     */
    @FXML
    private void printClasses() { // Prints all classes if checked.
        outputLabel.setVisible(true);
        infoInputLabel.setVisible( false );
        secondaryInputLabel1.setVisible( false );
        secondaryInputLabel1.setText("");
        secondaryInputLabel2.setVisible( false );
        secondaryInputLabel2.setText( "" );
        secondaryIP1.setVisible(false);
        secondaryIP2.setVisible(false);
        secondaryIP1.clear();
        secondaryIP2.clear();
        saveAssignmentChangesButton.setVisible(false);
        getAssignmentChangesButton.setVisible( false );
        String classString;
        if (!Data.courses.isEmpty()) {
            classString = "The courses stored are shown below: \n\n";
            for (Course course : Data.courses) {
                String courseString = course.toString(); // Converts the object into a string using the course toString function.
                classString = classString + "Course:" + courseString + "\n";
            }
            statusLabel.setText("All courses loaded!");
            outputLabel.setText(classString);
        } else { // Output message if user has not saved any courses.
            outputLabel.setText("There are no classes available to print. ");
            statusLabel.setText("No courses to load.");
        }
    }



    /**
     * Retrieves data from the file name entered by user.
     */
    @FXML
    private void findFileNameRetrieve() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle( "Load File" );
        fileChooser.setInitialDirectory( new File("."));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file!= null) {
            retrieveData(file);
        }
    }

    /**
     * retrieveClassData is called at the start of the function starter and
     * reads from the Stored_Classes.txt file and recreates the classes array.
     */
    @FXML
    public void retrieveData(File file) {
        try{
            // Looks for the saved file for retrieval.
            Scanner sc = new Scanner(file);
            String[] submission;
            String attribute;

            // Takes the submission in string form and adds the course to courses if it does not exist.
            while(sc.hasNext()){
                attribute = sc.nextLine();
                submission = attribute.split(",");
                Course course = Menu.checkCourse(submission[0]);

                // Identifies if the submission is an assignment or test based on length.
                // Then adds it to the submissions of its specific course.
                if(submission.length==7){
                    Assignment assignment = new Assignment(submission[0], submission[1], submission[2], Float.parseFloat(submission[3]),
                            submission[4], Float.parseFloat(submission[5]), Float.parseFloat(submission[6]));
                    course.submissions.add(assignment);
                } else if (submission.length==8) {
                    TestObject test = new TestObject(submission[0], submission[1], submission[2], Float.parseFloat(submission[3]),
                            submission[4], Float.parseFloat(submission[5]), submission[6], submission[7]);
                    course.submissions.add(test);
                }
            }
            // Informs user whether the data retrieval was successful.
            statusLabel.setText("Saved data retrieved.");
        }
        catch(Exception e){
            if (file == null){
                statusLabel.setText("No file to read.");
            }else {statusLabel.setText("File cannot be read.");}

        }
    }

    /**
     * Asks user what file they want to store their data in.
     */
    @FXML
    private void findFileNameStore() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(new Stage());
        fileChooser.setInitialFileName("SavedCourseInformation.txt");
        fileChooser.setInitialDirectory(new File(""));
        if (file != null) {
            storeDataToFiles(file); // Calls storeDataToFiles function so user data is inputted into that file.
        }
    }

    /**
     * storeDataToFiles is called at the end of menu options and
     * is also called in maintenanceOptions menu if the user wants to manually save.
     * It saves the global arrays to their own file.
     */
    @FXML
    private void storeDataToFiles(File file) {
        try {
            // Indexes each submission for each course, converts it to string, and writes it to file.
            FileWriter writer1 = new FileWriter(file);
            for (Course course : Data.courses) {
                for (Submission submission : course.submissions) {
                    String subString = submission.toString();
                    writer1.write(subString + "\n");
                }
            }
            // Closes file and informs user that data is stored.
            writer1.close();
            statusLabel.setText("Current data stored.");
        }
        catch (IOException e){
            statusLabel.setText("File cannot be written to.");
        }
    }

    /**
     * Clears the arrayLists that contain the information for classes, assignments, and tests.
     */
    @FXML
    private void clearArray(){
        // clears all the information contained in the arrays
        for (Course courseCounter : Data.courses) {
            courseCounter.submissions.clear();
        }
        Data.courses.clear();
        statusLabel.setText("Unsaved data cleared.");
        // deletes and recreates classes storing file
    }

    /**
     * Clears all information, and deletes the file containing all information.
     */
    @FXML
    private void clear() {
        // deletes and recreates classes storing file
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file!= null) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                statusLabel.setText("File could not be cleared.");
            }
        }
        statusLabel.setText("Saved data cleared.");
    }

    /**
     * Presents the pop-up for about authors.
     */
    @FXML
    private void aboutAuthors() {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Authors");
        alert.setContentText("Author One: Amy Wiebe - Tutorial 13\namy.wiebe1@ucalgary.ca\nAuthor Two: Cassandra Rodberg - Tutorial 17\ncassandra.rodberg@ucalgary.ca\nAuthor Three: Devanshi Iragavarapu - Tutorial 8\ndevanshi.iragavarapu1@ucalgary.ca\nVersion: v3.0\nThis is a schedule tracker that allows you to organize all of " +
                "\nyour classes and the assignments and tests for them.");
        alert.show();
    }

    /**
     * Exits the program.
     */
    @FXML
    private void exitProgram() {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit the program?");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            System.exit(0);
        }
    }
}