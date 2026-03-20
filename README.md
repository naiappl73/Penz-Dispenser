# 🎓 INT 2220 — Computer Science II Lab Portfolio

**Author:** Anaiah Apple
**Course:** INT 2220 — Computer Science II
**Semester:** Spring 2026

---

## 📋 Table of Contents

- [Overview](#overview)
- [Lab 1 — Binary to Decimal Converter](#lab-1--binary-to-decimal-converter)
- [Lab 2 — Polynomial Addition with Linked Lists](#lab-2--polynomial-addition-with-linked-lists)
- [Lab 3 — Pez Dispenser (Stacks)](#lab-3--pez-dispenser-stacks)
- [Extra Projects](#extra-projects)
- [How to Run](#how-to-run)
- [Technologies Used](#technologies-used)

---

## Overview

This repository contains lab assignments for **INT 2220: Computer Science II**. Projects cover core CS concepts including:

- Binary number systems and base conversion
- Linked lists and data structure manipulation
- Stacks and order-preserving data processing
- String parsing and input validation
- Exception handling and control flow
- Object-Oriented Programming (OOP) principles

Each lab is self-contained and runnable as a standalone Java program.

---

## Lab 1 — Binary to Decimal Converter

📁 `Lab1Final.java` | 📅 February 6, 2026

### What It Does

Takes a **binary string (base-2)** entered by the user and converts it to its **decimal (base-10) integer** equivalent — without using any built-in Java conversion methods.

### How It Works

1. User is prompted to enter a string of `0`s and `1`s
2. Input is **validated** — if it contains anything other than binary digits, an error is shown and the user is re-prompted
3. The binary string is converted using the **positional value algorithm**:
   - Read each bit left to right
   - Multiply the running total by `2`, then add the current bit
4. The original binary string and its decimal result are printed

### Key Concepts

| Concept | Where Used |
|---|---|
| Input validation with `while` loop | `main()` |
| String iteration with `.charAt()` | `processInput()` |
| OOP encapsulation | `Base2_converter` class |
| Resource management | `scanner.close()` |

### Example

```
Enter a string of bits (base 2): 1011
The original string you entered was 1011.
The equivalent integer is 11.
```

---

## Lab 2 — Polynomial Addition with Linked Lists

📁 `Lab2Submission.java` | 📅 February 27, 2026

### What It Does

Takes two polynomials in standard algebraic notation, **adds them together**, and displays the simplified result. Polynomials are stored internally as a **LinkedList of Term objects**.

### How It Works

1. User enters two polynomials as strings (e.g., `3x^4 + 2x^3 - 5x + 7`)
2. Each polynomial is **parsed** into a `LinkedList<Term>` — each Term holds a coefficient and exponent
3. All terms from both polynomials are merged into one list
4. **Like terms** (matching exponents) are combined and simplified
5. The result is printed in clean algebraic format

### Architecture

```
Lab2Submission
├── Term                 → Holds one term: coefficient + exponent
├── Polynomial           → LinkedList of Terms; handles parsing, adding, display
│   ├── readPoly()       → Parses string input into a Polynomial object
│   ├── addPolynomials() → Merges and simplifies two polynomials
│   └── toString()       → Formats result for readable output
└── main()               → Drives user I/O
```

### Parsing Handles

- Terms with explicit exponents: `3x^4`
- Linear terms: `2x` or `x`
- Constant terms: `7`
- Negative terms: `-5x`
- Implicit coefficients of `1` or `-1`

### Key Concepts

| Concept | Where Used |
|---|---|
| `LinkedList<T>` | Storing polynomial terms |
| Nested static classes | `Term` and `Polynomial` |
| String parsing with regex | `readPoly()` |
| Like-term combination algorithm | `addPolynomials()` |
| `@Override toString()` | Polynomial display formatting |

### Example

```
Enter first polynomial: 3x^4 + 2x^3 - 5x + 7
Enter second polynomial: 2x^3 - 5x + 5

First Polynomial: 3x^4 + 2x^3 - 5x + 7
Second Polynomial: 2x^3 - 5x + 5
The sum of the two polynomials is: 3x^4 + 4x^3 - 10x + 12
```

---

## Lab 3 — Pez Dispenser (Stacks)

📁 `Lab3PezDispenser.java` | 📅 March 19, 2026

### What It Does

Simulates a **Pez candy dispenser**. The user loads up to 12 candy colors, and the program removes all **yellow candies** while keeping every other candy in its original order. Uses two stacks to handle the reversal correctly.

### How It Works

1. User is prompted to enter up to 12 candy colors one at a time (or type `done` to stop early)
2. Input is **validated** — every color must be from the allowed list: Green, Yellow, Red, Pink, Orange
3. If any invalid color is found, the entire list is rejected and the user starts over
4. Yellow candies are filtered out using a two-stack approach:
   - Non-yellow candies are pushed onto `tempStack` (this reverses their order)
   - `tempStack` is popped into `reverseStack` (flips the order back to original)
   - `reverseStack` is popped into the final `result` list (correct original order restored)
5. The original input list and the filtered result are both displayed

### Why Two Stacks?

A single stack would reverse the candy order when popping. Using a second stack to reverse the reversal restores the original sequence — a classic two-stack order-preservation pattern.

```
Input:     [green, yellow, red, pink, yellow, orange]
tempStack: [green, red, pink, orange]   ← yellow skipped, order reversed
reverseStack: [orange, pink, red, green] ← flipped again
result:    [green, red, pink, orange]   ← original order restored ✓
```

### Key Concepts

| Concept | Where Used |
|---|---|
| `Stack<T>` push/pop | `tempStack` and `reverseStack` |
| `ArrayList<T>` | Input collection and final result |
| Input validation loop | `allColorsAreValid()` + outer `while` |
| Modular helper methods | `allColorsAreValid()`, `isAllowedColor()` |
| Case-insensitive comparison | `.equalsIgnoreCase()` throughout |

### Example

```
Welcome to the Pez Dispenser!

Enter candy #1: green
Enter candy #2: yellow
Enter candy #3: red
Enter candy #4: pink
Enter candy #5: done

Here's the input list:
     green, yellow, red, pink

Here's the output, after removing the yellow-colored candies:
     green, red, pink
```

---

## Extra Projects

### `Access.java` — Login System with Attempt Limiting
📅 October 10, 2025

A console-based login program that verifies a **password and number combination**. Users get a maximum of **3 attempts** before access is denied. Handles invalid input types gracefully.

**Key concepts:** `try-catch`, `InputMismatchException`, `while` loop with attempt counter, early `return` on success or quit.

---

### `PasswordSecurity.java` — Password Validator
📅 October 18, 2025

Simulates account creation with a **multi-rule password validator**. Throws custom exceptions if the password fails any requirement.

**Rules enforced:**
- Length: 6–15 characters
- No spaces allowed
- Must contain at least one digit
- Must contain at least one letter

**Key concepts:** Custom exception throwing, modular validation methods, `try-catch-finally`, recursive re-prompting on failure.

---

## How to Run

> Requires **Java 11+** installed on your machine.

**Compile any file:**
```bash
javac FileName.java
```

**Run the program:**
```bash
java FileName
```

**Example for Lab 2:**
```bash
javac Lab2Submission.java
java Lab2Submission
```

---

## Technologies Used

| Tool | Purpose |
|---|---|
| Java 11+ | Primary language |
| `java.util.LinkedList` | Polynomial term storage (Lab 2) |
| `java.util.Stack` | Two-stack candy filtering (Lab 3) |
| `java.util.ArrayList` | Input/output candy lists (Lab 3) |
| `java.util.Scanner` | User input across all labs |
| `java.util.InputMismatchException` | Input error handling (Access.java) |

---

> 📚 All work completed by Anaiah Apple for INT 2220 — Computer Science II, Spring 2026.
