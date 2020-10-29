package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String ADD_COMMAND = "add CS1231 t/Core : Adds module 'CS1231' with 'Core' tag to it.";
    public static final String HELP_COMMAND = "help : Displays the help guide.";
    public static final String LIST_COMMAND = "list : Displays list of current modules stored.";
    public static final String EDIT_COMMAND = "edit CS1231 c/CS1101S :"
            + "Replaces the module 'CS1231' with module 'CS1101S'.";
    public static final String FIND_COMMAND = "find CS1 : Displays all modules that contain the prefix 'CS1'. \n"
            +"find core : Displays all modules that contain the 'core' tag.";
    public static final String DELETE_COMMAND = "delete CS1231 : Deletes the 'CS1231' module from your GradPad.";
    public static final String CHECKMC_COMMAND = "checkmc : Checks the current total credits achieved by all modules.";
    public static final String REQUIRED_COMMAND = "required : Displays left over required modules that you have yet"
            + " to clear.";
    public static final String GEM_COMMAND = "gem : Displays all available General Education modules by the Semester.";
    public static final String SCIENCE_COMMAND = "science : Displays all available Science modules.";
    public static final String SEARCH_COMMAND = "search MA1521 : Searches and displays all relevant information about"
            + " the module 'MA1521'.";
    public static final String TAGS_COMMAND = "tags : Displays all existing tags that are being used in your GradPad.";
    public static final String EXIT_COMMAND = "exit : exits GradPad.";
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = HELP_COMMAND + "\n\n" + ADD_COMMAND + "\n\n" + LIST_COMMAND
            + "\n\n" + EDIT_COMMAND + "\n\n" + DELETE_COMMAND + "\n\n" + FIND_COMMAND + "\n\n" + CHECKMC_COMMAND
            + "\n\n" + REQUIRED_COMMAND+ "\n\n" + GEM_COMMAND + "\n\n" + SCIENCE_COMMAND + "\n\n"
            + SEARCH_COMMAND + "\n\n" + TAGS_COMMAND + "\n\n" + EXIT_COMMAND;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false);
    }
}
