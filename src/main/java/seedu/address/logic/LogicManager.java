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
        this.model = model;
        this.storage = storage;
        gradPadParser = new GradPadParser();
    }

    private void assignStalledComponents(Command command, String commandText) {
        stalledCommand = command;
        stalledCommandText = commandText.trim();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        if (stalledCommand instanceof Command) {
            if (!commandText.equalsIgnoreCase("yes")) {
                stalledCommand = null;
                return new CommandResult(MESSAGE_CONFIRMATION_CANCEL
                        + String.format("\"%s\"", stalledCommandText));
            }
        }

        CommandResult commandResult;
        Command command = gradPadParser.parseCommand(commandText);

        if (command instanceof ClearCommand) {
            assignStalledComponents(command, commandText);
            return new CommandResult(ClearCommand.MESSAGE_CONFIRMATION + MESSAGE_CONFIRMATION_SYNTAX);
        } else if (command instanceof DeleteCommand) {
            assignStalledComponents(command, commandText);
            Module moduleToBeDeleted = ((DeleteCommand) command).getModuleToDelete(model);
            return new CommandResult(DeleteCommand.MESSAGE_CONFIRMATION + moduleToBeDeleted
                    + MESSAGE_CONFIRMATION_SYNTAX);
        }

        if (command instanceof YesCommand) {
            if (stalledCommand == null) {
                return new CommandResult(YesCommand.NO_CONFIRMATION_MESSAGE);
            } else {
                command = stalledCommand;
                stalledCommand = null;
            }
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
