# ForDocs - Community Building App for Doctors

ForDocs is a community-building platform tailored for healthcare professionals, inspired by Reddit. It allows doctors to engage in discussions, share knowledge, and collaborate within dedicated medical communities. Built using **Spring Boot** with **MVC architecture**, ForDocs leverages **JSP** for the frontend and **PostgreSQL** for data storage.

---

## 🚀 Features

### 👥 User Features
#### **Registration and Login**
- Secure user authentication and registration using **JWT tokens**.
- Session management via **HttpSession** for logged-in users.

#### **Profile Management**
- View and update user profiles, including their posts and comments.

#### **Community Features**
- Create, join, and leave communities.
- View community members.
- Engage in discussions within communities.

#### **Post & Comment System**
- Create, update, and delete posts.
- Add, update, and delete comments on posts.
- Upvote or downvote posts.

### 🔧 Admin Features
- Manage **users, posts, and communities**.

### 📌 Pagination & Sorting
- Paginated views for posts and communities for enhanced user experience.

### ⚠️ Error Handling
- Custom error handling for **invalid operations** and **resource-not-found** scenarios.

---

## 🛠️ Technology Stack

### **Backend**
- **Spring Boot**
  - RESTful API implementation
  - Dependency injection for modularity
  - Security configurations via **Spring Security**
- **Hibernate**
  - ORM framework for PostgreSQL database interaction

### **Frontend**
- **JSP (Java Server Pages)**
  - Dynamic rendering of views

### **Database**
- **PostgreSQL**
  - Relational database for storing **users, posts, communities, and comments**

### **Security**
- **JWT**
  - Token-based authentication for secure access control

---

## Folder Structure

```plaintext
├── FinalProject
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── nb-configuration.xml
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── csye6220
│   │   │   │           └── finalProject
│   │   │   │               ├── FinalProjectApplication.java
│   │   │   │               ├── config
│   │   │   │               │   └── SecurityConfiguration.java
│   │   │   │               ├── controller
│   │   │   │               │   ├── AuthController.java
│   │   │   │               │   ├── CommentController.java
│   │   │   │               │   ├── CommunityController.java
│   │   │   │               │   ├── HomeController.java
│   │   │   │               │   ├── PostController.java
│   │   │   │               │   ├── UserController.java
│   │   │   │               │   └── VoteController.java
│   │   │   │               ├── dao
│   │   │   │               │   ├── CommentDAO.java
│   │   │   │               │   ├── CommunityDAO.java
│   │   │   │               │   ├── DAO.java
│   │   │   │               │   ├── PostDAO.java
│   │   │   │               │   ├── UserDAO.java
│   │   │   │               │   ├── VoteDAO.java
│   │   │   │               │   └── impl
│   │   │   │               │       ├── CommentDAOImpl.java
│   │   │   │               │       ├── CommunityDAOImpl.java
│   │   │   │               │       ├── PostDAOImpl.java
│   │   │   │               │       ├── UserDAOImpl.java
│   │   │   │               │       └── VoteDAOImpl.java
│   │   │   │               ├── dto
│   │   │   │               │   ├── AuthResponse.java
│   │   │   │               │   ├── CommentDto.java
│   │   │   │               │   ├── LoginRequest.java
│   │   │   │               │   ├── PostDto.java
│   │   │   │               │   ├── RegisterRequest.java
│   │   │   │               │   └── VoteDto.java
│   │   │   │               ├── exception
│   │   │   │               │   ├── GlobalExceptionHandler.java
│   │   │   │               │   ├── ResourceNotFoundException.java
│   │   │   │               │   └── ValidationException.java
│   │   │   │               ├── model
│   │   │   │               │   ├── Comment.java
│   │   │   │               │   ├── Community.java
│   │   │   │               │   ├── Post.java
│   │   │   │               │   ├── User.java
│   │   │   │               │   ├── Vote.java
│   │   │   │               │   └── VoteType.java
│   │   │   │               ├── security
│   │   │   │               │   ├── JWTAuthenticationFilter.java
│   │   │   │               │   ├── JwtAuthenticationEntryPoint.java
│   │   │   │               │   ├── JwtProvider.java
│   │   │   │               │   └── SecurityConstants.java
│   │   │   │               └── service
│   │   │   │                   ├── AuthService.java
│   │   │   │                   ├── CommentService.java
│   │   │   │                   ├── CommunityService.java
│   │   │   │                   ├── PostService.java
│   │   │   │                   ├── UserService.java
│   │   │   │                   ├── VoteService.java
│   │   │   │                   └── impl
│   │   │   │                       ├── AuthServiceImpl.java
│   │   │   │                       ├── CommentServiceImpl.java
│   │   │   │                       ├── CommunityServiceImpl.java
│   │   │   │                       ├── PostServiceImpl.java
│   │   │   │                       ├── UserDetailsServiceImpl.java
│   │   │   │                       ├── UserServiceImpl.java
│   │   │   │                       └── VoteServiceImpl.java
│   │   │   ├── resources
│   │   │   │   ├── application.properties
│   │   │   │   ├── hibernate.cfg.xml
│   │   │   │   └── static
│   │   │   └── webapp
│   │   │       └── WEB-INF
│   │   │           └── views
│   │   │               ├── commList.jsp
│   │   │               ├── community.jsp
│   │   │               ├── createCommunity.jsp
│   │   │               ├── createPost.jsp
│   │   │               ├── fordocs.png
│   │   │               ├── index.jsp
│   │   │               ├── login.jsp
│   │   │               ├── loginSuccess.jsp
│   │   │               ├── membersOfComm.jsp
│   │   │               ├── navbar.jsp
│   │   │               ├── navbarStyle.css
│   │   │               ├── postComponent.jsp
│   │   │               ├── postSuccess.jsp
│   │   │               ├── posts.jsp
│   │   │               ├── postsbycomm.jsp
│   │   │               ├── profile.jsp
│   │   │               ├── registration.jsp
│   │   │               ├── registrationSuccess.jsp
│   │   │               ├── updatePassword.jsp
│   │   │               ├── updateProfile.jsp
│   │   │               ├── user-list.jsp
│   │   │               └── userPosts.jsp
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── csye6220
│   │                   └── finalProject
│   │                       └── FinalProjectApplicationTests.java
│   └── target
│       ├── classes
│       │   ├── application.properties
│       │   ├── com
│       │   │   └── csye6220
│       │   │       └── finalProject
│       │   │           ├── FinalProjectApplication.class
│       │   │           ├── config
│       │   │           │   └── SecurityConfiguration.class
│       │   │           ├── controller
│       │   │           │   ├── AuthController.class
│       │   │           │   ├── CommentController.class
│       │   │           │   ├── CommunityController.class
│       │   │           │   ├── HomeController.class
│       │   │           │   ├── PostController.class
│       │   │           │   ├── UserController.class
│       │   │           │   └── VoteController.class
│       │   │           ├── dao
│       │   │           │   ├── CommentDAO.class
│       │   │           │   ├── CommunityDAO.class
│       │   │           │   ├── DAO.class
│       │   │           │   ├── PostDAO.class
│       │   │           │   ├── UserDAO.class
│       │   │           │   ├── VoteDAO.class
│       │   │           │   └── impl
│       │   │           │       ├── CommentDAOImpl.class
│       │   │           │       ├── CommunityDAOImpl.class
│       │   │           │       ├── PostDAOImpl.class
│       │   │           │       ├── UserDAOImpl.class
│       │   │           │       └── VoteDAOImpl.class
│       │   │           ├── dto
│       │   │           │   ├── AuthResponse.class
│       │   │           │   ├── CommentDto.class
│       │   │           │   ├── LoginRequest.class
│       │   │           │   ├── PostDto.class
│       │   │           │   ├── RegisterRequest.class
│       │   │           │   └── VoteDto.class
│       │   │           ├── exception
│       │   │           │   ├── GlobalExceptionHandler.class
│       │   │           │   ├── ResourceNotFoundException.class
│       │   │           │   └── ValidationException.class
│       │   │           ├── model
│       │   │           │   ├── Comment.class
│       │   │           │   ├── Community.class
│       │   │           │   ├── Post.class
│       │   │           │   ├── User.class
│       │   │           │   ├── Vote.class
│       │   │           │   └── VoteType.class
│       │   │           ├── security
│       │   │           │   ├── JWTAuthenticationFilter.class
│       │   │           │   ├── JwtAuthenticationEntryPoint.class
│       │   │           │   ├── JwtProvider.class
│       │   │           │   └── SecurityConstants.class
│       │   │           └── service
│       │   │               ├── AuthService.class
│       │   │               ├── CommentService.class
│       │   │               ├── CommunityService.class
│       │   │               ├── PostService.class
│       │   │               ├── UserService.class
│       │   │               ├── VoteService.class
│       │   │               └── impl
│       │   │                   ├── AuthServiceImpl.class
│       │   │                   ├── CommentServiceImpl.class
│       │   │                   ├── CommunityServiceImpl.class
│       │   │                   ├── PostServiceImpl.class
│       │   │                   ├── UserDetailsServiceImpl.class
│       │   │                   ├── UserServiceImpl.class
│       │   │                   └── VoteServiceImpl.class
│       │   └── hibernate.cfg.xml
│       ├── generated-sources
│       │   └── annotations
│       └── test-classes
│           └── com
│               └── csye6220
│                   └── finalProject
│                       └── FinalProjectApplicationTests.class
└── README.md
```


