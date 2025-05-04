import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

//Model class to represet a student
class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    //constructor
    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    //getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    //setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

     //Returns  readable string representation of the student
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}

//Main GUI class for the Student Management System 
public class StudentManagementSystemGUI1 {
    private ArrayList<Student> studentList = new ArrayList<>();
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> studentJList;
    
    //constructor-starts the GUI	
    public StudentManagementSystemGUI1() {
        initializeMainSystem(); // Directly show the main GUI
    }

     //Initializes the main system window
    private void initializeMainSystem() {
        frame = new JFrame("Student Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //list area to display students
        listModel = new DefaultListModel<>();
        studentJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(studentJList);

	//control buttons
        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton exitButton = new JButton("Exit");

	//panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

	//Add components to fram
        frame.add(new JLabel("Student List:"), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

	//Add actions to buttons
        addButton.addActionListener(e -> addStudent());
        viewButton.addActionListener(e -> viewStudents());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Exiting the application. Goodbye!");
            System.exit(0);
        });

        frame.setVisible(true);
    }
    
    //Method to add a student
    private void addStudent() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField courseField = new JTextField();
        Object[] fields = {
            "ID:", idField,
            "Name:", nameField,
            "Age:", ageField,
            "Course:", courseField
        };
        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String course = courseField.getText();
                Student student = new Student(id, name, age, course);
                studentList.add(student);
                listModel.addElement(student.toString());
                JOptionPane.showMessageDialog(frame, "Student added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Metod to view all students
    private void viewStudents() {
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No students available.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder students = new StringBuilder("--- Student List ---\n");
        for (Student student : studentList) {
            students.append(student).append("\n");
        }
        JOptionPane.showMessageDialog(frame, students.toString(), "Student List", JOptionPane.INFORMATION_MESSAGE);
    }

    //Methods to update a students information
    private void updateStudent() {
        String idInput = JOptionPane.showInputDialog(frame, "Enter Student ID to update:");
        if (idInput == null || idInput.isEmpty()) return;
        try {
            int id = Integer.parseInt(idInput);
            for (Student student : studentList) {
                if (student.getId() == id) {
                    JTextField nameField = new JTextField(student.getName());
                    JTextField ageField = new JTextField(String.valueOf(student.getAge()));
                    JTextField courseField = new JTextField(student.getCourse());
                    Object[] fields = {
                        "Name:", nameField,
                        "Age:", ageField,
                        "Course:", courseField
                    };
                    int option = JOptionPane.showConfirmDialog(frame, fields, "Update Student", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        student.setName(nameField.getText());
                        student.setAge(Integer.parseInt(ageField.getText()));
                        student.setCourse(courseField.getText());
                        refreshStudentList();
                        JOptionPane.showMessageDialog(frame, "Student updated successfully!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Student ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Method to delete a stuent by ID
    private void deleteStudent() {
        String idInput = JOptionPane.showInputDialog(frame, "Enter Student ID to delete:");
        if (idInput == null || idInput.isEmpty()) return;
        try {
            int id = Integer.parseInt(idInput);
            for (Student student : studentList) {
                if (student.getId() == id) {
                    studentList.remove(student);
                    refreshStudentList();
                    JOptionPane.showMessageDialog(frame, "Student deleted successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Student ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Refreshes the displayeed list of students
    private void refreshStudentList() {
        listModel.clear();
        for (Student student : studentList) {
            listModel.addElement(student.toString());
        }
    }

    //Main method
    public static void main(String[] args) {
        new StudentManagementSystemGUI1();
    }
}
