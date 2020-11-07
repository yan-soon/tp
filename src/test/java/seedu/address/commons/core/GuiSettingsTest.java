package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Point;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class GuiSettingsTest {

    private final GuiSettings defaultGui = new GuiSettings();
    @Test
    public void getWindowWidth_validTest() {
        double expected = 740;
        double actual = defaultGui.getWindowWidth();
        assertEquals(expected, actual);
    }

    @Test
    public void getWindowHeight_validTest() {
        double expected = 600;
        double actual = defaultGui.getWindowHeight();
        assertEquals(expected, actual);
    }

    @Test
    public void getWindowCoordinates_validTest() {
        Point actual = defaultGui.getWindowCoordinates();
        assertNull(actual);
    }

    @Test
    public void equals_validTest() {
        Config testConfig = new Config();
        assertFalse(defaultGui.equals(testConfig));
    }

    @Test
    public void hashCode_validTest() {
        int expected = Objects.hash(defaultGui.getWindowWidth(),
                defaultGui.getWindowHeight(), defaultGui.getWindowCoordinates());
        int actual = defaultGui.hashCode();
        assertEquals(expected, actual);
    }
}
