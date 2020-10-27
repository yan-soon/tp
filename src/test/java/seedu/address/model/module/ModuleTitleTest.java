package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleTitle(null));
    }

    @Test
    public void constructor_invalidModuleTitle_throwsIllegalArgumentException() {
        String invalidModuleTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleTitle(invalidModuleTitle));
    }

    @Test
    public void isValidModuleTitle() {
        // null module title
        assertThrows(NullPointerException.class, () -> ModuleTitle.isValidModuleTitle(null));

        // invalid module title
        assertFalse(ModuleTitle.isValidModuleTitle("")); // empty string
        assertFalse(ModuleTitle.isValidModuleTitle(" ")); // spaces only
        assertFalse(ModuleTitle.isValidModuleTitle("^")); // only non-alphanumeric characters
        assertFalse(ModuleTitle.isValidModuleTitle(
            "Software-Engineering")); // contains non-alphanumeric characters

        // valid module title
        assertTrue(ModuleTitle.isValidModuleTitle("Software Engineering")); // no digits
        assertTrue(ModuleTitle.isValidModuleTitle("Software Engineering 2")); // contains digits
        assertTrue(ModuleTitle.isValidModuleTitle("SOFTWARE ENGINEERING")); // with capital letters
        assertTrue(ModuleTitle.isValidModuleTitle("SoFtwArE eNgiNeerInG")); // mixture of lower and upper case
    }
}
