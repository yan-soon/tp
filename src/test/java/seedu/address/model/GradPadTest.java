package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NON_CORE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.ALICE;
import static seedu.address.testutil.TypicalModules.CS3216;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.testutil.ModuleBuilder;

public class GradPadTest {

    private final GradPad gradPad = new GradPad();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), gradPad.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradPad.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGradPad_replacesData() {
        GradPad newData = getTypicalGradPad();
        gradPad.resetData(newData);
        assertEquals(newData, gradPad);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedCS3216 = new ModuleBuilder(CS3216).withCode(VALID_CODE_CS3216)
                .withModularCredits(VALID_CREDITS_CS3216).withTags(VALID_TAG_NON_CORE)
                .build();
        List<Module> newModules = Arrays.asList(CS3216, editedCS3216);
        GradPadStub newData = new GradPadStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> gradPad.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradPad.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInGradPad_returnsFalse() {
        assertFalse(gradPad.hasModule(ALICE));
    }

    @Test
    public void hasModule_moduleInGradPad_returnsTrue() {
        gradPad.addModule(ALICE);
        assertTrue(gradPad.hasModule(ALICE));
    }

    @Test // Still needs changing
    public void hasModule_moduleWithSameIdentityFieldsInGradPad_returnsTrue() {
        gradPad.addModule(ALICE);
        Module editedAlice = new ModuleBuilder(ALICE).withCode(VALID_CODE_CS2040).withTags(VALID_TAG_CORE)
                .build();
        assertTrue(gradPad.hasModule(editedAlice));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> gradPad.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyGradPad whose modules list can violate interface constraints.
     */
    private static class GradPadStub implements ReadOnlyGradPad {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        GradPadStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
