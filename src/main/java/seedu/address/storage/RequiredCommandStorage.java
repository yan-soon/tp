package seedu.address.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.module.Module;

public class RequiredCommandStorage {
    private ObservableList<Module> requiredFoundation;
    private ObservableList<Module> requiredITprof;
    private ObservableList<Module> requiredMathAndScience;
    private ObservableList<Module> requiredScience;
    private ObservableList<Module> requiredInternship;

    /**
     * Makes use of classLoaders to convert the original file path
     * into one that can be readable during runtime.
     * @param fileName Original file path.
     * @return Converted runtime path.
     * @throws IOException When the provided fileName is invalid.
     */
    public String getFileNameFromResource(String fileName) throws IOException {
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

    /**
     * Converts a given JSON file via its runtime path, into a list of Modules.
     * @param filePath Converted runtime path.
     * @return List of modules taken from the JSON file via the runtime path.
     * @throws IOException When the filePath is invalid.
     * @throws IllegalValueException When the data from the JSON file violates some constraints.
     */
    public ObservableList<Module> getModulesFromJsonFile(String filePath) throws IOException, IllegalValueException {
        JsonSerializableGradPad jsonGradPad = JsonUtil.fromJsonString(filePath, JsonSerializableGradPad.class);
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
     * @throws IllegalValueException When the data from the JSON file violates some constraints.
     */
    public void setRequiredFoundation(String path) throws IOException, IllegalValueException {
        String fileName = getFileNameFromResource(path);
        requiredFoundation = getModulesFromJsonFile(fileName);
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
     * @throws IllegalValueException When the data from the JSON file violates some constraints.
     */
    public void setRequiredITprof(String path) throws IOException, IllegalValueException {
        String fileName = getFileNameFromResource(path);
        requiredITprof = getModulesFromJsonFile(fileName);
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
     * @throws IllegalValueException When the data from the JSON file violates some constraints.
     */
    public void setRequiredMathAndScience(String path) throws IOException, IllegalValueException {
        String fileName = getFileNameFromResource(path);
        requiredMathAndScience = getModulesFromJsonFile(fileName);
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
     * @throws IllegalValueException When the data from the JSON file violates some constraints.
     */
    public void setRequiredScience(String path) throws IOException, IllegalValueException {
        String fileName = getFileNameFromResource(path);
        requiredScience = getModulesFromJsonFile(fileName);
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
     * @throws IllegalValueException When the data from the JSON file violates some constraints.
     */
    public void setRequiredInternship(String path) throws IOException, IllegalValueException {
        String fileName = getFileNameFromResource(path);
        requiredInternship = getModulesFromJsonFile(fileName);
    }
}

