---
layout: page
title: Muhammad Syafiq Bin Abas's Project Portfolio Page
---

## Project: GradPad

GradPad provides an all-in-one solution to **Module Management** for Computer Science Undergraduates. It
**consolidates** all the processes required for students to **plan their modules** and **track their academic progress** 
into a single platform. The first of its kind - GradPad offers a **collaboration** like no other, allowing students to 
manage their modules more efficiently without the need to use several platforms all at once.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=mhdsyfq&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added the ability to check accumulated Modular Credits (MCs) using `checkmc`.

  * What it does: Allows users to check how many Modular Credits they have attained based on the modules that they have
  completed/added into GradPad.
  
  * Justification: This feature improves the application slightly as users might want to occasionally check how much 
  MCs they have attained so far so as to gauge how close they are to graduation without having to check the MCs for each
  module and manually count. 
    
* **New Feature**: Added the ability to choose to `delete`/`clear` with a confirmation prompt, or to force delete or
force clear using `fdelete`/`fclear`.

    * What it does: Allows users to provide confirmation whether or not they want to proceed with deleting or clearing
    data, and also allows users to force delete or clear data without a confirmation prompt.
    
    * Justification: This feature is significant because users can make mistakes in certain commands that results in them
    losing data and the app should provide a convenient way for users to avoid making those mistakes by prompting users
    for a confirmation before executing the commands. Some users (usually regulars or experts), however, may find it
    troublesome to have to constantly provide confirmation, so the app should also provide a way for users to
    instantly execute these commands.
    
    * Highlights: This enhancement of adding a confirmation prompt affects some existing commands and commands to be 
    added in future. The implementation was fairly challenging and it required an in-depth analysis of design alternatives as there was a need to stall the original 
    command, prompt for a confirmation, and then execute or stop the execution of the command (based on the confirmation
    provided). After figuring out the implementation, however, the extension to other existing commands was relatively
    straightforward.
    
* **Enhancements to existing features**:
  * Refactored the AB3 `Ui` package and components to suit the components of GradPad (Pull requests [\#37](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/37)).
  * Changed the entire outlook of the Graphical User Interface (GUI).
    * 
  * Added assertions to the `NUSMods` package

* **Team tasks**:
  * Set up assertions in Gradle.
  * Updated the project's readme page to reflect the project's features and direct all hyperlinks to GradPad pages.
  * Updated GradPad's Jekyll config (remove AB3-specific content).
  * Added feature summary list and command summary table to the user guide.
  
* **Project management**:
  * Managed releases `v1.3-trial` and `v1.3` (2 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `tag` [\#131](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/131)
    * Added documentation for the feature `edit` [\#64](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/64)
    * Added documentation to explain the automatic addition of module titles and modular credits 
    [\#83](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/64)
    * Updated documentation for the features `add`, `find`, `edit` 
    [\#131](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/131)
    * Updated introduction to include the purpose of the guide
    [\#113](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/113)
  * Developer Guide:
    * Added architecture details and design considerations for the `NUSMods` component, including a class diagram.
    [\#78](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/78)
    * Added implementation details of the `edit`, `list`, `checkmc`, `required`, `search` features, including
    all sequence diagrams.
    [\#78](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/78) & 
    [\#42](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/42)
    * Updated the `Storage` component class diagram.
    [\#42](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/42)

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [\#97](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/97), 
  [\#94](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/94), 
  [\#133](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/133), 
  [\#111](https://github.com/AY2021S1-CS2103T-T09-1/tp/pull/111)
  * Assisted teammates with any Git queries or issues.
  * Assisted in the program design of adding confirmation prompts when executing `delete` and `clear` commands.
  
* **Community**:
  * Assisted in testing and reporting bugs for another team's product:
  [\#1](https://github.com/Silvernitro/ped/issues/1),
  [\#2](https://github.com/Silvernitro/ped/issues/2),
  [\#3](https://github.com/Silvernitro/ped/issues/3),
  [\#4](https://github.com/Silvernitro/ped/issues/4),
  [\#5](https://github.com/Silvernitro/ped/issues/5),
  [\#6](https://github.com/Silvernitro/ped/issues/6),
  [\#7](https://github.com/Silvernitro/ped/issues/7)
