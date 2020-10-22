package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.ModuleInfoSearcher;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.nusmods.ModuleInfo;

/**
 * Search for a module to display its module details.
 * Module Code matching is case insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Search for a module with "
            + "the specified ModuleCode (case-insensitive) and display all the module details in the "
            + "Command Line Interface\n"
            + "Parameters: MODULE CODE\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    public static final String MESSAGE_SUCCESS = "Search result for: %1$s \n"
            + "Module Title: %2$s \n\n" + "Module Description: \n%3$s \n\n"
            + "Preclusion: %4$s\n\n" + "Prerequisite: %5$s\n";

    private final String moduleCode;

    /**
     * Creates a SearchCommand to search for a module from the Computer Science
     * curriculum {@code Module}
     */
    public SearchCommand(String moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ModuleInfoSearcher moduleInfoSearcher = new ModuleInfoSearcher();
        ModuleInfo searchResult;
        try {
            searchResult = moduleInfoSearcher.searchModule(moduleCode);
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }
        String searchDisplay = String.format(MESSAGE_SUCCESS, searchResult.getModuleCode(),
                searchResult.getTitle(), searchResult.getDescription(),
                searchResult.getPreclusion(), searchResult.getPrerequisite());
        return new CommandResult(searchDisplay);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && moduleCode.equals(((SearchCommand) other).moduleCode)); // state check
    }
}
