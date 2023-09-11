public class Student {

    private int id;
    private String name;
    private int programId;
    private String programName;

    public Student(int id, String name, int programId, String programName) {
        this.name = name;
        this.id = id;
        this.programId = programId;
        this.programName = programName;
    }

    public Student (String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProgramId() {
        return programId;
    }

    public String getProgramName () {
        return programName;
    }

    public void setName(String name) {
        this.name = name;
    }

}
