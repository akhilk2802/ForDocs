# ForDocs - Community Building Application for Doctors

### ForDocs is a community-building platform similar to Reddit, designed specifically for doctors. It enables healthcare professionals to engage in discussions, share knowledge, and collaborate on medical topics within dedicated communities. Built using Spring Boot and following the MVC architecture, ForDocs uses JSP for views and PostgreSQL for the database.

## Features

### User Features

- 	Registration and Login:
  - Secure user registration and authentication using JWT tokens.
  - Sessions for logged-in users are managed using HttpSession.
  
- Profile Management:
  - View and update user profiles, including posts and comments.
 
- Community Features:
  - Create, join, or leave communities.
  - View members of a community.
  - Discuss and collaborate within communities.

### Post and Comment System

- Users can create, update, and delete posts.
- Add, update, and delete comments on posts.
- Upvote or downvote posts.

### Admin Features

- Manage users, posts, and communities.

### Pagination and Sorting

- Paginated views for posts and communities for better user experience.

### Error Handling
- Custom error handling for invalid operations or resource not found scenarios.


## Technology Stack

### Backend

1. Spring Boot:
   - RESTful API implementation.
   - Dependency injection for modularity.
   - Security configurations using Spring Security.
  
2. Hibernate:
   - ORM framework for interacting with PostgreSQL.

### Frontend

1. JSP (Java Server Pages):
   - Dynamic rendering of views.

### Database

1. PostgreSQL:
   - Relational database for storing user, post, community, and comment data.

### Security

1. JWT:
   - Token-based authentication.


## System Architecture

### ForDocs follows the Model-View-Controller (MVC) architecture:
1. Model:
   - Represents the application data and database entities.
   - Managed via Hibernate and JPA annotations.
2. View:
   - JSP files render dynamic content based on the user’s role and interaction.
3. Controller:
   - Handles HTTP requests, processes data, and delegates to services.



## Key Components

### Controllers

1.	AuthController: Handles user login, signup, and logout functionality.
2.	UserController: Manages user profiles, updating details, and community memberships.
3.	PostController: 
    - Handles post creation, updates, and deletions.
    - Fetches posts by user or community.
4.	CommentController: Manages comments for posts, including creation, updates, and deletions.
5.	CommunityController: Handles community creation, member management, and fetching communities.
6.	VoteController: Manages upvote and downvote functionality for posts.

### Services

1.	AuthService: Handles user authentication and JWT token generation.
2.	UserService: Implements user-specific operations, including profile and password management.
3.	PostService: Encapsulates the logic for creating, updating, and retrieving posts.
4.	CommentService: Manages comment-related operations.
5.	CommunityService: Implements community-specific operations, such as member management.
6.	VoteService: Processes upvotes and downvotes.

