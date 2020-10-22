package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.ModuleInfoSearcher;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.nusmods.ModuleInfo;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS3216;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalGradPad(), new UserPrefs());
    private static final String CS1010X = "src/test/data/NusmodsDataManagerTest/CS1010X.json";

    @Test
    public void equals() {
        String firstPredicate = "first";
        String secondPredicate = "second";

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);

        // same object -> returns true
        assertEquals(searchFirstCommand, searchFirstCommand);

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate);
        assertEquals(searchFirstCommand, searchFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, searchFirstCommand);

        // null -> returns false
        assertNotEquals(null, searchFirstCommand);

        // different module -> returns false
        assertNotEquals(searchFirstCommand, searchSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = ModuleInfoSearcher.MESSAGE_EMPTY_SEARCH;
        String predicate = "";
        SearchCommand command = new SearchCommand(predicate);
        assertCommandFailure(command, model, expectedMessage);

    }

    @Test
    public void execute_searchCommand_success() throws DataConversionException  {
        String predicate = "CS1010X";
        ModuleInfo cs1010x = JsonUtil.readJsonFile(Paths.get(CS1010X), ModuleInfo.class).get();
        SearchCommand command = new SearchCommand(predicate);
        String expectedMessage = String.format(SearchCommand.MESSAGE_SUCCESS,
                cs1010x.getModuleCode(),
                cs1010x.getTitle(), cs1010x.getDescription(),
                cs1010x.getPreclusion(), cs1010x.getPrerequisite());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}