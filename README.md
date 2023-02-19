# Izicap Test 

## Prerequisites
Before running the project, make sure you have the following software installed on your system:
* Git
* Jenkins
* Java 17
* Maven

## Fork the project
To get started, fork the project on GitHub .

## Configure the project
Next, add a valid token to the application.properties file in the chatGPT.API.endpoint property. This token is required to run the project properly.

## Set up Jenkins pipeline
* Open Jenkins on your Windows environment.
* Create a new pipeline by clicking on "New Item" in the Jenkins dashboard.
* Name the pipeline and choose "Pipeline" as the project type.
* In the "Pipeline Definition" section, choose "Pipeline script from SCM".
* In the "SCM" section, choose "Git" as the SCM and paste the URL of the repo.
* In the "Branches to build" section, write "main" (without quotes).
* Click "Apply" and then "Save" to create the pipeline.
* Run the pipeline by clicking on "Build Now".

Jenkins  now automatically build and test the project and run it in run in docker container ,after that you can see the Api Documentation by clicking here [swagger](http://localhost:8080/swagger-ui/index.html)

## Project Dependencies

* __JSON__  : The json package is used to encode and decode JSON data. The package is used in the ChatGPTService class to deserialize the response from the ChatGPT API.


* __OpenCSV__ : The opencsv package is used to read a CSV file that contains a list of questions and answers. The package is used in the CSVService class to parse the CSV data.


* __JUnit__ : The junit package is used to write and run unit tests for the project. The package is used in several test classes to ensure that the project components are working correctly.


*  __Springdoc__ : The springdoc package is used to generate OpenAPI documentation for the project. The package is used in the ChatGPTController class to document the REST endpoint for the chatbot.


## Project Architecture

```
C:.
|   IzicapTestYounesElhakimiApplication.java
|
+---controllers
|       ChatGPTController.java
|
+---DTO
|       QuestionDTOReq.java
|
+---models
|       Question.java
|
\---services
        ChatGPTService.java
        ChatGPTServiceImpl.java
        CSVService.java
        CSVServiceImpl.java

```

* The ChatGPTController.java contain  tree Endpoints :

  * __/answer__ : take a question and return an answer
  * __/answer/all__ : return all questions and answers
  * __//answer/downloadCSV__ : download an CSV File contain all questions and answers
   
* CSVService.java contain two methods
  * __storeQuestionAndAnswerInCSV__ : take one argument 'Question type' and write one line in the csv file
  * __getAllQuestionAndAnswerFromCSV__ : Read the csv file and return List Of Questions

* ChatGPTService.java cantain one method 
  * __getAnswerFromChatGPT__ : method which  query the ChatGPT API and return the answer


## Test Descriptions
The __CSVServiceTest__ package contains two JUnit tests:

* __storeQuestionAndAnswerInCSV()__: This test checks whether the __storeQuestionAndAnswerInCSV()__ method of the CSVService class can store a question and its associated answer in a CSV file. The test creates a new question object, stores it in the CSV file, and checks whether the CSV file was created and whether the question was stored correctly.

* __getAllQuestionAndAnswerFromCSV()__: This test checks whether the __getAllQuestionAndAnswerFromCSV()__ method of the CSVService class can retrieve a list of questions and their associated answers from a CSV file. The test creates two new question objects, stores them in the CSV file, retrieves the list of questions from the CSV file, and checks whether the questions were retrieved correctly.






