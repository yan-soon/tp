package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NON_CORE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.GradPad;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2103T = new ModuleBuilder().withCode("CS2103T")
            .withModularCredits("4")
            .withTags("core").build();

    public static final Module ALICE = new ModuleBuilder().withCode("Alice Pauline")
                                               .withModularCredits("94351253")
                                               .withTags("friends").build();
    public static final Module BENSON = new ModuleBuilder().withCode("Benson Meier")
                                                .withModularCredits("98765432")
                                                .withTags("owesMoney", "friends").build();
    public static final Module CARL = new ModuleBuilder().withCode("Carl Kurz").withModularCredits("95352563")
            .build();
    public static final Module DANIEL = new ModuleBuilder().withCode("Daniel Meier").withModularCredits("87652533")
                                                .withTags("friends").build();
    public static final Module ELLE = new ModuleBuilder().withCode("Elle Meyer").withModularCredits("9482224")
                                              .build();
    public static final Module FIONA = new ModuleBuilder().withCode("Fiona Kunz").withModularCredits("9482427")
                                               .build();
    public static final Module GEORGE = new ModuleBuilder().withCode("George Best").withModularCredits("9482442")
                                                .build();

    // Manually added
    public static final Module HOON = new ModuleBuilder().withCode("Hoon Meier").withModularCredits("8482424")
                                              .build();
    public static final Module IDA = new ModuleBuilder().withCode("Ida Mueller").withModularCredits("8482131")
                                             .build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module CS3216 =
            new ModuleBuilder().withCode(VALID_CODE_CS3216).withModularCredits(VALID_CREDITS_CS3216)
                                             .withTags(VALID_TAG_NON_CORE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code GradPad} with all the typical modules.
     */
    public static GradPad getTypicalGradPad() {
        GradPad ab = new GradPad();
        for (Module module: getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
