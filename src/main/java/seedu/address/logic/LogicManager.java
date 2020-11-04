package seedu.address.logic;

import static seedu.address.commons.core.Messages.FILE_OPS_ERROR_MESSAGE;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_CONFIRMATION;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_CONFIRMATION;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_GRADPAD;

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

    private static Command stalledCommand;
    private static String stalledCommandText;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final GradPadParser gradPadParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        assert(model != null && storage != null);
        this.model = model;
        this.storage = storage;
        gradPadParser = new GradPadParser();
    }

    private CommandResult handleStall(Command command, String commandText) throws CommandException {
        if (command instanceof ClearCommand) {
            assignStalledComponents(command, commandText);
            return new CommandResult(MESSAGE_CLEAR_CONFIRMATION);
        } else {
            Module moduleToBeDeleted = ((DeleteCommand) command).getModuleToDelete(model);
            assignStalledComponents(command, commandText);
            return new CommandResult(MESSAGE_DELETE_CONFIRMATION + moduleToBeDeleted);
        }
    }

    private void assignStalledComponents(Command command, String commandText) {
        stalledCommand = command;
        stalledCommandText = commandText;
    }

    public static Command getStalledCommand() {
        return stalledCommand;
    }

    public static String getStalledCommandText() {
        return stalledCommandText;
    }

    public static void setStalledCommandToNull() {
        stalledCommand = null;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = gradPadParser.parseCommand(commandText);
        boolean isConfirmation = command instanceof YesCommand && stalledCommand != null;

        if (command.requiresStall()) {
            if (model.isEmpty()) {
                throw new CommandException(MESSAGE_EMPTY_GRADPAD);
            }
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