---

## 🏛️ System Architecture

ForDocs follows the **Model-View-Controller (MVC)** architecture:

- **Model:** Represents application data and database entities using **Hibernate & JPA annotations**.
- **View:** JSP files dynamically render content based on user roles and interactions.
- **Controller:** Handles HTTP requests, processes data, and delegates logic to services.

---

## 📌 Key Components

### **Controllers**
- `AuthController`: Manages user authentication, login, signup, and logout.
- `UserController`: Handles user profiles, updates, and community memberships.
- `PostController`: Manages post creation, updates, deletions, and fetching posts by user/community.
- `CommentController`: Manages comments, including creation, updates, and deletions.
- `CommunityController`: Handles community creation, member management, and community retrieval.
- `VoteController`: Manages upvote and downvote functionality.

### **Services**
- `AuthService`: Handles authentication and **JWT token** generation.
- `UserService`: Implements user-specific operations, including profile and password management.
- `PostService`: Encapsulates logic for **creating, updating, and retrieving posts**.
- `CommentService`: Manages comment-related operations.
- `CommunityService`: Implements community-specific operations such as **member management**.
- `VoteService`: Processes upvotes and downvotes.

---

## 🚀 Getting Started

### Prerequisites
- **Java 17+**
- **Spring Boot**
- **PostgreSQL**
- **Maven**

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/akhilk2802/ForDocs.git
   cd ForDocs
   ```
2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/fordocs_db
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   ```
3. Build and run the project:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

### API Documentation
- **Base URL:** `http://localhost:8080/api/v1/`
- **Swagger Documentation:** `http://localhost:8080/swagger-ui.html`

---

## 👨‍💻 Contributing
Contributions are welcome! Follow these steps:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Create a pull request.



