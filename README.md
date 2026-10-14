# ONLINE QUIZ GENERATION SYSTEM
**Full-Stack Java Mini Project | Academic Project Report & Documentation Guide**

---

## 📌 PROJECT SUMMARY & NAVIGATION

This documentation is structured into **11 clear, step-by-step sections** specifically designed to help you prepare your **11-page project report** for your teacher, while keeping the repository clean and easy to understand.

| Step | Topic | Report Mapping |
| :---: | :--- | :--- |
| **Step 1** | [Introduction & Problem Definition](#step-1-introduction--problem-definition) | Page 1: Abstract & Problem Statement |
| **Step 2** | [System Objectives & Scope](#step-2-system-objectives--project-scope) | Page 2: Objectives & Scope |
| **Step 3** | [System Requirements & Tech Stack](#step-3-system-requirements--technology-stack) | Page 3: Hardware, Software & Tech Stack |
| **Step 4** | [Core Java & OOP Concepts Applied](#step-4-core-java--oop-concepts-applied) | Page 4: Syllabus Mapping & OOP Details |
| **Step 5** | [System Architecture & 3-Tier MVC Design](#step-5-system-architecture--3-tier-mvc-design) | Page 5: Architecture & Tier Roles |
| **Step 6** | [System Flowcharts & Workflow Diagrams](#step-6-system-flowcharts--workflow-diagrams) | Page 6: System Diagrams & Flowcharts |
| **Step 7** | [Database Design & Schema (MySQL)](#step-7-database-design--mysql-schema) | Page 7: ER Diagram & Table Structures |
| **Step 8** | [Backend Implementation & Key Algorithms](#step-8-backend-implementation--algorithms) | Page 8: Code Structure & Logic Algorithms |
| **Step 9** | [REST API Endpoints & Request-Response](#step-9-rest-api-specifications) | Page 9: API Documentation & Sample JSON |
| **Step 10** | [Frontend UI & User Interaction](#step-10-frontend-ui--user-interaction) | Page 10: Screen Workflows & Features |
| **Step 11** | [Testing, Results & Future Enhancements](#step-11-testing-results--future-scope) | Page 11: Test Cases, Conclusion & Scope |

---

## STEP 1: INTRODUCTION & PROBLEM DEFINITION

### 1.1 Overview
The **Online Quiz Generation System** is an automated web application designed to facilitate real-time testing, question management, and instantaneous scoring. Built using **Spring Boot (Java)** and a modern **HTML/CSS/JavaScript** frontend, it connects to a **MySQL relational database** for reliable, persistent data storage.

### 1.2 Problem Statement
Conventional classroom tests rely on manual paper printing, physical distribution, manual answer matching, and time-consuming score tallying. This causes:
- Heavy administrative overhead for teachers.
- Risk of human error during manual evaluations.
- Delay in providing feedback to students.
- Fixed, repetitive question sequences that make cheating easier.

### 1.3 Proposed Solution
This project eliminates manual overhead by providing:
- **Instant Randomization**: Each quiz request shuffles available questions dynamically using Java Collections.
- **Immediate Automated Evaluation**: Students receive their numerical score immediately upon clicking submit.
- **Centralized Question Bank**: Teachers can add, list, and delete questions dynamically via simple web interfaces.
- **Relational Persistence**: All questions and options are securely saved in MySQL (`quizdb`).

---

## STEP 2: SYSTEM OBJECTIVES & PROJECT SCOPE

### 2.1 Key Objectives
1. **Apply Second-Year Engineering Concepts**: Put OOP principles, Collections, and layered MVC architecture into practice.
2. **Build a RESTful Web API**: Provide clean JSON endpoints for quiz generation, submission, and CRUD operations.
3. **Ensure Zero-Lag Assessment**: Enable client-side single-page asynchronous operations (`fetch` API) without page reloads.
4. **Permanent Data Storage**: Integrate Spring Data JPA with MySQL for automatic table generation and data management.

### 2.2 Scope & Boundaries
- **In Scope**:
  - Multiple-choice questions (4 options per question).
  - Dynamic category-based filtering (e.g., `Java`).
  - Automated scoring on the server side.
  - Adding new MCQs through the teacher portal.
- **Out of Scope (Future Scope)**:
  - User authentication and role-based login (Student vs Admin).
  - Timed countdown tests with auto-submit.
  - Negative marking.

---

## STEP 3: SYSTEM REQUIREMENTS & TECHNOLOGY STACK

### 3.1 Hardware Requirements
- **Processor**: Intel Core i3 / AMD Ryzen 3 or higher.
- **RAM**: Minimum 4 GB (8 GB recommended).
- **Disk Space**: At least 500 MB free space.

### 3.2 Software Requirements
- **Operating System**: Windows 10/11, macOS, or Linux.
- **Java Development Kit (JDK)**: JDK 8 or JDK 17/21.
- **Database Server**: MySQL Server 8.0+ / 9.0 (Running on port `3306`).
- **Build Tool**: Apache Maven (Wrapper included as `mvnw.cmd`).
- **Web Browser**: Chrome, Firefox, Edge, or Safari.

### 3.3 Technology Stack Table
| Component | Technology | Purpose |
| :--- | :--- | :--- |
| **Backend Framework** | Spring Boot 2.7.18 | REST API controllers, dependency injection, embedded Tomcat. |
| **Backend Language** | Java (JDK 8 compatible) | Business logic, entity classes, and Collections algorithms. |
| **ORM / Persistence** | Spring Data JPA / Hibernate | Object-Relational Mapping to eliminate boilerplate SQL. |
| **Database** | MySQL Server | Relational storage for tables and persistent question data. |
| **Frontend UI** | HTML5, CSS3, JavaScript | Single Page Application (SPA) with asynchronous `fetch` calls. |

---

## STEP 4: CORE JAVA & OOP CONCEPTS APPLIED

This section maps classroom theory directly to project code.

### 4.1 Encapsulation
In [Question.java](file:///c:/Users/Niraj%20Yadav/OneDrive/Desktop/quiz2/quiz/src/main/java/com/example/quiz/entity/Question.java):
- All fields (`id`, `questionTitle`, `option1`, `option2`, `option3`, `option4`, `rightAnswer`, `category`) are declared **`private`**.
- Access and mutation are only permitted through **`public` getters and setters**, protecting data integrity.

### 4.2 Abstraction & Interface Inheritance
In [QuestionRepository.java](file:///c:/Users/Niraj%20Yadav/OneDrive/Desktop/quiz2/quiz/src/main/java/com/example/quiz/repository/QuestionRepository.java):
- Extends `JpaRepository<Question, Long>`.
- Internal JDBC connections, transaction handling, and statement preparation are abstracted away.
- Custom query methods are declared cleanly:
  ```java
  List<Question> findByCategory(String category);
  ```

### 4.3 Collections Framework
In [QuizService.java](file:///c:/Users/Niraj%20Yadav/OneDrive/Desktop/quiz2/quiz/src/main/java/com/example/quiz/service/quizservice.java):
- **`List` & `ArrayList`**: Store and manipulate question records in memory.
- **`Collections.shuffle(list)`**: Reorders questions unpredictably before sub-slicing.
- **`Map<Long, String>`**: Represents the student's submission mapping Question IDs to selected answers.

### 4.4 Dependency Injection (IoC)
- Spring’s `@Autowired` injects the repository into the service layer, and the service into the controller, eliminating tight coupling and manual `new` constructor instantiations.

---

## STEP 5: SYSTEM ARCHITECTURE & 3-TIER MVC DESIGN

The system follows a standard **3-Tier Model-View-Controller (MVC)** architectural pattern:

```
+-------------------------------------------------------------------------+
|                       1. PRESENTATION LAYER (VIEW)                      |
|                  index.html (HTML5 + CSS3 + Vanilla JS)                 |
+------------------------------------+------------------------------------+
                                     |
                                     | HTTP Requests (JSON)
                                     v
+-------------------------------------------------------------------------+
|                      2. APPLICATION LAYER (CONTROLLER)                  |
|                 QuizController.java (@RestController)                   |
+------------------------------------+------------------------------------+
                                     |
                                     | Service Invocations
                                     v
+-------------------------------------------------------------------------+
|                        3. BUSINESS LOGIC LAYER                          |
|                   QuizService.java (@Service)                           |
+------------------------------------+------------------------------------+
                                     |
                                     | JPA Repositories
                                     v
+-------------------------------------------------------------------------+
|                     4. DATA PERSISTENCE LAYER (DATABASE)                |
|                    MySQL Database (quizdb -> question table)            |
+-------------------------------------------------------------------------+
```

### Layer Roles:
1. **Presentation Layer**: Sends asynchronous `fetch()` requests and renders dynamic question cards and test scores.
2. **Controller Layer**: Exposes endpoints (`/api/quiz/*`, `/api/questions`), deserializes JSON, and delegates tasks.
3. **Service Layer**: Handles quiz generation (filtering + shuffling) and submission grading logic.
4. **Data Layer**: Hibernate executes auto-generated SQL statements against the MySQL server.

---

## STEP 6: SYSTEM FLOWCHARTS & WORKFLOW DIAGRAMS

### 6.1 Quiz Generation & Submission Flowchart

```
                 +-----------------------+
                 |  Student Selects      |
                 |  Category & Clicks    |
                 |   "Generate Quiz"     |
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 |   GET /api/quiz/      |
                 |       generate        |
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 | Query Database for    |
                 | Questions by Category |
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 | Collections.shuffle() |
                 |  Randomizes Questions |
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 | Slice Subset (Count)  |
                 |   and Return JSON     |
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 | Student Picks Answers |
                 |  & Clicks "Submit"    |
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 | POST /api/quiz/submit |
                 | Payload: {id: answer} |
                 +-----------+-----------+
                             |
                             v
                 +-----------------------+
                 | Server Loops Answers: |
                 | Is answer correct?    |
                 +-----------+-----------+
                     |              |
                YES  |              |  NO
                     v              v
              [Score + 1]       [Ignore]
                     |              |
                     +-------+------+
                             |
                             v
                 +-----------------------+
                 | Return Final Score    |
                 | (e.g., "Score: 4/5")  |
                 +-----------------------+
```

### 6.2 Question Addition Workflow

```
   [Teacher Enters Question Details] 
                  |
                  v
       [Fill Title & 4 Options]
                  |
                  v
       [Specify Correct Answer]
                  |
                  v
          [Click "Save"]
                  |
                  v
    [POST /api/questions (JSON)]
                  |
                  v
   [Hibernate: INSERT INTO question]
                  |
                  v
     [Success Notification Shown]
```

---

## STEP 7: DATABASE DESIGN & MYSQL SCHEMA

### 7.1 Database Details
- **Database Name**: `quizdb`
- **Table Name**: `question`
- **Engine**: InnoDB (MySQL 8.0 / 9.0)
- **Character Set**: `utf8mb4`

### 7.2 Table Structure (`question`)
| Field | Type | Key | Nullable | Description |
| :--- | :--- | :---: | :---: | :--- |
| `id` | `BIGINT` | **PK (AI)** | No | Auto-incremented unique primary key. |
| `category` | `VARCHAR(255)` | - | No | Subject/category tag (e.g., `Java`). |
| `question_title` | `VARCHAR(255)` | - | No | The actual problem statement. |
| `option1` | `VARCHAR(255)` | - | No | Option A. |
| `option2` | `VARCHAR(255)` | - | No | Option B. |
| `option3` | `VARCHAR(255)` | - | No | Option C. |
| `option4` | `VARCHAR(255)` | - | No | Option D. |
| `right_answer` | `VARCHAR(255)` | - | No | Exact text matching the correct choice. |

### 7.3 Default Seeded Dataset
When the application runs for the first time, the service seeds 5 standard questions into MySQL:
1. *"Which OOP concept binds data and methods together?"* -> `Encapsulation`
2. *"Which keyword is used to inherit a class in Java?"* -> `extends`
3. *"Which data structure works on LIFO principle?"* -> `Stack`
4. *"Which collection in Java does not allow duplicate values?"* -> `Set`
5. *"What is the default value of boolean in Java?"* -> `false`

---

## STEP 8: BACKEND IMPLEMENTATION & ALGORITHMS

### 8.1 Randomized Quiz Generation Algorithm
1. **Input**: `category` (String), `count` (int).
2. **Fetch**: If `category == "ALL"`, query `findAll()`; else query `findByCategory(category)`.
3. **Randomize**: Call `Collections.shuffle(candidateList)` (Fisher-Yates shuffle algorithm, $O(n)$ time complexity).
4. **Slice**: Limit the list to `min(count, size)`.
5. **Output**: Return sanitized question subset as a JSON array.

```java
public List<Question> generateQuiz(String category, int count) {
    List<Question> questions = (category == null || category.equalsIgnoreCase("ALL"))
            ? questionRepository.findAll()
            : questionRepository.findByCategory(category);
    Collections.shuffle(questions);
    int limit = Math.min(count, questions.size());
    return new ArrayList<>(questions.subList(0, limit));
}
```

### 8.2 Evaluation Algorithm
1. **Input**: Map of `{ questionId: selectedOption }`.
2. **Initialize**: `score = 0`.
3. **Iterate**: For each entry `(id, answer)` in the submission map:
   - Retrieve question entity by `id`.
   - Check `question.getRightAnswer().trim().equalsIgnoreCase(answer.trim())`.
   - If match is found, increment `score = score + 1`.
4. **Output**: Return final integer `score`.

---

## STEP 9: REST API SPECIFICATIONS

All APIs exchange standard JSON payloads over HTTP.

### 9.1 Summary Table
| Method | Endpoint | Query / Body Params | Returns | Purpose |
| :---: | :--- | :--- | :--- | :--- |
| **GET** | `/api/quiz/generate` | `?category=Java&count=5` | `List<Question>` (JSON) | Generates shuffled quiz questions. |
| **POST** | `/api/quiz/submit` | JSON Map `{ "1": "extends" }` | `Integer` | Computes and returns score. |
| **GET** | `/api/questions` | *None* | `List<Question>` (JSON) | Retrieves all questions in bank. |
| **POST** | `/api/questions` | Single `Question` JSON | Saved `Question` | Adds new MCQ to database. |
| **DELETE**| `/api/questions/{id}`| Path Variable `{id}` | String status | Removes question from bank. |

### 9.2 Sample JSON Payloads

#### Quiz Submission Request:
```json
{
  "1": "Encapsulation",
  "2": "extends",
  "3": "Stack"
}
```

#### Add Question Request:
```json
{
  "questionTitle": "Which package contains the Scanner class?",
  "option1": "java.lang",
  "option2": "java.util",
  "option3": "java.io",
  "option4": "java.net",
  "rightAnswer": "java.util",
  "category": "Java"
}
```

---

## STEP 10: FRONTEND UI & USER INTERACTION

The frontend is implemented in a single lightweight file: [index.html](file:///c:/Users/Niraj%20Yadav/OneDrive/Desktop/quiz2/quiz/src/main/resources/static/index.html).

### 10.1 UI Sections
1. **Header & Navigation Tabs**:
   - Modern tab switcher: **Take Quiz** tab and **Add Question** tab.
2. **Quiz Generation Panel**:
   - Dropdown for category filtering (`Java`, `ALL`).
   - "Generate Quiz" button triggers dynamic AJAX fetch without reloading.
3. **Dynamic Question Cards**:
   - Renders questions with styled custom radio buttons.
4. **Score Modal / Result Card**:
   - Displays real-time score with color indicators: `Your Score: 4 / 5`.
5. **Teacher Input Form**:
   - Clean card form for adding new questions with validation.

---

## STEP 11: TESTING, RESULTS & FUTURE SCOPE

### 11.1 Test Case Matrix
| Test ID | Test Scenario | Input Data | Expected Result | Actual Result | Status |
| :---: | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | App Startup | Launch `QuizApplication` | Connects to MySQL on port 8080 | Connected in ~10s | **PASS** |
| **TC-02** | Seed Data Validation | `SELECT * FROM question;` | 5 pre-loaded questions exist | 5 rows found | **PASS** |
| **TC-03** | Quiz Randomization | Call `/api/quiz/generate` twice | Question order varies | Different orders returned | **PASS** |
| **TC-04** | Scoring Accuracy | Submit 3 correct, 2 wrong | Returns score = 3 | Returns 3 | **PASS** |
| **TC-05** | Question Insertion | Fill and submit web form | New question appears in DB | Question persisted | **PASS** |

### 11.2 Conclusion
The **Online Quiz Generation System** meets all criteria for a successful Second-Year Engineering Mini Project. It demonstrates practical mastery of Object-Oriented Programming, the Java Collections Framework, Spring Boot REST architecture, and relational database persistence with MySQL.

### 11.3 Future Enhancements
- **Timer & Countdown**: Client-side exam timer with automatic submission upon expiry.
- **User Authentication**: Student and teacher logins via Spring Security.
- **Score History**: Storing student attempt history and average score analytics in a dedicated `results` table.
- **PDF Report Generation**: Exporting certificates or result scorecards.

---

## 🚀 HOW TO RUN THE PROJECT LOCALLY

1. **Verify MySQL is Running**:
   - Port: `3306`
   - Database: `quizdb`
2. **Check Configuration**:
   - In [application.properties](file:///c:/Users/Niraj%20Yadav/OneDrive/Desktop/quiz2/quiz/src/main/resources/application.properties), ensure `spring.datasource.password` matches your MySQL password.
3. **Run Application**:
   - Open [QuizApplication.java](file:///c:/Users/Niraj%20Yadav/OneDrive/Desktop/quiz2/quiz/src/main/java/com/example/quiz/QuizApplication.java) and click **Run**, or run:
     ```powershell
     .\mvnw.cmd spring-boot:run
     ```
4. **Open in Browser**:
   - Open `http://localhost:8080` to access the application.
