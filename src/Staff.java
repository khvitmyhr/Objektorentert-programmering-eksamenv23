public class Staff extends Participant {
    private String teacher;
    private String programResponsible;

    public Staff(String name, int id, String teacher, String programResponsible) {
        super(name, id);
        this.id = id;
        this.teacher = teacher;
        this.programResponsible = programResponsible;
    }
}
