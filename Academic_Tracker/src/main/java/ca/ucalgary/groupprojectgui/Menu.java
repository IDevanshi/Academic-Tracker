package ca.ucalgary.groupprojectgui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *                           -----MENU CLASS-----

 * Test class to check the functionality of our main functions.
 * @author Amy Wiebe, Tutorial 13
 * @author Cassandra Rodberg, Tutorial 17
 * @author Devanshi Iragavarapu, Tutorial 08
 * @date April 15th, 2024
 * @presenting Tutorial 13
 * Project Name: Academic Tracker
 * Description: Organizes all assignments and tests for all your classes. It also helps keep track of
 * upcoming tests and assignments, and allows you to check the statistics of your performance.
 */

public class Menu {
    /**
     * Global constants (a scanner and an array) that would be used throughout the class.
     */
    private static final Scanner sc = new Scanner(System.in);
    private static final String[] Main_Options = new String[6]; //there are 4 main menu options

    // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
    static { // The main menu options are listed below.
        Main_Options[0] = "Exit";
        Main_Options[1] = "Add Data";
        Main_Options[2] = "Specific Assignment Data";
        Main_Options[3] = "Specific Test Data";
        Main_Options[4] = "Output Options";
        Main_Options[5] = "Save/Clear";
    }

    private static String optionMessage = """
            Store and Access class and assessment information.
            \tMenu Options
            """;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(optionMessage);
        for (int i = 0; i < Main_Options.length; i++) {
            sb.append(String.format("\t%d. %s\n", i, Main_Options[i]));
        }
        optionMessage = sb.toString();
    }
    // End of cited code

    /**
     * Starts the program by running the functions that pull information from files to add to the arrayLists for classes, assignments, and tests.
     * Once all the arrayList information has been updated, the function runs menuLoop.
     */
    public static void starter(String fileName) {
        if (fileName != null) {
            File starterFile = new File(fileName);
            if (starterFile.exists() && starterFile.canRead()) {
                System.out.println("Information pulled from Main.java");
            } else {
                System.out.println("File is not accessible: running with no file attachment");
            }
        }
        menuLoop();
    }

    /**
     * The main menu that runs all the sub-menus and also allows you to exit the program.
     * While running the program, you are in the menuLoop function the entire time.
     * Gives the option to add general data, change assignment/test data, output information, save/clear information, and exit.
     */
    private static void menuLoop(){
        //loops through the main menu
        System.out.println(optionMessage);
        String choice = sc.nextLine();
        //invalid value for default
        int option = -1;
        boolean validOptionCheck = false;
        do {
            try{
                option = Integer.parseInt(choice);

                validOptionCheck = true;
            }
            catch(NumberFormatException e){
                System.out.println("The inputted value was not a number, please retry: ");
                choice = sc.nextLine();
            }
        }while(!validOptionCheck);

        // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
        while(option!=0){
            if(option>0 && option< Main_Options.length){
                System.out.printf("Selected option %d. %s%n", option, Main_Options[option]);
                System.out.println("Press any key to continue...");
                sc.nextLine();
            }

            switch(option){
                case 1 -> addData();
                case 2 -> changeAssignmentData();
               // case 3 -> //changeTestData();
                case 4 -> outputOptions();
                default -> System.out.printf("Option %d not recognized!%n", option);
            }
            System.out.println("Press any key to continue...");
            sc.nextLine();
            System.out.println(optionMessage);
            choice = sc.nextLine();
            option = Integer.parseInt(choice);
        }
        System.out.println("Thank you for using this class tracker.");
        System.exit(0);
    }
    // End of cited code

    /**
     * This is a sub-menu for adding general data, that allows you to add a class, assignment, or test.
     * This function is called by menuLoop, and calls addClass, addAssignment, and addTest
     */
    private static void addData() {
        System.out.println("Please enter the name of the course you are adding data for.");
        String stringCourse = sc.nextLine();
        while(stringCourse.isBlank()) {
            System.out.println("Please enter the name of the course you are adding data for.");
            stringCourse = sc.nextLine();
        }
        Course course = checkCourse(stringCourse);
        String[] dataOptions = new String[3]; //there are 3 addData options
        // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
        dataOptions[0] = "Exit";
        dataOptions[1] = "Add Assignment";
        dataOptions[2] = "Add Test";

        String dataOptionMessage = """
            Store class and assessment information.
            \tMenu Options:
            """;

        StringBuilder sb = new StringBuilder();
        sb.append(dataOptionMessage);
        for (int i = 0; i < dataOptions.length; i++) {
            sb.append(String.format("\t%d. %s\n", i, dataOptions[i]));
        }
        dataOptionMessage = sb.toString();
        System.out.println(dataOptionMessage);
        String choice = sc.nextLine();
        // End of cited code

        //invalid value for default
        int option = -1;
        boolean validOptionCheck = false;
        do {
            try{
                option = Integer.parseInt(choice);

                validOptionCheck = true;
            }
            catch(NumberFormatException e){
                System.out.println("The inputted value was not a number, please retry: ");
                choice = sc.nextLine();
            }
        }while(!validOptionCheck);

        // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
        while(option!=0) {
            if (option > 0 && option < dataOptions.length) {
                System.out.printf("Selected option %d. %s%n", option, dataOptions[option]);
                System.out.println("Press any key to continue...");
                sc.nextLine();
            }

            switch (option) {
                //case 1 -> course.addAssignment(course);
                //case 2 -> course.addTest(course);
                default -> System.out.printf("Option %d not recognized!%n", option);
            }
            System.out.println("Press any key to continue...");
            sc.nextLine();
            System.out.println(dataOptionMessage);
            choice = sc.nextLine();
            try {
                option = Integer.parseInt(choice);
            }catch (NumberFormatException e){
                System.out.println("Invalid input, must be an integer.");
                System.out.println(dataOptionMessage);
                choice = sc.nextLine();
            }

        }
        menuLoop();
    }

    // Class sub-function location begins

    // class sub-function location ends

    /**
     * returnCourse function is made to return specified course, to be used in other functions.
     * @param course takes in the specified course, for which the object needs to be returned.
     * @return currentCourse, which is an object of course class, taken from courses arrayList in Data.java. Returns null if specified course does not exist.
     */
    public static Course returnCourse(String course) {
        for (Course currentCourse : Data.courses) {
            if (currentCourse.equals(course)) {
                return currentCourse;
            }
        }
        return null;
    }

    /**
     * checkCourse is a function that is called in findClass, findAssignment, and findTest.
     * It allows the user to input the course name.
     * @return returns an object of the class Course.
     */
    public static Course checkCourse(String course){
        Course courseName;
        if (returnCourse(course) == null) {
            courseName = new Course(course);
            Data.courses.add(courseName);
        } else {
            courseName = returnCourse(course);
        }
        return courseName;
    }

    /**
     * Checks if a given course is valid.
     * @param course the course to be validated
     * @return true if the course is valid, false otherwise
     */
    public static boolean validCourse(String course) {
        boolean validCourse = true;
        for (Course currentCourse : Data.courses) {
            System.out.println(currentCourse);
            System.out.println(course);
            if (currentCourse.equals(course)) {
                System.out.println("hi");
                validCourse = false;
            }
        }
        return validCourse;
    }

    /**
     * checkSubName is a function that is called in findAssignment and findTest,
     * it allows the user to input the submission name
     * @return returns a String, the submission name.
     */
    public static Submission checkSubName(Course course, String name){
        for (Submission submissionCounter : course.submissions) {
            if (submissionCounter.equals(name)) {
                return submissionCounter;
            }
        }
        return null;
    }

    /**
     * validSubmissionName is a function that ensures the lack of duplicates.
     * @param course takes the course name of specified.
     * @param name takes the submission name specified.
     * @return validName which is a boolean that returns true if it is not a repeated task, else false.
     */
    public static boolean validSubmissionName(Course course, String name) {
        boolean validName = true;
        for (Submission submissionCounter : course.submissions) {
            if (submissionCounter.equals(name)) { // If submission of specified course is repeated it returns false, as the submission is no longer valid.
                validName = false;
            }
        }
        return validName;
    }

    /**
     * changeAssignmentData is submenu function and is called from menuLoop.
     * It is for when the user wants to change an existing assignment,
     * if none are available it sends you back to the main menu.
     * It calls all the get functions addAssignment does although here they are optional and the user can choose,
     * it also calls checkCourse, checkSubName and findAssignment.
     */
    private static void changeAssignmentData() {
        String[] dataAssignment = new String[7];
        System.out.println("Please enter the name of the course for the submission search.");
        String course = sc.nextLine();
        String name;
        int option = -1;
        Course courseName = null;
        Assignment assignment = null;

        //Work finding condition
            while (courseName == null || assignment == null) {
                boolean validOptionCheck = false;
                while (!validOptionCheck){
                    try {
                        System.out.println( "Please enter submission name for search." );
                        name = sc.nextLine();
                        System.out.println( "Searching for submission..." );
                        courseName = returnCourse( course );
                        if(courseName == null) {
                            System.out.println("Course does not exist");
                            option = 0;
                            break;
                        }
                        if (validSubmissionName( courseName, name )){
                            System.out.println("Assignment does not exist");
                            option = 0;
                            break;
                        }
                        else {
                            try{
                                assignment = (Assignment) checkSubName( courseName, name );
                                validOptionCheck = true;
                            } catch (Exception e){
                                System.out.println("Submission name does not belong to an assignment.");
                            }

                        }

                    } catch (NullPointerException e) {
                        System.out.println( "Please enter the name of the course for the submission search." );
                        course = sc.nextLine();
                    }
                }
                if(!validOptionCheck){
                    break;
                }
            }

            // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
            dataAssignment[0] = "Exit";
            dataAssignment[1] = "Change assignment name: ";
            dataAssignment[2] = "Add assignment due date: ";
            dataAssignment[3] = "Add assignment weighting: ";
            dataAssignment[4] = "Add assignment notes: ";
            dataAssignment[5] = "Add assignment progress update: ";
            dataAssignment[6] = "Add assignment grade: ";

            String dataAssignmentMessage = """
                    Store  and change assignment details.
                    \tMenu Options:
                    """;

            StringBuilder sb = new StringBuilder();
            sb.append( dataAssignmentMessage );
            for (int i = 0; i < dataAssignment.length; i++) {
                sb.append( String.format( "\t%d. %s\n", i, dataAssignment[i] ) );
            }
            dataAssignmentMessage = sb.toString();
            String choice = "";
            if (option == -1){
                System.out.println( dataAssignmentMessage );
                choice = sc.nextLine();
            }
            // End of cited code

            boolean validOptionCheck2 = false;
            // Error checking for non integer values.
            if (option == -1){
                do {
                    try {
                        option = Integer.parseInt( choice );
                        validOptionCheck2 = true;
                    } catch (NumberFormatException e) {
                        System.out.println( "The inputted value was not a number, please retry: " );
                        choice = sc.nextLine();
                    }
                } while (!validOptionCheck2);
            }

            // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
            while (option != 0) {
                if (option > 0 && option < dataAssignment.length) {
                    System.out.printf( "Selected assignment option %d. %s%n", option, dataAssignment[option] );
                    System.out.println( "Press any key to continue..." );
                    sc.nextLine();
                }
                int ind = Data.courses.indexOf( courseName );
                if (Data.courses.size() == 0 || Data.courses.get( ind ).submissions.size() == 0) {
                    System.out.println( "No assignments available to change at this moment. Exiting back to main menu" );
                    option = 0;
                } else {
//                    switch (option) {
//                        case 1 -> assignment.setName();
//                        case 2 -> assignment.setDate();
//                        case 3 -> assignment.setWeight();
//                        case 4 -> assignment.setNote();
//                        case 5 -> assignment.setProgress();
//                        case 6 -> assignment.setGrade();
//                        default -> System.out.printf( "Option %d not recognized!%n", option );
//                    }
                    System.out.println( "Press any key to continue..." );
                    sc.nextLine();
                    System.out.println( dataAssignmentMessage );
                    choice = sc.nextLine();
                    validOptionCheck2 = false;
                    do {
                        try{
                            option = Integer.parseInt(choice);
                            validOptionCheck2 = true;
                        }
                        catch(NumberFormatException e){
                            System.out.println("The inputted value was not a number, please retry: ");
                            choice = sc.nextLine();
                        }
                    }while(!validOptionCheck2);
                }
            }
            menuLoop();
    }
    // End of cited code



    // Assignment and test detail sub-function location begins
    // Assignment and test detail sub-function location ends

    /**
     * outputOptions runs a submenu where the user can interact with the data inputted.
     * It is called from the main menu and calls getAllTasks, getLowestClassGrade, getClassGrade,
     * getGPA, dailyDues, and printClasses.
     */
    private static void outputOptions() {
        //list of output options
        String [] outputOptions = new String[7];
        // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
        outputOptions[0] = "Exit";
        outputOptions[1] = "Ask for all assignments and tests to be printed";
        outputOptions[2] = "Ask for the class with the lowest grade";
        outputOptions[3] = "Ask for the grade of a class";
        outputOptions[4] = "Ask for GPA for total assignments and tests - submitted or otherwise";
        outputOptions[5] = "Ask for assignments and tests due on a specific day; ";
        outputOptions[6] = "Ask for all classes";
        String optionalOutputMessage = """
                \tOutput Options:
                """;

        StringBuilder sb = new StringBuilder();
        sb.append(optionalOutputMessage);
        for (int i = 0; i < outputOptions.length; i++) {
            sb.append(String.format("\t%d. %s\n", i, outputOptions[i])); // Formats and prints output options in order.
        }
        optionalOutputMessage = sb.toString();
        System.out.println(optionalOutputMessage);
        String choice = sc.nextLine();
        // End of cited code

        // Error checking during option selection.
        int option = -1;
        boolean validOptionCheck = false;
        do {
            try{
                option = Integer.parseInt(choice);
                validOptionCheck = true;
            }
            catch(NumberFormatException e){ // Input error checking (if user input is not an integer).
                System.out.println("The inputted value was not a number, please retry: ");
                choice = sc.nextLine();
            }
        }while(!validOptionCheck);

        // The following code is based on the outline shown in this video: https://d2l.ucalgary.ca/d2l/le/content/569159/viewContent/6215132/View
        while(option!=0) {
            if (option > 0 && option < outputOptions.length) {
                System.out.printf("Selected assignment option %d. %s%n", option, outputOptions[option]);
                System.out.println("Press any key to continue...");
                sc.nextLine();
            }

            switch (option) { // Switch case for the output options and its respective functions to be run.
                case 1 -> getAllTasks();
                case 2 -> getLowestClassGrade();
                case 3 -> getClassGrade();
                case 4 -> getGPA();
                case 5 -> dailyDues();
                case 6 -> printClasses();
                default -> System.out.printf("Option %d not recognized!%n", option); // Default in case the input was not one of the few in the cases.
            }
            System.out.println("Press any key to continue...");
            sc.nextLine();
            System.out.println(optionalOutputMessage);
            choice = sc.nextLine();
            validOptionCheck = false;
            do {
                try{
                    option = Integer.parseInt(choice);
                    validOptionCheck = true;
                }
                catch(NumberFormatException e){ // Input error checking (if user input is not an integer).
                    System.out.println("The inputted value was not a number, please retry: ");
                    choice = sc.nextLine();
                }
            }while(!validOptionCheck);
        }
        menuLoop();
    }
    // End of cited code

    // Output sub-function location begins

    /**
     * findAllTasks is a function made to make submissions more accessible.
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
     * @return Returns arraylist of all tasks.
    */
    //We need a toString function for assignment and test
    private static void getAllTasks() {
        //Comment added
        if (Data.courses.isEmpty()) { // Message printed if there are no assignments or tests saved.
            System.out.println("No submissions to show! Please enter a submission to see results.");
        } else {
            System.out.println("Submissions are listed below: ");
            ArrayList<String[]> submissions = findAllTasks();
            for (String[] submission : submissions) {
                if (submission.length == 7) { // Checks whether the specific submission is an assignment or a test, and prints accordingly.
                    System.out.println("Assignment - Course:" + submission[0] + " Name:" + submission[1] + " Date:" + submission[2] + " Weight:" + submission[3] + " Note:" + submission[4] + " Grade:" + submission[5] + " Progress:" + submission[6]);
                } else {
                    System.out.println("Test - Course:" + submission[0] + " Name:" + submission[1] + " Date:" + submission[2] + " Weight:" + submission[3] + " Note:" + submission[4] + " Grade:" + submission[5] + " Time:" + submission[6] + " Location:" + submission[7]);
                }
            }
        }
    }

    /**
     * getLowestClassGrade goes through all classes and prints the class with the lowest grade.
     * Runs the getGrade function for each class to obtain this information.
     */
    private static void getLowestClassGrade() {
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
            System.out.println("The lowest class grade is for " + lowestClass + " and is " + minGrade + ".");
        } else {
            System.out.println("There are no classes to consider for lowest class grade.");
        }
    }

    /**
     * Asks the user what class they are looking for a grade for.
     * Runs the getGrade function to obtain the grade for the class, and prints the grade.
     */
    private static void getClassGrade() {
        System.out.println("What class would you like to find the grade for: ");
        String courseName;
        do {
            courseName = sc.nextLine().trim();
        }while (courseName.isEmpty());
        Course course = returnCourse( courseName ); // Calls returnCourse function to output the course specified.
        if (course != null){
            float courseGrade = course.getGrade(); // Calls get grade function to get the grade of the course the user wants the check the grade of.
            System.out.println("The grade for " + courseName + " is " + courseGrade + "." );
        }
        else {
            System.out.println("Class does not exist.");
        }
    }

    /**
     * getGPA() function uses get grade function and calculates user's overall GPA.
     * Used in output options function.
     */
    private static void getGPA() {
        float[] grades = new float[Data.courses.size()];
        int gradesInd = 0;
        double GPA=0, totalGPA=0; // totalGPA adds up the GPA of every course, and GPA is the// overall GPA (average of the sum GPA of courses).
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
            System.out.println("Your GPA is " + GPA + ".");
        }
        else{ // Print statement if there are no tasks saved.
            System.out.println("No entries to check for.");
        }

    }

    /**
     * Asks the user for a date that they would like to see what submissions they have due on.
     * Runs through the assignment arraylist due dates and adds assignments due that day to a separate arraylist.
     * Runs through the test arraylist due dates and adds tests due that day to a separate arraylist.
     * Prints all the assignments and tests that are due on the chosen day.
     */
    private static void dailyDues() {
        ArrayList<String[]> dueOnDate = new ArrayList<>();
        String chosenDate;
        do{
            System.out.println("Enter the date you would like to see submissions required in the format dd/mm/yy: ");
            chosenDate = sc.nextLine();
        } while(chosenDate.isEmpty());
        for (int courseCounter = 0;  courseCounter< Data.courses.size() ; courseCounter++) {
            for (Submission submission:Data.courses.get(courseCounter).submissions){
                if (submission.returnDate().equals(chosenDate)){
                    String strSubmission = submission.toString();
                    String[] arraySub = strSubmission.split(",");
                        dueOnDate.add(arraySub);
                }
            }
        }
        if (!dueOnDate.isEmpty()) {
            System.out.println("The submissions required that day are: "); // Prints tasks due on entered date.
            for (String[] submissionPart2 : dueOnDate) {
                if (submissionPart2.length == 7) { // Checks whether the specific submission is an assignment or a test, and prints accordingly.
                    System.out.println("Assignment - Course:" + submissionPart2[0] + " Name:" + submissionPart2[1] + " Date:" + submissionPart2[2] + " Weight:" + submissionPart2[3] + " Note:" + submissionPart2[4] + " Grade:" + submissionPart2[5] + " Progress:" + submissionPart2[6]);
                } else {
                    System.out.println("Test - Course:" + submissionPart2[0] + " Name:" + submissionPart2[1] + " Date:" + submissionPart2[2] + " Weight:" + submissionPart2[3] + " Note:" + submissionPart2[4] + " Grade:" + submissionPart2[5] + " Time:" + submissionPart2[6] + " Location:" + submissionPart2[7]);
                }
            }
        } else {
            System.out.println("There are no submissions due that day."); // Print statement if no due dates on entered date.
        }
    }

    // Output sub-function location ends
    // sub function

    /**
     * printClasses prints all stored Classes for the user.
     * If there are no classes to print, informs the user.
     */
    private static void printClasses() { // Prints all classes if checked.
        if (!Data.courses.isEmpty()) {
            System.out.println("The classes stored are shown below: ");
            for (Course course : Data.courses) {
                String courseString = course.toString(); // Converts the object into a string using the course toString function.
                System.out.println("Course:" + courseString);
            }
        } else { // Output message if user has not saved any courses.
            System.out.println("There are no classes available to print. ");
        }
    }

}
