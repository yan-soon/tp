package seedu.address.model.util;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.storage.JsonGradPadStorage;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SampleDataUtilTest {

    public static final String TEST_SAMPLE_MODULES_PATH = "src/test/data/SampleDataUtilTest/samplemodules.json";
    @Test
    public void getSampleGradPad_validTest() throws IOException, DataConversionException {
        JsonGradPadStorage testGradPad = new JsonGradPadStorage(Paths.get(TEST_SAMPLE_MODULES_PATH));
        ReadOnlyGradPad expected = testGradPad.readGradPad().get();
        ReadOnlyGradPad actual = SampleDataUtil.getSampleGradPad();
        assertEquals(expected, actual);
    }
}