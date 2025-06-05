# CPSC 233 Group Project 

## Authors

Names: 
1) Amy Wiebe 
2) Cassandra Rodberg 
3) Devanshi Iragavarapu

Email IDs: 
1) amy.wiebe1@ucalgary.ca
2) cassandra.rodberg@ucalgary.ca
3) devanshi.iragavar1@ucalgary.ca

## About the Academic Tracker
This is a tool used to navigate through user's courses, and their respective assignments and tests. It allows user
to add/edit/delete courses and tasks, and contains special output options as listed: 
1) Print list of all tasks
2) Print course and grade of task with the lowest grade 
3) Print the grade of asked course.
4) Print your overall GPA
5) Print list of tasks due on a particular day.
6) Print list of all courses currently taken by user.

## How to run

### Running the application (technical aspect):

1) You can run the program via terminal
   Go to this website https://gluonhq.com/products/javafx/ and download the latest JavaFX sdk version suitable one for your OS
   and then use this path to run your file java --module-path "C:\Program Files\Java\javafx-sdk-22\lib" --add-modules javafx.controls,javafx.fxml rw.app.Main

2) You can also run the program using the jar file
   groupprojectgui % java --module-path "C:\Program Files\Java\javafx-sdk-22\lib" --add-modules javafx.controls,javafx.fxml -jar groupprojectgui.jar


### Running the application (functionality aspect):
1) To load/save/delete information from/to a file, there exists a menu bar item called "File" which contains sub-items that allow
user to do so.
2) To get special output options information, there exists a menu bar item called "Output Options" with the list of sub-items as stated:
   a) Print list of all tasks b) Print course and grade of task with the lowest grade c) Print the grade of asked course 
   d) Print your overall GPA e) Print list of tasks due on a particular day f) Print list of all courses currently taken by user.
For items a), b), d), f), user simply has to click on the menu bar item's sub-items accordingly. For item c) User will be asked to
 enter the course name for which they want the grade of. For item e) user will be asked to enter a date to see the tasks due on that date.
3) To add assignments and tests user must enter values to the fields under the section "Assignment" and "Test", and then press the
"Create Assignment" or "Create Test" button. 
4) To change values of a certain task's details, you press on the change assignment button, and then in the secondary input fields
you must enter the course name, as well as the submission name, it then loads the details of the task in the main fields, and allows 
you to change the values.


## Class information
1) Course.java is a parent class to Submission.java and it contains functions related to adding courses, tests, assignments;
as well as validating those tasks. 
2) Submission.java inherits information from Course.java and is a parent to Assignment.java and TestObject.java. It contains
common functions between assignments and tasks, and allows user to add tasks accordingly. 
3) Assignment.java inherits information from Submission.java and it contains all functions specific to managing assignments within 
the application, such as setting and retrieving progress, and formatting assignment details for display or storage.
4) TestObject.java class, extending Submission, specializes in managing test-related data within the application, offering 
functionalities for setting and retrieving attributes like test time and location, and providing methods to format test details for 
display or storage purposes.
5) MenuController.java contains all the main functionalities for running the Graphical User Interface.
6) Menu.java contains remnant functions from object-oriented that were still required for the program even after deleting the menus. 
