package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.YesCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.GradPadParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String MESSAGE_CONFIRMATION_SYNTAX = "\n\nType:\tyes<Enter>\tto confirm "
            + "OR\nType:\tno<Enter>\t\tto cancel";
    public static final String MESSAGE_CONFIRMATION_CANCEL = "Command aborted - ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final GradPadParser gradPadParser;

    private Command stalledCommand;
    private String stalledCommandText;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        assert(model != null && storage != null);
        this.model = model;
        this.storage = storage;
        gradPadParser = new GradPadParser();
    }

    /**
     * Handles a stalled situation where a command requires confirmation from the user to execute.
     *
     * @param command The command to be stalled.
     * @param commandText The user input text to be stalled.
     * @return A CommandResult requesting confirmation from the user.
     * @throws CommandException if a requested DeleteCommand is invalid.
     */
    public CommandResult handleStall(Command command, String commandText) throws CommandException {
        if (command instanceof ClearCommand) {
            assignStalledComponents(command, commandText);
            return new CommandResult(ClearCommand.MESSAGE_CONFIRMATION + MESSAGE_CONFIRMATION_SYNTAX);
        } else {
            Module moduleToBeDeleted = ((DeleteCommand) command).getModuleToDelete(model);
            assignStalledComponents(command, commandText);
            return new CommandResult(DeleteCommand.MESSAGE_CONFIRMATION + moduleToBeDeleted
                    + MESSAGE_CONFIRMATION_SYNTAX);
        }
    }

    /**
     * Assigns the specified command and commandText to the relevant stalled components.
     *
     * @param command The command to be stalled.
     * @param commandText The user input text to be stalled.
     */
    public void assignStalledComponents(Command command, String commandText) {
        stalledCommand = command;
        stalledCommandText = commandText;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        boolean isCancel = stalledCommand != null && !commandText.equalsIgnoreCase("yes");

        if (isCancel) {
            stalledCommand = null;
            return new CommandResult(MESSAGE_CONFIRMATION_CANCEL
                    + String.format("\"%s\"", stalledCommandText));
        }

        CommandResult commandResult;
        Command command = gradPadParser.parseCommand(commandText);
        boolean isConfirmation = command instanceof YesCommand && stalledCommand != null;

        if (command.requiresStall()) {
            return handleStall(command, commandText);
        } else if (isConfirmation) {
            command = stalledCommand;
            stalledCommand = null;
        }

        commandResult = command.execute(model);

        try {
            storage.saveGradPad(model.getGradPad());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyGradPad getGradPad() {
        return model.getGradPad();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public Path getGradPadFilePath() {
        return model.getGradPadFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
