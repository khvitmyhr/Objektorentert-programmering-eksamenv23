public class Event {

    private int eventId;
    private String student;
    private String program;
    private String guest;

    public Event(int eventId, String student, String program, String guest) {
        this.eventId = eventId;
        this.student = student;
        this.program = program;
        this.guest = guest;
    }

    public Event(String student, String program) {
        this.student = student;
        this.program = program;
    }

    public String getGuest() {
        return guest;
    }
    public String getStudent() {
        return student;
    }

    public String getProgram() {
        return program;
    }

}
