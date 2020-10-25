package seedu.address.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;

public class RequiredCommandStorage {
    private ObservableList<Module> requiredFoundation;
    private ObservableList<Module> requiredITprof;
    private ObservableList<Module> requiredMathAndScience;
    private ObservableList<Module> requiredScience;
    private ObservableList<Module> requiredInternship;

    public String helper(String fileName) throws IOException {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException();
        } else {
            StringBuilder dataString = new StringBuilder();

            Reader reader = new BufferedReader(new InputStreamReader(inputStream,
                    Charset.forName(StandardCharsets.UTF_8.name())));
            int c = 0;
            while ((c = reader.read()) != -1) {
                dataString.append((char) c);
            }

            return dataString.toString();
        }
    }

    public ObservableList<Module> helper2(String filePath) throws IOException, IllegalValueException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<JsonSerializableGradPad> targetType = new TypeReference<>(){};
        JsonSerializableGradPad jsonGradPad = mapper.readValue(filePath, targetType);
        return jsonGradPad.toModelType().getModuleList();
    }
    
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
    public void setRequiredFoundation(String path) throws IOException, IllegalValueException {
       String filePath = helper(path);
       requiredFoundation = helper2(filePath);
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

