public class CeremonyProgram {

    private int ceremonyId;
    private String program;
    private String programResponsible;
    private int duration;
    private int programResponsibleSpeach;
    private int studentSpeech;
    private int breakBetweenProgram;

    public CeremonyProgram(int ceremonyId, String program, String programResponsible,
                           int duration, int programResponsibleSpeach, int studentSpeech,
                           int breakBetweenProgram) {
        this.ceremonyId = ceremonyId;
        this.program = program;
        this.programResponsible = programResponsible;
        this.duration = duration;
        this.programResponsibleSpeach = programResponsibleSpeach;
        this.studentSpeech = studentSpeech;
        this.breakBetweenProgram = breakBetweenProgram;
    }

    public String getProgram() {
        return program;
    }

    public String getProgramResponsible() {
        return programResponsible;
    }

    public int getDuration() {
        return duration;
    }

    public int getProgramResponsibleSpeach() {
        return programResponsibleSpeach;
    }

    public int getStudentSpeech() {
        return studentSpeech;
    }

    public int getBreakBetweenProgram() {
        return breakBetweenProgram;
    }
}
