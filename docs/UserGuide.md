---
layout: page
title: User Guide
---


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## GradPad

GradPad is a one-stop solution to module management for Computer Science Undergraduates.
Planning for modules has always been a tedious process but it does not have to be.

The current approach to planning and tracking graduation requirements is to open up tabs after tabs of NUS resources which can
be messy at times. So our team has come up with the idea of an easy-to-use, all-in-one application that
can ease the process of module management for Computer Science Undergraduates.

GradPad is able to consolidate the modules you have taken and display the remaining required modules to
ease your module planning process. A module searching platform is also included in GradPad, providing easy
navigation of modules.

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `GradPad.jar` from [here](https://github.com/AY2021S1-CS2103T-T09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your GradPad.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all added modules.

   * **`add`**`CS2103T 4` : Adds a module named `CS2103T` with `4` modular credits to the GradPad.

   * **`delete`**`CS2103T` : Deletes the module titled `CS2103T` in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Feature Summary List](#feature-summary-list) below for a summary of all commands.

--------------------------------------------------------------------------------------------------------------------

## Feature Summary List

 Features                         |    Description 
 ----------------------------------------|------------
 View Help                               | Allows user to view a list of all possible commands
 Add a Module                            | Allows user to add any Module of choice into the `Completed Modules` section for tracking purposes
 View Added Modules                      | Allows user to check all their added modules in `Completed Modules`
 Find Modules from Completed Modules     | Allows user to look for modules in `Completed Modules` using keywords
 Delete a Module                         | Allows user to delete unwanted modules in `Completed Modules`
 Edit a Module                           | Allows user to edit the details of a module they have already added in the `Completed Modules`
 Check Modular Credits                   | Allows user to display their total accumulated modular credit score
 View All Required Modules               | Allows user to display all required modules in the Computer Science curriculum onto the `Command Line Display`
 Search for a Required Module            | Allows user to search for a required module and display the module details in the `Command Line Display`
 Exit the Program                        | Allows user to terminate the GradPad program


 Refer to [features](#features) below for details on all commands.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the Command Format and Feature Descriptions:**<br>

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  
* Highlighted words (Eg. `This`) refer to actual commands or sections on the GradPad 
user interface.

</div>

### View Help : `help`

Description:

 * Lists all of GradPad's available commands and their respective functions.

Format: `help`

Example of usage: `help`

Expected outcome:

<img src="images/help.png" width="550px" height="350px">

### Add a Module : `add`

Description:

 * Adds any Module of choice into the list of Current Modules in GradPad.

 * User can add any module by specifying their desired Module Code (eg. CS1231),
 Modular Credits (eg. 4), and Tags (eg. Core) along with the `add` command, in their
 input.
 
 * Module Code and Modular Credit fields are compulsory inputs but Tag fields are optional.
 Multiple Tags are allowed per module. Duplicate Modules (Modules with all the same fields)
 are not allowed.
 
 * When you add a module, GradPad automatically gives it its correct module title based on the module code you've
 entered. For example, Gradpad automatically gives the CS2103T module its title, "Software Engineering".
 As such, there is no need to enter module titles yourself.

Format: `add c/ {module code} cr/ {modular credits}`

Example of usage: `add c/CS2103T cr/4`

Expected outcome:

<img src="images/add.png" width="550px" height="350px">

### View Added Modules : `list`

Description:

 * Displays the list of Current Modules in GradPad in the `Current Modules` section.
 
 * Users can use this command to reset the view of the `Current Modules`
 section, as certain commands (Eg. find) might alter the 
 view of the `Current Modules` section.

Format: `list`

Example of usage: `list`

Expected outcome:

<img src="images/list.png" width="550px" height="350px">

### Edit a Module: `edit`

Description:

 * Edits the details of a module that has already been added to GradPad.
 
 * To choose the module that you want to edit, specify the index of that module in the Current Modules list.
   You may also choose to edit more than one module detail in the same `edit` command.
   
**Note:** Editing the tag of a module does not add on to its existing tags. Rather, it replaces all
existing tags with the new tags you're specifying.

Format: `edit {index} [c/module code] [cr/modular credits] [t/tag]...`

Example of usage: `edit 1 c/CS2103T cr/4 t/Core t/Required`

Expected outcome:

<img src="images/edit.png" width="550px" height="350px">

### Find a Specific Module : `find`

Description:

 * Shows the specified module in the `Current Modules` section.
 
 * Module code must be specified, and the module must exist in
   the list of Current Modules for the module to be successfully displayed.

Format: `find {module code}`

Example of usage: `find CS2103T`

Expected outcome:

<img src="images/find.png" width="550px" height="350px">

### Delete a Module : `delete`

Description:

 * Removes a Module of choice from the list of Current Modules in GradPad.
 
 * Module code must be specified and module must exist in
   the list of Current Modules in Gradpad for successful deletion.

Format: `delete {module code}`

Example of usage: `delete CS2103T`

Expected outcome:

<img src="images/delete.png" width="550px" height="350px">

### Check Modular Credits : `checkmc`

Description:

 * Displays the current amount of Modular Credits achieved from 
 all Modules in the list of Current Modules in GradPad.

 * The current amount will be displayed at the `Command Line Display`
 section.

Format: `checkmc`

Example of usage: `checkmc`

Expected outcome:

<img src="images/checkmc.png" width="550px" height="350px">

### View All Required Modules : `required`

Display all required modules in the Computer Science curriculum onto the `Command Line Display` for easy referencing.

Format: `required`

Example of usage: `required`

Expected outcome:

### Search for a Required Module : `search`

Search for a required module in the Computer Science curriculum using the module code and display the module details in the `Command Line Display`.

The following module details will be displayed:
- module code
- title
- modular credits
- description
- preclusion
- prerequisite

Format: `search {module code}`

Example of usage: `search CS2103T`

Expected outcome:

### Exit : `exit`

Description:

 * Exits GradPad. All changes made while on GradPad will be saved automatically.

Format: `exit`

Example of usage: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous GradPad home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add c/ {module code} cr/ {modular credits}` <br> e.g. `add c/CS2100 cr/4`
**Edit** | `edit {index} [c/module code] [cr/modular credits] [t/tags]` <br> e.g. `edit 1 c/CS2103T t/core`
**Delete** | `delete {module code}`<br> e.g. `delete c/CS2103T`
**Completed Modules** | `list`
**Find** | `find {module code}` <br> e.g. `find CS2103T`
**Check MCs** | `checkmc`
**Required Modules** | `required` 
**Search module details** | `search {module code}` <br> e.g. `search CS2103T`
**Exit** | `exit`
