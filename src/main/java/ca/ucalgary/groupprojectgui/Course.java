package ca.ucalgary.groupprojectgui;

import java.util.ArrayList;
import java.util.Scanner;



public class Course {

    /**
     * Class variables to be used throughout the functions.
     */
    public ArrayList<Submission> submissions = new ArrayList<>(); // Contains all the user's submissions.
    protected String course;

    /**
     * toString function Returns a string representation of the course.
     * @return a string containing the name of the course
     */
    @Override
    public String toString() {
        String courseString = this.course; // Passes the course name value.
        return courseString; // Returns it.
    }

    /**
     * Constructs a new Course object with the given course name.
     * @param course the name of the course
     */
    public Course(String course) {
        this.course = course; // Constructor to pass course value.
    }

    /**
     * Checks if this course is equal to the given course name.
     * @param course the course name to compare
     * @return true if the course names are equal, false otherwise
     */
    public boolean equals(String course){
            if (returnCourseName().equals(course)){
                return true; // Returns true if the specified course name is the same as the passed value of course name.
            }else {
                return false; // Else returns false.
            }
    }

    /**
     * Returns the name of the course.
     * @return the name of the course
     */
    public String returnCourseName() {
        return this.course; // Returns string of the course name.
    }

    /**
     * Acquires the information to create an assignment through running seven functions and equating their results to a variable.
     * Runs those variables through the validAssignment function as parameters which adds the information to the arrayList for assignments in an array.
     * @param course Course object
     * @param name String name of the assignment
     * @param date String date of the assignment
     * @param weight float weight of the assignment
     * @param notes String notes for the assignment
     * @param grade float grade for the assignment
     * @param progress float progress for the assignment
     */
    public void addAssignment(Course course, String name, String date, float weight, String notes, float grade, float progress) {
        if (Menu.validSubmissionName(course, name)) { // Checks if the submissions specified is valid (Course and submission name exists)
            validAssignment(String.valueOf(course), name, date, weight, notes, grade, progress); // Then checks whether the submission is a duplicate or not
        }
    }

    /**
     * Acquires the information to create a test through running eight functions and equating their results to a variable.
     * Runs those variables through the validTest function as parameters which adds the information to the arrayList for tests in an array.
     *@param course Course object
     *@param name String name of the test
     *@param date String date of the test
     *@param weight float weight of the test
     *@param notes String notes for the test
     *@param grade float grade for the test
     *@param time String time for the test
     *@param location String location for the test
     */
    public void addTest(Course course, String name, String date, float weight, String notes, float grade, String time, String location) {
        validTest(String.valueOf(course), name, date, weight, notes, grade, time, location);
    }

    /**
     * validAssignment is called in addAssignment, and adds the assignment to assignments ArrayList
     * if checkExistsAssignments function  which is called, returns false.
     * @param course is a String value, takes the course name.
     * @param name is a String value, takes the assignment name.
     * @param date is a String value, takes the due date for the assignment.
     * @param weight is a float value, takes the assignment weight.
     * @param notes is a String value, takes any notes the user wants to leave about assignment.
     * @param progress is a float value, takes the assignment progress.
     * @param grade is a float value, takes the assignment grade.
     * @return function returns a boolean, true if the assignment doesn't exist and false if it does.
     */
    public boolean validAssignment(String course, String name, String date, float weight, String notes, float grade, float progress) {
        Course objectCourse = Menu.returnCourse(course); // Passes parameter course name to local object of Course.
        if (Menu.validSubmissionName(objectCourse, name)) {
            Assignment assignment = new Assignment(course, name, date, weight, notes, grade, progress);
            objectCourse.submissions.add(assignment); // Adds assignment if the course and submission names are valid.
            return true; // Returns true if valid.
        } else {
            return false; // Else false.
        }
    }

    /**
     * validTest is called in addTest, and adds the test to tests ArrayList
     * if checkExistsTest function  which is called, returns false.
     * @param course is a String value, takes the course name.
     * @param name is a String value, takes the test name.
     * @param date is a String value, takes the date of the test.
     * @param time is a String value, takes the time of the test.
     * @param weight is a float value, takes the test weight.
     * @param notes is a String value, takes any notes the user wants to leave about test.
     * @param location is a String value, takes the location of the test.
     * @param grade is a float value, takes the test grade.
     * @return function returns a boolean, true if the test doesn't exist and false if it does.
     */
    public boolean validTest(String course, String name, String date, float weight, String notes, float grade, String time, String location) {
        Course objectCourse = Menu.returnCourse(course);
        try{
            if (Menu.validSubmissionName(objectCourse, name)) {
                TestObject test = new TestObject(course, name, date, weight, notes, grade, time, location);
                objectCourse.submissions.add(test); // Adds test if the course and submission names are valid.
                return true; // Returns true if the complete test submission is valid.
            }
        }
        catch (NullPointerException e) {
            System.out.println(e); // Error checking if the objectCourse is null.
        }
        return false; // Else false.
    }

    /**
     * Gets grade of specified task and returns it.
     * @return float of the grade for the task.
     */
    public float getGrade(){
        ArrayList<Float> weightedGrades = new ArrayList<>();
        ArrayList<Float> weights = new ArrayList<>();
        if (this.submissions.size() != 0) {
            for (int submissionCounter = 0; submissionCounter < this.submissions.size(); submissionCounter ++) {
                Submission submission = this.submissions.get(submissionCounter);
                float weight = submission.returnWeight(); // Gets weighting of assignment.
                    float grade = submission.returnGrade();
                    float weightedGrade = (weight * grade);
                    weightedGrades.add(weightedGrade); // Adds the calculated weighted grade to array of weighted grades.
                    weights.add(weight); // Adds weight to array of weights.

            }
        }
        float sumWeightedGrades = 0;
        if (weightedGrades.size() != 0) { // Calculates the sum of weighted grades.
            for (int gradeCounter = 0 ; gradeCounter < weightedGrades.size() ; gradeCounter ++) {
                Float tempIndex = weightedGrades.get(gradeCounter);
                float primTempIndex = tempIndex.floatValue();
                sumWeightedGrades = sumWeightedGrades + primTempIndex;
            }
        }
        float sumWeights = 0;
        if (weights.size() != 0) { // Calculates the sum of weights.
            for (int weightCounter = 0 ; weightCounter < weights.size() ; weightCounter ++) {
                Float tempIndex = weights.get(weightCounter);
                float primTempIndex = tempIndex.floatValue();
                sumWeights = sumWeights + primTempIndex;
            }
        }
        float finalGrade = 0;
        if (sumWeightedGrades != 0 || sumWeights != 0) {
            finalGrade = sumWeightedGrades / sumWeights; // Calculates final grade.
        }
        return finalGrade;
    }
}
