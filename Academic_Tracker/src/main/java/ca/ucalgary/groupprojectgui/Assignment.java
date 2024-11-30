package ca.ucalgary.groupprojectgui;

/**
 * @author Amy Wiebe, Tutorial 13
 * @author Cassandra Rodberg, Tutorial 17
 * @author Devanshi Iragavarapu, Tutorial 08
 * @date April 15th, 2024
 * @presenting Tutorial 13
 */

public class Assignment extends Submission {

    /**
     * progress is a float used to track what percentage of the assignment you are done.
     */
    private float progress;

    /**
     * Constructs the assignment using the parameters below, and adds the traits from submission using the operation super.
     *
     * @param course is the name of the course the assignment is for and kept in.
     * @param name is the name of the assignment; can be used to find the assignment along with the course name.
     * @param date is the date the assignment is due to be handed in.
     * @param weight is the amount the assignment is worth in its class; used in getGrade.
     * @param note is any notes that you have to remember for the assignment.
     * @param grade is the grade that you have obtained for the assignment, and is 0 if it has not yet been submitted.
     * @param progress is the amount of the assignment that has been completed.
     */
    public Assignment(String course, String name, String date, float weight, String note, float grade, float progress) {
        super(course, name, date, weight, note, grade); // Calls super class, to pass the common attributes, with our values.
        this.progress = progress; // Passes assignment progress.
    }

    /**
     * Sets the progress of the assignment using the getProgress function.
     */
    public void setProgress(float progress) {
        this.progress = progress; // Gets and sets progress from Course.getProgress().
    }

    /**
     * @return float of progress of the assignment.
     */
    public float returnProgress(){
        return this.progress; // Returns assignment progress.
    }

    /**
     * Overrides the default toString function to format the assignment information as a collective string with commas separating them.
     * @return the string of the assignment, with all its traits, properly formatted with commas in between the strings.
     */
    @Override
    public String toString() {
        // Passes returned assignment details.
        String assignmentString;
        // Uses the return functions to get all the assignment information for string conversion.
        String course = this.returnCourseName();
        String name = this.returnName();
        String date = this.returnDate();
        float weight = this.returnWeight();
        String note = this.returnNote();
        float grade = this.returnGrade();

        // Combines and formats the assignment and its details into a string.
        assignmentString = course + "," + name + "," + date + "," + weight + "," + note + "," + grade + "," + progress;
        return assignmentString; // Returns the formatted string.
    }
}
