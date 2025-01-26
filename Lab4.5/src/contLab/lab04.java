package contLab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Prototype {
    Prototype clone();
    void checkPrototype();
}

    class ConcretePrototype1 implements Prototype {
    public ConcretePrototype1() {
        System.out.println("ConcretePrototype1");
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype1();
    }

    @Override
    public void checkPrototype() {
        System.out.println("Prototype1 has been created");
    }
}


class ConcretePrototype2 implements Prototype {
    public ConcretePrototype2() {
        System.out.println("ConcretePrototype2");
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype2();
    }

    @Override
    public void checkPrototype() {
        System.out.println("Prototype2 has been created");
    }
}

class Client {
    private Prototype prototype;

    public Client() {
        System.out.println("Client");
    }

    public void setPrototype(Prototype p) {
        this.prototype = p;
    }

    public Prototype clone() {
        if (prototype == null) {
            return null;
        }
        return prototype.clone();
    }
}

class Student implements Cloneable {
    private String name;
    private int age;
    private String program;

    public Student(String name, int age, String program) {
        this.name = name;
        this.age = age;
        this.program = program;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public Student clone() {
        try {
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        // Create a table-like format for the student details
        StringBuilder table = new StringBuilder();
        table.append("+------------------+------------------+\n");
        table.append(String.format("| %-16s | %-16s |\n", "Field", "Value"));
        table.append("+------------------+------------------+\n");
        table.append(String.format("| %-16s | %-16s |\n", "Name", name));
        table.append(String.format("| %-16s | %-16d |\n", "Age", age));
        table.append(String.format("| %-16s | %-16s |\n", "Program", program));
        table.append("+------------------+------------------+\n");
        return table.toString();
    }
}


public class lab04 {
    private static List<Student> students = new ArrayList<>();
    private static Student prototypeStudent = new Student("Default Name", 20, "International Program");

    public static void run() {

        Client client = new Client();
        client.setPrototype(new ConcretePrototype1());
        Prototype p1 = client.clone();
        p1.checkPrototype();

        client.setPrototype(new ConcretePrototype2());
        Prototype p2 = client.clone();
        p2.checkPrototype();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    createMultipleStudents(scanner);
                    break;
                case 4:
                    updateStudent(scanner);
                    break;
                case 5:
                    removeStudent(scanner);
                    break;
                case 6:
                    removeAllStudents();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("--- Menu ---");
        System.out.println("1. List students");
        System.out.println("2. Add a new student (use prototype)");
        System.out.println("3. Create multiple students (use prototype)");
        System.out.println("4. Update a student by index");
        System.out.println("5. Remove a student by index");
        System.out.println("6. Remove all students");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.println(i + ": " + students.get(i));
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        Student newStudent = prototypeStudent.clone();
        System.out.print("Enter student name: ");
        newStudent.setName(scanner.nextLine());
        System.out.print("Enter student age: ");
        newStudent.setAge(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter student program: ");
        newStudent.setProgram(scanner.nextLine());
        students.add(newStudent);
        System.out.println("Student added.");
    }

    private static void createMultipleStudents(Scanner scanner) {
        System.out.print("Enter number of students to create: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (int i = 0; i < count; i++) {
            Student newStudent = prototypeStudent.clone();
            newStudent.setName("Student " + (i + 1));
            newStudent.setAge(20);
            newStudent.setProgram("International Program");
            students.add(newStudent);
        }
        System.out.println(count + " students created.");
    }

    private static void updateStudent(Scanner scanner) {
        listStudents();
        System.out.print("Enter index of student to update: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (index >= 0 && index < students.size()) {
            Student student = students.get(index);
            System.out.print("Enter new name: ");
            student.setName(scanner.nextLine());
            System.out.print("Enter new age: ");
            student.setAge(scanner.nextInt());
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new program: ");
            student.setProgram(scanner.nextLine());
            System.out.println("Student updated.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void removeStudent(Scanner scanner) {
        listStudents();
        System.out.print("Enter index of student to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index >= 0 && index < students.size()) {
            students.remove(index);
            System.out.println("Student removed.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void removeAllStudents() {
        students.clear();
        System.out.println("All students removed.");
    }
}