package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_NEED_HELP = "If you need help, type \"help\".";
    public static final String MESSAGE_EMPTY_FIELD = "Please enter a command.\n\n"
        + MESSAGE_NEED_HELP;
    public static final String MESSAGE_UNKNOWN_COMMAND = "You have entered an unknown command. Please try "
        + "again.\n\n" + MESSAGE_NEED_HELP;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Please use the correct format for the "
        + "respective command: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "Module %1$s already exists!";
    public static final String MESSAGE_INVALID_MODULE = "Module %1$s cannot be found!";
    public static final String MESSAGE_EMPTY_GRADPAD = "GradPad is empty!";

    // add command messages
    public static final String ADD_COMMAND_WORD = "add";
    public static final String MESSAGE_ADD_USAGE = ADD_COMMAND_WORD + "\n\nTo add a module, type in \"add\" "
        + "followed by a valid module code and tags\n(Tags are optional and you can have any number of tags, "
        + "including 0)\n\n" + "Example(s):\tadd cs1231\n\t\t\tadd cs1231 t/Foundation t/Core";
    public static final String MESSAGE_ADD_SUCCESS = "New module added: %1$s";

    // checkmc command messages
    public static final String CHECKMC_COMMAND_WORD = "checkmc";
    public static final String MESSAGE_CHECKMC_SUCCESS = "You have accumulated a total of %1$d MCs so far!";

    // clear command messages
    public static final String CLEAR_COMMAND_WORD = "clear";
    public static final String MESSAGE_CLEAR_SUCCESS = "GradPad has been cleared!";
    public static final String MESSAGE_CLEAR_CONFIRMATION = "Are you sure you wish to clear all modules? "
        + "(yes/no)";

    public static final String MESSAGE_MODULES_LISTED_OVERVIEW = "%1$d modules listed!";

}
