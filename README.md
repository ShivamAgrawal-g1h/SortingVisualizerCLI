# Sorting Visualizer CLI (Java)

## 📌 Overview
A Java-based Command Line Interface (CLI) application that implements and visualizes multiple sorting algorithms. The project provides step-by-step execution tracing, recursion flow visualization, and performance analysis using execution time measurement.

---

## 🚀 Features
- Implementation of **7 sorting algorithms**:
    - Bubble Sort
    - Selection Sort (Min & Max variants)
    - Insertion Sort (Swap & Shift variants)
    - Merge Sort (Naive, Indices-based, Bottom-Up)
    - Quick Sort (Naive, Lomuto, Hoare)
    - Counting Sort (supports negative values)
    - Radix Sort

- **Step-by-step visualization**
    - Displays array state after each significant step
    - Tracks progression of sorting

- **Recursion visualization**
    - Shows entering and exiting recursion levels (DFS flow, i.e. Depth First Search)

- **Performance tracking**
    - Execution time using `System.nanoTime()`
    - Separate mode for:
        - Actual algorithm runtime
        - Runtime including visualization overhead

- **Flexible input handling**
    - Manual input
    - Random array generation

- **Interactive CLI system**
    - Dynamic algorithm selection
    - Retry options:
        - Same algorithm / different input
        - Different algorithm / same input

- **Time & Space Complexity Display**
    - Best, Average, Worst case analysis
    - Algorithm-specific complexity output

---

## 🧠 Concepts Used
- Data Structures & Algorithms
- Recursion (DFS-based execution)
- Time & Space Complexity Analysis
- Object-Oriented Programming (OOP)
- Java CLI application design

---

## 🛠️ How to Run

1. Compile the code:
```bash
javac SortingVisualizerCLI.java
```
2. Run the program.