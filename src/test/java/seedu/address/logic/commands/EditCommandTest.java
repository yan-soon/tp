package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MODULE;
import static seedu.address.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NON_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.model.GradPad;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder().build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditCommand editCommand = new EditCommand(CODE_FIRST_MODULE, descriptor);
        Module moduleToEdit = model.getGradPad().getModuleList().stream()
            .filter(x -> x.getModuleCode().equals(CODE_FIRST_MODULE)).findFirst().get();

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, moduleToEdit, editedModule);

        Model expectedModel = new ModelManager(new GradPad(model.getGradPad()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        ModuleCode moduleCodeLastModule = CODE_SECOND_MODULE;
        Module lastModule = model.getFilteredModuleList().stream()
            .filter(x -> x.getModuleCode().equals(moduleCodeLastModule)).findFirst().get();

        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module editedModule = moduleInList.withTags(VALID_TAG_CORE).build();

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
            .withModuleCode(VALID_CODE_CS3216).withModuleTitle(VALID_TITLE_CS3216)
            .withModularCredits(VALID_CREDITS_CS3216).withTags(VALID_TAG_CORE).build();
        EditCommand editCommand = new EditCommand(moduleCodeLastModule, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, lastModule, editedModule);

        Model expectedModel = new ModelManager(new GradPad(model.getGradPad()), new UserPrefs());
        expectedModel.setModule(lastModule, editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(CODE_FIRST_MODULE, new EditModuleDescriptor());
        Module moduleToEdit = model.getGradPad().getModuleList().stream()
            .filter(x -> x.getModuleCode().equals(CODE_FIRST_MODULE)).findFirst().get();
        Module editedModule = model.getFilteredModuleList().stream()
            .filter(x -> x.getModuleCode().equals(CODE_FIRST_MODULE)).findFirst().get();

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, moduleToEdit, editedModule);

        Model expectedModel = new ModelManager(new GradPad(model.getGradPad()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Module moduleInFilteredList = model.getFilteredModuleList().stream()
            .filter(x -> x.getModuleCode().equals(CODE_FIRST_MODULE)).findFirst().get();
        Module editedModule = new ModuleBuilder(moduleInFilteredList)
            .withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE).build();
        EditCommand editCommand = new EditCommand(CODE_FIRST_MODULE,
                new EditModuleDescriptorBuilder().withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE).build());

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, moduleInFilteredList, editedModule);

        Model expectedModel = new ModelManager(new GradPad(model.getGradPad()), new UserPrefs());
        expectedModel.setModule(moduleInFilteredList, editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().stream()
            .filter(x -> x.getModuleCode().equals(CODE_FIRST_MODULE)).findFirst().get();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        EditCommand editCommand = new EditCommand(CODE_SECOND_MODULE, descriptor);

        assertCommandFailure(editCommand, model, String.format(MESSAGE_DUPLICATE_MODULE,
            firstModule.getModuleCode()));
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        // edit module in filtered list into a duplicate in GradPad
        Module moduleInList = model.getGradPad().getModuleList().stream()
            .filter(x -> x.getModuleCode().equals(CODE_SECOND_MODULE)).findFirst().get();
        EditCommand editCommand = new EditCommand(CODE_FIRST_MODULE,
                new EditModuleDescriptorBuilder(moduleInList).build());

        assertCommandFailure(editCommand, model, String.format(MESSAGE_DUPLICATE_MODULE,
            moduleInList.getModuleCode()));
    }

    @Test
    public void execute_moduleNotYetAddedUnfilteredList_failure() {
        ModuleCode moduleNotYetAdded = new ModuleCode("CS2108");
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
            .withModuleCode(VALID_CODE_CS3216).build();
        EditCommand editCommand = new EditCommand(moduleNotYetAdded, descriptor);

        assertCommandFailure(editCommand, model, String.format(MESSAGE_INVALID_MODULE,
            moduleNotYetAdded));
    }

    /**
     * Edit filtered list where module code does exists in NUSMods but not yet added into GradPad.
     */
    @Test
    public void execute_moduleNotYetAddedFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        ModuleCode moduleNotYetAdded = new ModuleCode("CS2108");

        EditCommand editCommand = new EditCommand(moduleNotYetAdded,
            new EditModuleDescriptorBuilder().withModuleCode(VALID_CODE_CS3216).build());

        assertCommandFailure(editCommand, model, String.format(MESSAGE_INVALID_MODULE,
            moduleNotYetAdded));
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(CODE_FIRST_MODULE, DESC_CS2103T);

        // same values -> returns true
        EditModuleDescriptor copyDescriptor = new EditModuleDescriptor(DESC_CS2103T);
        EditCommand commandWithSameValues = new EditCommand(CODE_FIRST_MODULE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different code -> returns false
        assertFalse(standardCommand.equals(new EditCommand(CODE_SECOND_MODULE, DESC_CS2103T)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(CODE_FIRST_MODULE, DESC_CS3216)));
    }

}
