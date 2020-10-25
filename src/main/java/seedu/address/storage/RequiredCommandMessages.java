package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RequiredCommandMessages {
    public static final String MESSAGE_FOUNDATION = "These are the Foundation Modules you have yet to take: ";
    public static final String MESSAGE_FAILURE = "There was an error loading the Modules :(";
    public static final String MESSAGE_SCIENCE = "You have not completed your Science Module requirement,"
            + " use the 'science' command to view the available modules.";
    public static final String MESSAGE_INTERN_1 = "You have yet to complete your 12MCs worth of Internship Modules.";
    public static final String MESSAGE_INTERN_2 = "These are the Internship Modules you can take:";
    public static final String MESSAGE_SUCCESS_FOUNDATION = "You have completed all your Foundation Modules!";
    public static final String MESSAGE_SUCCESS_SCIENCE = "You have completed your Science Module Requirement!";
    public static final String MESSAGE_SUCCESS_INTERN = "You have completed your Internship Module Requirement!";
    public static final String MESSAGE_ITPROF = "These are the IT Professionalism Modules you have yet to take:";
    public static final String MESSAGE_SUCCESS_ITPROF = "You have completed your "
            + "IT Professionalism Module Requirement!";
    public static final String MESSAGE_MATHANDSCI = "These are the Math and Science Modules you have yet to take:";
    public static final String MESSAGE_SUCCESS_MATHANDSCI = "You have completed your "
            + "Math and Science Module Requirement!";
    public static final String FOUNDATION_PATH = "data/foundationmodules.json";
    public static final Path INTERNSHIP_PATH = Paths.get("src/main/resources/data/industrialexperience.json");
    public static final Path ITPROF_PATH = Paths.get("src/main/resources/data/ITProfessionalism.json");
    public static final Path MATHANDSCI_PATH = Paths.get("src/main/resources/data/mathandsciencemodules.json");
    public static final Path SCIENCE_PATH = Paths.get("src/main/resources/data/sciencemodules.json");
}
