[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/AY2021S1-CS2103T-T09-1/tp/actions)

#GradPad

Keep track of your degree progress and modules taken during your time in NUS with ease. Faster and more 
lightweight than traditional GUI applications, view and update your progress by issuing simple text commands. 

![Ui](docs/images/Ui.png)

##Installation
###Requirements
GradPad requires that you have a Java SE Runtime Environment (JRE) installed on your machine.

###Steps
1. Download the latest jar release of GradPad from this repository.
2. Run the downloaded jar file
    - You can either double-click on the jar file, or
    - Execute this command from your terminal: `java -jar GradPad.jar`

##Features
####Adding a module
Command: ```add {module title} {modular credits}```

Example usage: ```add CS2103T 4```

####Deleting a module
Command: ```delete {module title}``` 

Example usage: ```delete CS2103T```

####View all added modules
Command: ```list```

####View total modular credits (MCs)
Command: ```checkmc```

####Save session
Command: ```save```

####View help
Command: ```help```

####Exit
Command: ```exit```

##Usage
For detailed guidance on using GradPad, take a look at our user guide 
[here](https://ay2021s1-cs2103t-t09-1.github.io/tp/UserGuide.html).

For developers, view our detailed documentation 
[here](https://ay2021s1-cs2103t-t09-1.github.io/tp/DeveloperGuide.html).

##Contributing
Although GradPad is an open source project, it is currently not open to public contribution.

##Authors and acknowledgement
This project is based on the AddressBook-Level3 project created by the 
[SE-EDU initiative](https://se-education.org).
