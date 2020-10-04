package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.GradPad;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code GradPad} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleCode("Alex Yeoh"), new ModularCredits("87438807"),
                       getTagSet("friends")),
            new Module(new ModuleCode("Bernice Yu"), new ModularCredits("99272758"),
                       getTagSet("colleagues", "friends")),
            new Module(new ModuleCode("Charlotte Oliveiro"), new ModularCredits("93210283"),
                       getTagSet("neighbours")),
            new Module(new ModuleCode("David Li"), new ModularCredits("91031282"),
                       getTagSet("family")),
            new Module(new ModuleCode("Irfan Ibrahim"), new ModularCredits("92492021"),
                       getTagSet("classmates")),
            new Module(new ModuleCode("Roy Balakrishnan"), new ModularCredits("92624417"),
                       getTagSet("colleagues"))
        };
    }

    public static ReadOnlyGradPad getSampleAddressBook() {
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
