package ca.ucalgary.groupprojectgui;

/**
 * @author Amy Wiebe, Tutorial 13
 * @author Cassandra Rodberg, Tutorial 17
 * @author Devanshi Iragavarapu, Tutorial 08
 * @date April 15th, 2024
 * @presenting Tutorial 13
 *
 * The TestObject class is a child class of Course, that mainly sets and returns attributes specific to tests.
 */

public class TestObject extends Submission {

    /**
     * Class variables time, and location, used throughout the functions in the class.
     */
    private String time;
    private String location;

    /**
     * Parameterized constructor of TestObject used to create objects for submission type tests.
     * @param course is a String of the course name.
     * @param name is a String of the test name.
     * @param date is a String of the test date.
     * @param weight is a float of the test weighting.
     * @param note is a String of the test notes.
     * @param grade is a float of the test grade.
     * @param time is a String of the test timing.
     * @param location is a String of the test location.
     */
    public TestObject(String course, String name, String date, float weight, String note, float grade, String time, String location) {
        super(course, name, date, weight, note, grade); // Calls super class to pass on the common attributes with our values.
        this.time = time;
        this.location = location;
    }

    /**
     * Takes the test time and sets it to be used in functions involving test data.
     */
    public void setTime(String time) {
        this.time = time;
    }
//
//    /**
//     * Takes the test location and sets it to be used in functions involving test data.
//     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return String of the test time.
     */
    public String returnTime() {
        return this.time; // Returns test time.
    }

    /**
     * @return String of the test location.
     */
    public String returnLocation() {
        return this.location; // Returns test location.
    }

    /**
     * toString for tests, converts and returns the String format of a test.
     * @return String value of the test, and its details.
     */
    @Override
    public String toString() {
        // Passes all values of the attributes of the test.
        String testString;
        String course = this.returnCourseName();
        String name = this.returnName();
        String date = this.returnDate();
        float weight = this.returnWeight();
        String note = this.returnNote();
        float grade = this.returnGrade();
        String time = this.returnTime();
        String location = this.returnLocation();
        // Prints the string format of the test details.
        testString = course + "," + name + "," + date + "," + weight + "," + note + "," + grade + "," + time + "," + location;
        return testString; // Returns the formatted string.
    }

}
