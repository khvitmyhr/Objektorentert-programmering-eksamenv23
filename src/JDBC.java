import java.sql.*;
import java.util.ArrayList;

public class JDBC {

    //Se referanseliste i rapport for denne klassen

    //Hentet fra Bogdan gitrepository
    public JDBC() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    //Hentet fra Bogdan gitrepository
    public static Connection getConnectionUniversity() {
        try {
            return DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/universityDB?useSSL=false",
                            "root",
                            "root"
                    );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static Connection getConnectionEvent() {
        try {
            return DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306/eventDb?useSSL=false",
                            "root",
                            "root"
                    );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


    //Hentet/inspirert fra Bogdan gitrepository

        public ArrayList<Student> retrieveAllStudent(){
        ArrayList<Student> listOfStudent = new ArrayList<>();

        try(Connection con = getConnectionUniversity()) {
            Statement stmt = con.createStatement();

            String selectSql = "SELECT * FROM universityDB.student";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                int programId = resultSet.getInt("program_id");
                String programName = resultSet.getString("program_name");

                Student student = new Student(id, name, programId, programName);
                listOfStudent.add(student);
                getConnectionUniversity().close();
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfStudent;
    }

    public ArrayList<Guest> retrieveAllGuests(String nameSearch){
        ArrayList<Guest> listOfGuests = new ArrayList<>();

        try(Connection con = getConnectionEvent()) {
            Statement stmt = con.createStatement();

            String selectSql = "SELECT * FROM eventDB.attend";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()) {
                int id = resultSet.getInt("attendent_id");
                String name = resultSet.getString("studentName");
                String program = resultSet.getString("program");
                String guestName = resultSet.getString("guest");

                Guest guest = new Guest(guestName, id, program, guestName);
                listOfGuests.add(guest);
                getConnectionUniversity().close();
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfGuests;
    }


    //Denne blir pr nå ikke brukt, bruker kun den under
    public ArrayList<Event> retrieveFromEvent(){
        ArrayList<Event> listOfEvent = new ArrayList<>();

        try(Connection con = getConnectionEvent()) {
            Statement stmt = con.createStatement();

            String selectSql = "SELECT * FROM eventDB.attend";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()) {
                int id = resultSet.getInt("attendent_id");
                String name = resultSet.getString("studentName");
                String program = resultSet.getString("program");
                String guest = resultSet.getString("guest");

                Event event = new Event(id, name, program, guest);
                listOfEvent.add(event);
                getConnectionUniversity().close();
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfEvent;
    }


    public ArrayList<Event> retrieveFromEventDistinct(){
        ArrayList<Event> listOfEvent = new ArrayList<>();

        try(Connection con = getConnectionEvent()) {
            Statement stmt = con.createStatement();

            String selectSql = "SELECT distinct studentName, program FROM eventDB.attend";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()) {
                String name = resultSet.getString("studentName");
                String program = resultSet.getString("program");

                Event event = new Event(name, program);
                listOfEvent.add(event);
                getConnectionUniversity().close();
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfEvent;
    }


    //Hentet/inspirert fra Bogdan gitrepository
    public ArrayList<Event> retrieveEventWhereProgram(String program){
        ArrayList<Event> list = new ArrayList<>();

        try(Connection con = getConnectionEvent()) {
            con.setAutoCommit(false);

            String selectSql = "SELECT * FROM eventDB.attend WHERE program = ?";

            PreparedStatement prep = con.prepareStatement(selectSql);
            prep.setString(1, program);

            ResultSet resultSet = prep.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("attendent_id");
                String studentName = resultSet.getString("studentName");
                program = resultSet.getString("program");
                String guest = resultSet.getString("guest");

                Event event = new Event(id, studentName, program, guest);
                list.add(event);
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return list;
    }

    public ArrayList<Program> selectProgram(String studentName){
        ArrayList<Program> programList = new ArrayList<>();

        try(Connection con = getConnectionUniversity()) {
            con.setAutoCommit(false);

            String selectSql = "SELECT program_name, program_id FROM universityDB.student WHERE name = ?";

            PreparedStatement prep = con.prepareStatement(selectSql);
            prep.setString(1, studentName);

            ResultSet resultSet = prep.executeQuery();
            while(resultSet.next()) {
                int programId = resultSet.getInt("program_id");
                String programName = resultSet.getString("program_name");

                Program program = new Program(programId, programName);
                programList.add(program);
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return programList;
    }


    //Hentet/inspirert fra Bogdan gitrepository
    public ArrayList<Event> retrieveEventWhereStudent(String student){
        ArrayList<Event> listOfStudent = new ArrayList<>();

        try(Connection con = getConnectionEvent()) {
            con.setAutoCommit(false);

            String selectSql = "SELECT * FROM eventDB.attend WHERE studentName = ?";

            PreparedStatement prep = con.prepareStatement(selectSql);
            prep.setString(1, student);

            ResultSet resultSet = prep.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("attendent_id");
                String studentName = resultSet.getString("studentName");
                student = resultSet.getString("program");
                String guest = resultSet.getString("guest");

                Event event = new Event(id, studentName, student, guest);
                listOfStudent.add(event);
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfStudent;
    }


    //Hentet/inspirert fra Marcus gitrepository
        public boolean createEvent(Event event) {

        String query = "INSERT INTO eventDB.attend(studentName, program, guest) " +
                "VALUES(?, ?, ?)";

        try (Connection connection = JDBC.getConnectionEvent()) {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, event.getStudent());
            statement.setString(2, event.getProgram());
            statement.setString(3, event.getGuest());

            statement.execute();
            statement.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Hentet/inspirert fra Marcus gitrepository
        public void deleteEvent(String studentName) {

        String query = "DELETE FROM eventDB.attend WHERE studentName = ?;";

        try (Connection connection = JDBC.getConnectionEvent()) {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, studentName);

            statement.execute();
            statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //Hentet/inspirert fra Bogdan gitrepository
    public ArrayList<CeremonyProgram> retrieveCeremony(String program){
        ArrayList<CeremonyProgram> listOfCeremony = new ArrayList<>();

        try(Connection con = getConnectionEvent()) {
            con.setAutoCommit(false);

            String selectSql = "SELECT * FROM eventDB.ceremony_program WHERE program = ?";

            PreparedStatement prep = con.prepareStatement(selectSql);
            prep.setString(1, program);

            ResultSet resultSet = prep.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("ceremony_id");
                program = resultSet.getString("program");
                String programResponsible = resultSet.getString("program_responsible");
                int duration = resultSet.getInt("duration");
                int programResponsibleSpeach = resultSet.getInt("program_responsible_speech");
                int studentSpeach = resultSet.getInt("student_speech");
                int breakBetweenProgram = resultSet.getInt("break_between_program");

                CeremonyProgram ceremonyProgram = new CeremonyProgram(id, program,
                        programResponsible, duration, programResponsibleSpeach, studentSpeach,
                        breakBetweenProgram);
                listOfCeremony.add(ceremonyProgram);
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return listOfCeremony;
    }

    //påbegynt tanke her som ikke ble ferdig
//    public ArrayList<Participant> retriveAllParticipants(){
//        ArrayList<Participant> participantsArray = new ArrayList<>();
//        ArrayList<Event> eventArray = retrieveFromEvent();
//        ArrayList<Student> studentArray = retrieveAllStudent();
//        ArrayList<Staff> staffArray = retrieveAllStaff();
//        ArrayList<Guest> guestArray = retrieveAllGuests();
//
//        participantsArray.addAll(participantsArray);
//        participantsArray.addAll(studentArray);
//        return participantsArray;
//    }
}