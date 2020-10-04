package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModularCreditsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModularCredits(null));
    }

    @Test
    public void constructor_invalidModularCredit_throwsIllegalArgumentException() {
        String invalidCredits = "";
        assertThrows(IllegalArgumentException.class, () -> new ModularCredits(invalidCredits));
    }

    @Test
    public void isValidMC() {
        // null modular credit value
        assertThrows(NullPointerException.class, () -> ModularCredits.isValidMC(null));

        // invalid modular credits values
        assertFalse(ModularCredits.isValidMC("")); // empty string
        assertFalse(ModularCredits.isValidMC(" ")); // spaces only
        assertFalse(ModularCredits.isValidMC("mc")); // non-numeric
        assertFalse(ModularCredits.isValidMC("12mcs")); // alphabets within digits
        assertFalse(ModularCredits.isValidMC("1 2")); // spaces within digits
        assertFalse(ModularCredits.isValidMC("1234")); // >2 digits long

        // valid modular credits
        assertTrue(ModularCredits.isValidMC("1")); // 1 digit long
        assertTrue(ModularCredits.isValidMC("12")); // 2 digits long
    }
}
