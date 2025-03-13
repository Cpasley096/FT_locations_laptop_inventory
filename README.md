# Installation Instructions (Windows)

1. Clone the Repository
   ```
   git clone https://github.com/Cpasley096/FT_locations_laptop_inventory.git
   ```

2. Navigate to the Project Directory
   After cloning, navigate into the project directory 

3. Install Java SDK 11+
   ```
   java -version
   ```

4. Install Maven
   Download **Apache Maven** from the official [Maven download page](https://maven.apache.org/download.cgi).
   - Download the zip file for the latest version (e.g., `apache-maven-3.9.9-bin.zip`).
   - Extract the zip file to a folder on your system (e.g., `C:\apache-maven-3.9.9`).

5. Set `MAVEN_HOME` Environment Variable
   1. In the **System Properties** window, click **Environment Variables**.
   2. Under **System Variables**, click **New** to add a new system variable:
      - **Variable Name**: `MAVEN_HOME`
      - **Variable Value**: `C:\apache-maven-3.9.9` (or wherever you extracted Maven)

6. Update the `Path` Environment Variable
   1. In the **Environment Variables** window, scroll down to find the **Path** variable under **System Variables** and click **Edit**.
   2. Click **New** and add the following path:
      - `C:\apache-maven-3.9.9\bin` (or the `bin` directory inside your Maven folder).
   3. Click **OK** to save and close the windows.

7. Verify Maven Installation
   Run the following command to verify Maven is installed (may need to restart terminal)
   ```
   mvn -version
   ```

8. Build the Application
   Once Maven and Java are installed and properly configured, navigate to the project directory if you're not already there (Must be the dir with the pom.xml file)
   Build the application using Maven:
   ```
   mvn clean install
   ```

9. Run the Application
   After the build is complete, run the Spring Boot application using the following command:
   ```
   mvn spring-boot:run
   ```

10. Access the Application in a Browser
   Open your browser and go to:
   ```
   http://localhost:8080
   ```
---

# Installation Mac (I have only tested setup on windows)
1) git clone https://github.com/Cpasley096/FT_locations_test.git
2) cd into FT_locations (Must be in directory that has the pom.xml file)
3) Install Java SDK 11+
4) Install Maven using Homebrew:
   - Run `brew install maven` in the terminal.
5) After installation, verify Maven and Java installations:
   - Run `java -version` and `mvn -v` to verify installation.
6) Run `mvn clean install` (this will download dependencies and build the project)
7) Run `mvn spring-boot:run` (application will run on port 8080)
8) Open http://localhost:8080/ in your browser

---

## **Additional information**
### **1. Data Loading**  
- Sample data is loaded into the database once the application starts via `DataLoader.java`.  
- The application uses an **H2 in-memory database**, meaning data is lost upon restart unless reloaded by `DataLoader.java`.  

### **2. H2 Database (In-Memory Database)**  
- **Why?** It’s lightweight and great for development/testing.  
- **Limitation:** No persistent storage—ideal for demos but not production.  

### **3. Thymeleaf for Server-Side Rendering**  
- The front-end uses **Thymeleaf**, a server-side templating engine that renders HTML before being sent to the browser.  
- **Implication:** The controller returns fully-rendered pages instead of JSON responses.  

---

## **Potential Improvements for Production use** 
### **1. Use a Persistent Relational Database**  
Instead of H2:  
- **PostgreSQL**
- **MySQL**

### **2. Authentication & Authorisation**  
- Add a **Login/Sign-Up system** with:  
  - **Spring Security** for authentication.  
  - **OAuth2** for token-based authentication.  
  - **Role-based access control (RBAC)** to secure endpoints.  

### **3. Move Front-End to a Client-Side Framework**  
Instead of Thymeleaf, use:  
- **Vue.js** 
- **React.js** 
- **Angular** 
- This allows **dynamic content updates without full-page reloads**.  

### **4. RESTful API**  
- Convert the controller logic to return **JSON responses** instead of HTML.  
- Implement a **RESTful API** with endpoints for CRUD operations.  
- Use **Spring Boot with Swagger/OpenAPI** for API documentation.  

### **5. Containerisation & Deployment**  
- **Docker**: Package the application and database in containers.  
- **CI/CD**: Automate testing and deployments with **GitHub Actionss**.  
- **Cloud Deployment**: Deploy on **AWS, Azure, or Google Cloud**.  
