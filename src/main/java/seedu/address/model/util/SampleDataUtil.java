package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.GradPad;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code GradPad} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleCode("CS2103T"), new ModularCredits("4"),
                       getTagSet("Foundation")),
            new Module(new ModuleCode("CS1010"), new ModularCredits("4"),
                       getTagSet("Foundation")),
            new Module(new ModuleCode("CS2101"), new ModularCredits("4"),
                       getTagSet("ITProf")),
            new Module(new ModuleCode("CS2105"), new ModularCredits("4"),
                       getTagSet("Foundation")),
            new Module(new ModuleCode("ST2334"), new ModularCredits("4"),
                       getTagSet("MathAndSciences")),
            new Module(new ModuleCode("CS2107"), new ModularCredits("4"),
                       getTagSet("BreadthAndDepth"))
        };
    }

    public static ReadOnlyGradPad getSampleGradPad() {
        GradPad sampleAb = new GradPad();
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
