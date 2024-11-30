package ca.ucalgary.groupprojectgui;

/**
 * @author Amy Wiebe, Tutorial 13
 * @author Cassandra Rodberg, Tutorial 17
 * @author Devanshi Iragavarapu, Tutorial 08
 * @date April 15th, 2024
 * @presenting Tutorial 13
 *
 * The submission class is an abstract child class of Course, that mainly sets and returns common attributes of tests and assignments.
 */

public abstract class Submission extends Course {

    /**
     * List of class variables (common between tests and assignments) used in the abstract functions.
     */
    private String name;
    private String date;
    private float weight;
    private String note;
    private float grade;

    /**
     * Submission parameterized constructor, that calls the super constructor, and passes values from the parameters to the class variables of specified object.
     * @param course passes course name
     * @param name passes submission name
     * @param date  passes submission date
     * @param weight passes submission weight
     * @param note passes submission notes
     * @param grade passes submission grade
     */
    protected Submission(String course, String name, String date, float weight, String note, float grade) {
        super(course); // Calls super constructor to pass the course name of the submission.
        this.name = name; // Passes all values of the common attributes of tests and assignments.
        this.date = date;
        this.weight = weight;
        this.note = note;
        this.grade = grade;
    }

    /**
     * Checks if course is supposed to be replacing another course, and if so replaces it.
     * Also adds course to classes arrayList if it does not already exist.
     */
    public void setCourse(String course) {
        this.course = course; // Gets and sets current course name from Data.getCourse().
    }

    /**
     * Changes the name of assignment or test if there is an index for assignment or test
     */
    public void setName(String name) {
        this.name = name; // Gets and sets current submission name from Course.getName().
    }

    /**
     * Takes the date recorded and replaces it in the assignments or tests arrayList.
     */
    public void setDate(String date) {
        this.date = date; // Gets and sets current submission date from Course.getDate().
    }

    /**
     * Takes the new weight of an assignment or test and adds it into the proper arrayList based on the index and GLOBAL SUBMISSION.
     */
    public void setWeight(float weight) {
        this.weight = weight; // Gets and sets current submission weight from Course.getWeight().
    }

    /**
     * Takes the notes string and adds it to the assignment or test arrayList based on its index.
     */
    public void setNote(String note) {
        this.note = note; // Gets and sets current submission note from Course.getNote().
    }

    /**
     * Takes the float of grade and indexes it into the assignments or tests arrayList based on the global index.
     */
    public void setGrade(float grade) {
        this.grade = grade; // Gets and sets current submission grade from Course.getSubmissionGrade().
    }

    /**
     * returnName function returns the current submission name.
     * @return a String: this.name
     */
    public String returnName() {
        return this.name;  // Returns the current submission name.
    }

    /**
     * returnDate function returns the current submission date.
     *
     * @return a String of the current date.
     */
    public String returnDate(){
        return this.date; // Returns the current submission date.
    }

    /**
     * returnDate function returns the current submission weight.
     * @return a float of the current weight.
     */
    public float returnWeight(){
        return this.weight; // Returns the current submission weight.
    }

    /**
     * returnDate function returns the current submission grade.
     * @return a float of the current grade.
     */
    public float returnGrade(){
        return this.grade; // Returns the current submission grade.
    }

    /**
     * returnDate function returns the current submission note.
     * @return a String of the current note.
     */
    public String returnNote(){
        return this.note; // Returns the current submission note.
    }

    /**
     * @param submissionName the course name to compare
     * @return boolean true if the name returned equals the submissionName parameter.
     * Is used in multiple functions for error checking, and ensuring the data entered and saved is all valid, and not duplicated.
     */
    @Override
    public boolean equals(String submissionName){
        if (this.returnName().equals( submissionName )){ // Checks whether this.returnName is the same as the value passed through the parameter.
            return true; // Returns true if it is the same.
        }else {
            return false; // Else returns false.
        }

    }
}
