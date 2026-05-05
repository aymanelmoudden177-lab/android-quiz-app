package com.Ayman.quizapp;

import java.util.ArrayList;

public class QuizData {

    // ── Software Engineering – 10 questions ──────────────────────────────────

    public static ArrayList<Question> getSoftwareEngineeringQuiz() {
        ArrayList<Question> SoftwareEngineeringQuiz = new ArrayList<>();

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "What does SDLC stand for?",
            "System Design and Life Cycle",
            "Software Development Life Cycle",
            "Software Design and Logic Cycle",
            "System Development Language Code",
            "B",
            "SDLC stands for Software Development Life Cycle - a structured process for planning, creating, testing, and delivering software."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "Which of the following is an Agile framework?",
            "Waterfall",
            "Scrum",
            "COBOL",
            "UML",
            "B",
            "Scrum is an Agile framework for developing and delivering complex products through iterative sprints."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "What is the primary purpose of a Use Case diagram?",
            "Database schema design",
            "Network topology mapping",
            "Showing system functionality from a user perspective",
            "Documenting source code",
            "C",
            "Use Case diagrams capture system functionality from the perspective of external actors (users or other systems)."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "Which testing type verifies individual components in isolation?",
            "Integration Testing",
            "System Testing",
            "Unit Testing",
            "Acceptance Testing",
            "C",
            "Unit Testing tests the smallest units of code (methods/classes) independently of other components."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "What does OOP stand for?",
            "Object-Oriented Programming",
            "Open-Output Protocol",
            "Operational Output Processing",
            "Object-Order Paradigm",
            "A",
            "OOP - Object-Oriented Programming - organises software around objects that combine data and behaviour."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "Which SOLID principle says a class should have only one reason to change?",
            "Open/Closed Principle",
            "Single Responsibility Principle",
            "Liskov Substitution Principle",
            "Interface Segregation Principle",
            "B",
            "The Single Responsibility Principle (SRP) keeps each class focused on a single concern."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "What is a software design pattern?",
            "A programming language feature",
            "A reusable solution to a common design problem",
            "A type of database schema",
            "A network protocol",
            "B",
            "Design patterns are proven, reusable templates for solving recurring software-design problems."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "What is version control primarily used for?",
            "Managing server CPU load",
            "Compiling source code faster",
            "Tracking and managing changes to source code over time",
            "Testing software performance",
            "C",
            "Version control (e.g. Git) records every change to code, enabling collaboration and rollback."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "In the MVC pattern, what does the V stand for?",
            "Variable",
            "Value",
            "View",
            "Verify",
            "C",
            "MVC stands for Model-View-Controller. The View is responsible for rendering the user interface."
        ));

        SoftwareEngineeringQuiz.add(new Question(
            "Software Engineering",
            "What is code refactoring?",
            "Rewriting the entire codebase from scratch",
            "Adding new features to existing code",
            "Restructuring code internally without changing its external behaviour",
            "Testing code for bugs",
            "C",
            "Refactoring improves the internal structure of code without altering what it does."
        ));

        return SoftwareEngineeringQuiz;
    }

    // ── Data Structures – 10 questions ──────────────────────────────────────

    public static ArrayList<Question> getDataStructureQuiz() {
        ArrayList<Question> DataStructureQuiz = new ArrayList<>();

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "What is the average-case time complexity of searching in a balanced Binary Search Tree?",
            "O(n)",
            "O(log n)",
            "O(n squared)",
            "O(1)",
            "B",
            "Each comparison in a balanced BST halves the search space, giving O(log n) average-case performance."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "Which data structure follows the LIFO (Last In, First Out) principle?",
            "Queue",
            "Stack",
            "Linked List",
            "Tree",
            "B",
            "A Stack follows LIFO - the most recently pushed element is the first one popped."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "What is the worst-case time complexity of Bubble Sort?",
            "O(n log n)",
            "O(log n)",
            "O(n)",
            "O(n squared)",
            "D",
            "Bubble Sort compares adjacent elements with nested loops, resulting in O(n squared) worst-case complexity."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "Which data structure follows the FIFO (First In, First Out) principle?",
            "Stack",
            "Queue",
            "Tree",
            "Graph",
            "B",
            "A Queue follows FIFO - the first element enqueued is the first one dequeued."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "What does each node in a singly linked list contain?",
            "Only data",
            "Data and a pointer to the next node",
            "Only a pointer",
            "A key-value pair",
            "B",
            "Each node in a singly linked list stores a data value and a reference (pointer) to the next node."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "Which tree traversal visits the root node FIRST, then left subtree, then right subtree?",
            "In-order",
            "Post-order",
            "Pre-order",
            "Level-order",
            "C",
            "Pre-order traversal: Root then Left subtree then Right subtree."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "What is the time complexity of accessing an element by index in an array?",
            "O(n)",
            "O(log n)",
            "O(n squared)",
            "O(1)",
            "D",
            "Array elements are stored at contiguous memory addresses, so index-based access is O(1) constant time."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "How does a Hash Table primarily store data?",
            "As a sorted list",
            "As key-value pairs",
            "As nodes and edges",
            "As a stack of frames",
            "B",
            "A Hash Table maps keys to values using a hash function, enabling average O(1) insertion and lookup."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "Which sorting algorithm generally offers the best average-case performance in practice?",
            "Bubble Sort",
            "Selection Sort",
            "Quick Sort",
            "Insertion Sort",
            "C",
            "Quick Sort has O(n log n) average complexity and benefits from cache efficiency."
        ));

        DataStructureQuiz.add(new Question(
            "Data Structures",
            "What are the two fundamental components of a Graph?",
            "Only vertices (nodes)",
            "Only edges",
            "Vertices (nodes) and edges",
            "Keys and values",
            "C",
            "A graph consists of vertices (nodes) connected by edges, used to model relationships and networks."
        ));

        return DataStructureQuiz;
    }
}
