# ContactHive

A scalable, cloud-based contact management system built with Java Spring Boot, Thymeleaf, and MySQL. The application provides secure authentication with OAuth integration and efficient cloud storage management.


## 🌟 Features

- **Smart Contact Management**: Efficiently organize and manage your contacts with search and filtering
- **OAuth Integration**: Seamless authentication with Google and GitHub
- **Cloud Storage**: Optimized image handling with Amazon S3
- **Secure Routes**: Protected with Spring Security
- **Responsive Design**: Built with Thymeleaf and Tailwind CSS
- **Database Management**: Robust data handling with Amazon RDS
- **Performance Optimization**: Implemented pagination for faster load times
- **Docker Support**: Containerized deployment for consistency across environments

## 🚀 Live Demo

Visit the live application at: [contacthive.onrender.com/home](https://contacthive.onrender.com/home)

## 📸 Screenshots

<div align="center">
  <img src="path_to_screenshot1.png" alt="ContactHive Screenshot 1" width="800">
  <p><em>Dashboard View</em></p>
  
  <img src="path_to_screenshot2.png" alt="ContactHive Screenshot 2" width="800">
  <p><em>Contact Management Interface</em></p>
  
  <img src="path_to_screenshot3.png" alt="ContactHive Screenshot 3" width="800">
  <p><em>Profile Settings</em></p>
</div>

## 🛠️ Technology Stack

### Backend
- Java Spring Boot
- Spring Security
- Spring Data JPA
- MySQL (Amazon RDS) (Deployed currently on [aivencloud](https://aiven.io/)
- Maven

### Frontend
- Thymeleaf
- Tailwind CSS
- JavaScript

### Cloud Infrastructure
- Amazon RDS (Database)
- Amazon S3 (Image Storage)
- Amazon EC2 (Deployment)
- Docker (Containerization)

### Authentication
- Spring Security
- Google OAuth
- GitHub OAuth

## 📁 Project Structure

```
└── shivd131-contacthive/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/contact_hive/
│ │ │ ├── config/
│ │ │ ├── controllers/
│ │ │ ├── entities/
│ │ │ ├── exceptions/
│ │ │ ├── forms/
│ │ │ ├── helpers/
│ │ │ ├── implementation/
│ │ │ ├── repositories/
│ │ │ └── services/
│ │ └── resources/
│ │ ├── static/
│ │ └── templates/
└── .github/workflows/
```

## 🚀 Prerequisites

- Java 11 or higher
- Maven
- Docker
- MySQL
- AWS Account (for S3 and RDS)

## 💻 Installation

1. Clone the repository:
```bash
git clone https://github.com/Shivd131/ContactHive.git
cd ContactHive
```

2. Configure environment variables (create .env file based on .env.example):
```properties
DB_URL=your_database_url
AWS_ACCESS_KEY=your_aws_access_key
AWS_SECRET_KEY=your_aws_secret_key
S3_BUCKET=your_s3_bucket_name
[other variables...]
```

3. Build the application:
```bash
mvn clean install
```

4. Run with Docker:
```bash
docker build -t contacthive .
docker run -p 8080:8080 contacthive
```

Or run directly with Maven:
```bash
mvn spring-boot:run
```

## 🔐 Security Configuration

The application implements comprehensive security measures:

- OAuth2 authentication with Google and GitHub
- Custom login failure handling
- Secure session management
- Protected API routes

## 📊 Performance Optimizations

- Implemented pagination for contact listings
- Optimized image storage with S3
- Efficient database queries
- Containerized deployment for consistent performance

## 🌟 Key Achievements

- Reduced authentication time by 65% through OAuth integration
- Achieved 30% faster load times with strategic pagination
- Implemented robust cloud storage with Amazon RDS and S3
- Successfully containerized and deployed on Amazon EC2

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📂 Complete Directory Structure

```
Directory structure:
└── shivd131-contacthive/
    ├── README.md
    ├── Dockerfile
    ├── et --hard 49952848db4c502e05997b2973e342f375c3634a
    ├── mvnw
    ├── mvnw.cmd
    ├── package.json
    ├── pom.xml
    ├── tailwind.config.js
    ├── .env.example
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── contact_hive/
    │   │   │           └── contact_hive/
    │   │   │               ├── ContactHiveApplication.java
    │   │   │               ├── config/
    │   │   │               │   ├── LoginFailureHandler.java
    │   │   │               │   ├── OAuthAuthenticationSuccessHandler.java
    │   │   │               │   ├── S3Config.java
    │   │   │               │   └── SecurityConfig.java
    │   │   │               ├── controllers/
    │   │   │               │   ├── ApiController.java
    │   │   │               │   ├── AuthController.java
    │   │   │               │   ├── ContactController.java
    │   │   │               │   ├── PageController.java
    │   │   │               │   ├── RootController.java
    │   │   │               │   ├── S3Controller.java
    │   │   │               │   └── UserController.java
    │   │   │               ├── entities/
    │   │   │               │   ├── Contact.java
    │   │   │               │   ├── Providers.java
    │   │   │               │   ├── SocialLink.java
    │   │   │               │   └── User.java
    │   │   │               ├── exeptions/
    │   │   │               │   ├── GlobalExceptionHandler.java
    │   │   │               │   └── ImageUploadException.java
    │   │   │               ├── forms/
    │   │   │               │   ├── ContactForm.java
    │   │   │               │   ├── ContactSearchForm.java
    │   │   │               │   └── UserForm.java
    │   │   │               ├── helpers/
    │   │   │               │   ├── AppConstants.java
    │   │   │               │   ├── CustomResponse.java
    │   │   │               │   ├── Helper.java
    │   │   │               │   ├── Message.java
    │   │   │               │   ├── MessageType.java
    │   │   │               │   ├── ResourceNotFoundException.java
    │   │   │               │   └── SessionHelper.java
    │   │   │               ├── implementation/
    │   │   │               │   ├── ContactServiceImpl.java
    │   │   │               │   ├── EmailServiceImpl.java
    │   │   │               │   ├── ImageServiceImpl.java
    │   │   │               │   ├── SecurityCustomUserDetailService.java
    │   │   │               │   └── UserServiceImpl.java
    │   │   │               ├── repositories/
    │   │   │               │   ├── ContactRepository.java
    │   │   │               │   └── UserRepository.java
    │   │   │               └── services/
    │   │   │                   ├── ContactService.java
    │   │   │                   ├── EmailService.java
    │   │   │                   ├── ImageService.java
    │   │   │                   └── UserService.java
    │   │   └── resources/
    │   │       ├── application.properties
    │   │       ├── static/
    │   │       │   ├── css/
    │   │       │   │   ├── input.css
    │   │       │   │   └── output.css
    │   │       │   ├── images/
    │   │       │   └── js/
    │   │       │       └── contacts.js
    │   │       └── templates/
    │   │           ├── base.html
    │   │           ├── contact.html
    │   │           ├── error.html
    │   │           ├── error_page.html
    │   │           ├── home.html
    │   │           ├── login.html
    │   │           ├── message.html
    │   │           ├── navbar.html
    │   │           ├── register.html
    │   │           ├── services.html
    │   │           ├── success_page.html
    │   │           └── user/
    │   │               ├── add_contact.html
    │   │               ├── contact_modals.html
    │   │               ├── contacts.html
    │   │               ├── dashboard.html
    │   │               ├── profile.html
    │   │               ├── search.html
    │   │               ├── sidebar.html
    │   │               ├── update_contact.html
    │   │               ├── update_contact_modal.html
    │   │               └── user_navbar.html
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── contact_hive/
    │                   └── contact_hive/
    │                       └── ContactHiveApplicationTests.java
    ├── .github/
    │   └── workflows/
    │       └── render-deploy.yml
    └── .mvn/
        └── wrapper/
            └── maven-wrapper.properties

```
## 🙏 Acknowledgments

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [AWS Documentation](https://docs.aws.amazon.com)
- [Docker Documentation](https://docs.docker.com)
