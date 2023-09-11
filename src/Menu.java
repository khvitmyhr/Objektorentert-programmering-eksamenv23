import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    JDBC jdbc = new JDBC();
    String studentName = "";
    Student student = new Student(studentName);
    ArrayList<Student> students = jdbc.retrieveAllStudent();

    public void startProgram() {

        while (!studentExists()) {

            System.out.println("Welcome. Sign in to use the program, if you are registered as a student: ");
            studentName = scanner.nextLine();
            student.setName(studentName);

        }
        System.out.println("Welcome: " + studentName + "! What do you want to do?");
        mainMenu();
    }

    private void mainMenu() {

        printMenu();

        String userInput;
        boolean running = true;

        while (running) {
            userInput = scanner.nextLine();

            switch (userInput) {

                case "1": {

                    if (studentAlreadyAttend()) {
                        System.out.println("You cannot participate to more than one event!");
                    } else {
                        createEvent();
                    }
                    printMenu();
                    break;
                }
                case "2": {
                    System.out.println("This is all students participateting and the program their in");

                    ArrayList<Event> list = jdbc.retrieveFromEventDistinct();

                    for (Event participants : list) {
                        System.out.println(participants.getStudent());
                        System.out.println("  Program: " + participants.getProgram());
                    }
                    printMenu();
                    break;
                }
                case "3": {
                    System.out.println("Which name of program do you want to see the ceremony" +
                            "program for?");
                    printPrograms();

                    String programRequest = scanner.nextLine();

                    ArrayList<Program> programsListSertainStudent = jdbc.selectProgram(studentName);

                    printCeremonyProgram(programRequest);

                    for (Program program : programsListSertainStudent) {

                        if (!programRequest.equalsIgnoreCase(program.getProgramName())) {

                            System.out.println(studentName + " you are a part of " + program.getProgramName() +
                                    ", du you want to see that program? Type yes/no");

                            String userAnswer = scanner.nextLine();

                            if (userAnswer.equalsIgnoreCase("yes")) {
                                printCeremonyProgram(program.getProgramName());
                            }
                        }
                    }

                    printMenu();
                    break;
                }
                case "4": {
                    System.out.println("Which program do you want to see participants from?");
                    printPrograms();

                    String programQuery = scanner.nextLine();

                    ArrayList<Event> list = jdbc.retrieveEventWhereProgram(programQuery);

                    if (list.isEmpty()) {
                        System.out.println("No student from the program " + programQuery + " is registered for ceremony");
                    } else {
                        System.out.println("Students attending " + programQuery + " is: ");
                        for (Event participants : list) {
                            System.out.println(participants.getStudent());
                        }
                    }
                    printMenu();
                    break;
                }
                case "5": {
                    System.out.println("Which person are you looking for?");
                    String studentSearch = scanner.nextLine();

                    ArrayList<Event> students = jdbc.retrieveEventWhereStudent(studentSearch);
                    ArrayList<Guest> guests = jdbc.retrieveAllGuests(studentSearch);

                    if (students.isEmpty()) {
                        System.out.println("No student with name " + studentSearch + " is registered for ceremony!");
                    } else {
                        for (Event event1 : students) {
                            System.out.println(event1.getStudent() + " is a student participating from the program "
                                    + event1.getProgram());
                        }
                    }

                    if (guests.isEmpty()) {
                        System.out.println("No guests with name " + studentSearch + " is registered for ceremony!");
                    } else {
                        for (Guest guest : guests) {
                            if (studentSearch.equalsIgnoreCase(guest.getName()))
                                System.out.println(guest.getName() + " is participating as guest!");
                        }
                    }
                    printMenu();
                    break;
                }
                case "6": {
                    if (!studentAlreadyAttend()) {
                        System.out.println("You are not registered for an event yet!");
                    } else {
                        System.out.println("1: delete you registration");
                        System.out.println("2: edit the amount of guests");

                        String userChoice = scanner.nextLine();

                        if (userChoice.equals("1")) {
                            jdbc.deleteEvent(studentName);
                            System.out.println("Your event is now deleted!");
                        } else {
                            jdbc.deleteEvent(studentName);
                            createEvent();
                            System.out.println("Your event is now updated!");
                        }
                    }
                    printMenu();
                    break;
                }
                case "7": {
                    System.out.println("Thank you for now!");
                    running = false;
                    break;
                }
                default: {
                    System.out.println("Type a valid number");
                    printMenu();
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("1: Register for an event");
        System.out.println("2: See all students that are participating");
        System.out.println("3: Look at the ceremony program");
        System.out.println("4: Search for participants on a particular program");
        System.out.println("5: Search for a particular student or guest");
        System.out.println("6: edit registration");
        System.out.println("7: exit/change user");
    }

    private boolean studentExists() {

        for (Student student : students) {
            if (studentName.equalsIgnoreCase(student.getName())) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<String> addGuests(int amount) {
        ArrayList<String> guests = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            System.out.println("Add name of guest: ");
            String guest = scanner.nextLine();
            guests.add(guest);
        }
        if (amount == 0) {
            guests.add("null");
        }
        return guests;
    }

    public void createEvent() {

        System.out.println("Great! How many guests would you like to bring? Maximum 4");
        int amount = Integer.parseInt(scanner.nextLine());
        if (amount < 5) {
            ArrayList<String> nameOfGuests = addGuests(amount);
            ArrayList<Student> studentInfo = jdbc.retrieveAllStudent();

            for (Student student : studentInfo) {
                if (studentName.equalsIgnoreCase(student.getName())) {
                    String program = student.getProgramName();
                    for (String guest : nameOfGuests) {
                        Event event = new Event(0, studentName, program, guest);
                        jdbc.createEvent(event);
                    }
                }
            }
            System.out.println("See you there!");
        } else {
            System.out.println("Maximum 4 guests!");
        }
    }

    public boolean studentAlreadyAttend() {

        ArrayList<Event> event = jdbc.retrieveFromEvent();

        for (Event e : event) {
            if (studentName.equals(e.getStudent())) {
                return true;
            }
        }
        return false;
    }

    public void printPrograms() {
        System.out.println("you can choose between: ");
        System.out.println("Programmering");
        System.out.println("Cyber security");
        System.out.println("Interaktivt design");
        System.out.println("Databaser");
    }

    public void printCeremonyProgram(String programRequest) {

        ArrayList<CeremonyProgram> programList = jdbc.retrieveCeremony(programRequest);
        if (!programList.isEmpty()) {
            for (CeremonyProgram ceremonyProgram1 : programList) {
                if (programRequest.equalsIgnoreCase(ceremonyProgram1.getProgram())) {

                    System.out.println("For the program: " + ceremonyProgram1.getProgram());
                    System.out.println("with the program responsible: " + ceremonyProgram1.getProgramResponsible());
                    System.out.println("Total duration will be " + ceremonyProgram1.getDuration() + " hours");
                    System.out.println("Program responsible speach: " + ceremonyProgram1.getProgramResponsibleSpeach() + " minutes");
                    System.out.println("Student speaches " + ceremonyProgram1.getStudentSpeech() + " minutes pr. student");
                    System.out.println("There will be a " + ceremonyProgram1.getBreakBetweenProgram() + " minutes long break between each program");
                    System.out.println("--");
                }
            }
        }
    }
}



