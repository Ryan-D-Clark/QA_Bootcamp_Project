# QA_Bootcamp_Project
Final Spring Boot project made at the end of the 10-week QA software development bootcamp demonstating the technologies that we have covered.



## Table of contents
* [Why are we doing this](#why-are-we-doing-this?)
* [How I expected the project to go](#how-i-expected-the-project-to-go)
* [What went well](#what-went-well)
* [What didn't go well](#what-didnt-go-well)
* [Possible improvements for future versions of the project](#possible-improvements-for-future-versions-of-the-project)
* [Using Postman to test CRUD functionality](#using-postman-to-test-crud-functionality)
* [H2-console](#h2-console)
* [Testing](#testing)


## Why are we doing this?
The purpose of this project was to be able to demonstrate the different technologies we had been taught throughout the Bootcamp and be able to demonstrate our ability to use them.

Different technologies that were covered during bootcamp:
* Integrated Development Environment: Intellij
* Version Control System: Git
* Source Code Management: GitHub
* Kanban Board: Jira (Scrum Board)
* Database Management System: H2, MySQL Server 
* Back-End Programming Language: Java
* API Development Platform: Spring 
* Build Tool: Maven
* Unit & Integration testing: JUnit, Mockito

I personally opted in for the bootcamp as I have previously struggled with trying to find a role within the industry; having not gone to university I have been hesitant when applying for junior roles that have wanted previous experience. I believe that this bootcamp has now given me the ability and confidence to go for these roles.
## How I expected the project to go
Having previously worked on a personal Flask project, I was already familiar with some apsects of the project such as URL routing and requests. My biggest concern was testing and creating the actual API calls, as both of these were brand new concepts I thought that it might have been a challenge to be able to create and fully test them within the project timespan of a week.

Setting up the project was an area concern as I had previously faced issues when classes and packages were named incorrectly. As well as this, I was using Intellij instead of Eclipse which had its own set of issues such as not providing the option to create a Spring Boot application in the community version of the IDE.

Documentation was also another area of concern as although it had been covered throughout the bootcamp I didn't know to what extent I needed to go when creating it. My only other experience of project documentation was my A-level project which was a step-by-step guide but I didn't think that this project required that. 

## What went well
As expected, I was able to complete the core features (CRUD functionality) of the project within the first couple of days which I had initially planned. This meant that I could spend more time on the testing components which I was still unsure about

## What didn't go well
Unfortunately, after allocating too much time to testing and not enough to documentation I was falling behind on the last couple of days which means that although I covered all areas for the MVP, I did not complete all my targets. One of my main goals was to add a front-end so that the project would be easier to demonstrate, however I have decided that I will implement this later on as I don't want to have broken/unfinished components in my project.

<p align="center">
  <img src="https://github.com/Ryan-D-Clark/QA_Bootcamp_Project/blob/dev/Documentation/Screenshots/H2%20dependency%20issue.PNG?raw=true" alt="An image of H2 console asking me to sign in with the generated password and unknown user" />
</p>

One issue I faced in my project (as shown by the image above) was that I added the wrong H2 dependency which generated a password and wanted me to sign in although I did not know the user (sa didn't work). The fix was relatively simple as I just had to make a change to the dependency but it did cause issues when I was trying to test CRUD functionality.

<p align="center">
  <img src="https://github.com/Ryan-D-Clark/QA_Bootcamp_Project/blob/dev/Documentation/Screenshots/Forgot%20to%20label%20service%20as%20a%20service.PNG?raw=true" alt="An image of my Service class showing how I didn't label it as a service" />
</p>

Another issue that I encountered was that I forgot to label my Service as '@Service' (as shown above). Although this was easy to fix I struggled with finding the actual problem as I originally thought it was an issue to do with my package structure as it was failing to import Service.
## Possible improvements for future versions of the project
For future versions of the project I would like to add a front-end component that allows users to perform queries as I belive that this is more user-friendly then trying to make different requests. As well as this I want to explore custom exceptions as this was a stretch goal that I didn't manage to get to.

I would also like to try hosting the API so that it could be used without needing to download the repository. I have found [pages.github](https://pages.github.com/) that allows you to host a repository so I might try to do something with this in the future.


## Using Postman to test CRUD functionality
Screenshots also added below.
### Create
> Request Method: post(localhost:8080/api/create)
```
body = {
    "name": "Isla",
    "specie": "Crested Gecko",
    "age": 3,
    "gender": "Female",
    "insectivore": false,
    "arboreal": true
}
```
> Response: {
    "id": 1,
    "name": "Isla",
    "specie": "Crested Gecko",
    "age": 3,
    "gender": "Female",
    "insectivore": false,
    "arboreal": true
}

>  Status: 201 Created
<p align="center">
  <img src="https://github.com/Ryan-D-Clark/QA_Bootcamp_Project/blob/dev/Documentation/Screenshots/Postman/create%20with%20body.png?raw=true" alt="A screenshot of Postman running" />
</p>

### ReadAll
Records persisted on different requests.
> Request Method: get(localhost:8080/api/readAll)

> Response: [
    {
        "id": 1,
        "name": "Isla",
        "specie": "Crested Gecko",
        "age": 3,
        "gender": "Female",
        "insectivore": false,
        "arboreal": true
    }
]

> Status: 200 Ok
<p align="center">
  <img src="https://github.com/Ryan-D-Clark/QA_Bootcamp_Project/blob/dev/Documentation/Screenshots/Postman/readAll%20after%20creating%20records.PNG?raw=true" alt="A screenshot of Postman running" />
</p>

### ReadById
Records persisted on different requests.
> Request Method: get(localhost:8080/api/readById/1)

> Response: {
    "id": 1,
    "name": "Isla",
    "specie": "Crested Gecko",
    "age": 3,
    "gender": "Female",
    "insectivore": false,
    "arboreal": true
}

> Status: 200 Ok

<p align="center">
  <img src="https://github.com/Ryan-D-Clark/QA_Bootcamp_Project/blob/dev/Documentation/Screenshots/Postman/readById%20on%20existing%20record.PNG?raw=true" alt="A screenshot of Postman running" />
</p>

### Update
Records persisted on different requests.
> Request Method: put(localhost:8080/api/update/1)
```
body = {
    "name": "Isla",
    "specie": "Gargoyle Gecko",
    "age": 5,
    "gender": "Female",
    "insectivore": false,
    "arboreal": true
}
```
> Response: {
    "id": 1,
    "name": "Isla",
    "specie": "Gargoyle Gecko",
    "age": 5,
    "gender": "Female",
    "insectivore": false,
    "arboreal": true
}

> Status: 202 Accepted

<p align="center">
  <img src="https://github.com/Ryan-D-Clark/QA_Bootcamp_Project/blob/dev/Documentation/Screenshots/Postman/update%20on%20an%20existing%20ID.PNG?raw=true" alt="A screenshot of Postman running" />
</p>

### Delete
Records persisted on different requests.
> Request Method: delete(localhost:8080/api/delete/1)

> Status: 204 No-content

<p align="center">
  <img src="https://github.com/Ryan-D-Clark/QA_Bootcamp_Project/blob/dev/Documentation/Screenshots/Postman/update%20on%20an%20existing%20ID.PNG?raw=true" alt="A screenshot of Postman running" />
</p>

## H2-console

## Testing
### Controller Integration Test
### Controller Unit Test
### Entity Test
### Service Unit Test
