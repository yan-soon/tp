package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the GradPad.
     *
     * @see seedu.address.model.Model#getGradPad()
     */
    ReadOnlyGradPad getGradPad();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns the user prefs' GradPad file path.
     */
    Path getGradPadFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Assigns the parameters to the various attributes.
     *
     * @param command Command that requires stalling.
     * @param commandText User input.
     */
    void assignStalledComponents(Command command, String commandText);

    /**
     * Returns the Stalled Command.
     */
    Command getStalledCommand();

    /**
     * Returns the Stalled Command's accompanying arguments.
     */
    String getStalledCommandText();

    /**
     * Checks if the command given requires stalling.
     *
     * @param command Command to check.
     * @param commandText User input.
     */
    CommandResult handleStall(Command command, String commandText) throws CommandException;
}
