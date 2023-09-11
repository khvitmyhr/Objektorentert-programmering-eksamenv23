public class Guest extends Participant{

    private String attendingProgram;
    private String guestOfStudent;

    public Guest(String name, int id, String attendingProgram, String guestOfStudent) {
        super(name, id);
        this.attendingProgram = attendingProgram;
        this.guestOfStudent = guestOfStudent;
    }

}
