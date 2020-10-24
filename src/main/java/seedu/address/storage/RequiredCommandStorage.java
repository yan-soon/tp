package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;

public class RequiredCommandStorage {
    private ObservableList<Module> requiredFoundation;
    private ObservableList<Module> requiredITprof;
    private ObservableList<Module> requiredMathAndScience;
    private ObservableList<Module> requiredScience;
    private ObservableList<Module> requiredInternship;

    /**
     * Returns requiredFoundation attribute of RequiredCommandStorage object.
     * @return requiredFoundation attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredFoundation() {
        return requiredFoundation;
    }
    /**
     * Loads the requiredFoundation attribute with Foundation Modules.
     * @throws IOException When path is invalid.
     * @throws DataConversionException When there is an error converting from the JSON file.
     */
    public void setRequiredFoundation(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        Optional<ReadOnlyGradPad> gradPad = storage.readGradPad();
        requiredFoundation = gradPad.orElseThrow().getModuleList();
    }

    /**
     * Returns requiredITprof attribute of RequiredCommandStorage object.
     * @return requiredITprof attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredITprof() {
        return requiredITprof;
    }
    /**
     * Loads the requiredITprof attribute with IT Professionalism Modules.
     * @throws IOException When path is invalid.
     * @throws DataConversionException When there is an error converting from the JSON file.
     */
    public void setRequiredITprof(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        Optional<ReadOnlyGradPad> gradPad = storage.readGradPad();
        requiredITprof = gradPad.orElseThrow().getModuleList();
    }

    /**
     * Returns requiredMathAndScience attribute of RequiredCommandStorage object.
     * @return requiredMathAndScience attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredMathAndScience() {
        return requiredMathAndScience;
    }
    /**
     * Loads the requiredMathAndScience attribute with Math and Science Modules.
     * @throws IOException When path is invalid.
     * @throws DataConversionException When there is an error converting from the JSON file.
     */
    public void setRequiredMathAndScience(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        Optional<ReadOnlyGradPad> gradPad = storage.readGradPad();
        requiredMathAndScience = gradPad.orElseThrow().getModuleList();
    }

    /**
     * Returns requiredScience attribute of RequiredCommandStorage object.
     * @return requiredScience attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredScience() {
        return requiredScience;
    }
    /**
     * Loads the requiredScience attribute with Science Modules.
     * @throws IOException When path is invalid.
     * @throws DataConversionException When there is an error converting from the JSON file.
     */
    public void setRequiredScience(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        Optional<ReadOnlyGradPad> gradPad = storage.readGradPad();
        requiredScience = gradPad.orElseThrow().getModuleList();
    }

    /**
     * Returns requiredInternship attribute of RequiredCommandStorage object.
     * @return requiredInternship attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredInternship() {
        return requiredInternship;
    }
    /**
     * Loads the requiredInternship attribute with Internship Modules.
     * @throws IOException When path is invalid.
     * @throws DataConversionException When there is an error converting from the JSON file.
     */
    public void setRequiredInternship(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        Optional<ReadOnlyGradPad> gradPad = storage.readGradPad();
        requiredInternship = gradPad.orElseThrow().getModuleList();
    }
}

