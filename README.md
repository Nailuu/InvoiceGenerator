# Usage

1. Add the `.csv` file at root directory of the application
2. Package application with Maven
3. Add application.properties file in `src/main/resources/` (see file: application.properties.example)
4. Run:
`java -jar .\target\InvoiceGenerator-0.0.1-SNAPSHOT.jar --spring.config.location=file:src/main/resources/application.properties`