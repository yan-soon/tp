package seedu.address.nusmods;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seedu.address.commons.util.FileUtil;
import seedu.address.nusmods.exceptions.NusmodsException;

/**
 * Encapsulates operations to retrieve NUSMods module information.
 */
public class NusmodsDataManager implements NusmodsData {
    private final String filePath;
    private final DataFetcher dataFetcher;

    NusmodsDataManager() {
        dataFetcher = new DataFetcherManager();
        filePath = DataFetcher.DATA_FILE_PATH;
    }

    /**
     * Constructor to change the DataFetcher class used. Mainly used for testing to provide stubs.
     *
     * @param dataFetcher The class used to invoke static methods in this class.
     */
    NusmodsDataManager(DataFetcher dataFetcher, String filePath) {
        this.dataFetcher = dataFetcher;
        this.filePath = filePath;
    }


    /**
     * Returns the module title of a module given its module code.
     *
     * @param moduleCode The module code to search for.
     * @return The module title or {@code Optional.empty()} if not found.
     * @throws NusmodsException if an error occurs while retrieving module info.
     */
    public Optional<String> getModuleTitle(String moduleCode) throws NusmodsException {
        return getModuleInfo(moduleCode).map(ModuleInfo::getTitle);
    }

    /**
     * Returns the {@code ModuleInfo} of a module given its module code.
     * This method attempts to fetch the latest info from the NUSMods API if possible. Otherwise, it will
     * still return the info stored locally.
     *
     * @param moduleCode The module code to search for.
     * @return The {@code ModuleInfo} or {@code Optional.empty()} if not found.
     * @throws NusmodsException if an error occurs while retrieving module info.
     */
    public Optional<ModuleInfo> getModuleInfo(String moduleCode) throws NusmodsException {
        try {
            return dataFetcher.fetchModuleInfo(moduleCode);
        } catch (NusmodsException ex) {
            return getModuleInfoFromFile(moduleCode);
        }
    }

    /**
     * Returns the {@code ModuleInfo} for a given module code by retrieving it locally.
     *
     * @param moduleCode The module coed to search for.
     * @return The {@code ModuleInfo} or {@code Optional.empty()} if not found.
     * @throws NusmodsException if an error occurs while retrieving module info.
     */
    private Optional<ModuleInfo> getModuleInfoFromFile(String moduleCode) throws NusmodsException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = FileUtil.readFromFile(Paths.get(getFilePath()));
            TypeReference<HashMap<String, ModuleInfo>> targetType = new TypeReference<>(){};
            Map<String, ModuleInfo> moduleInfoMap = mapper.readValue(jsonString, targetType);

            return Optional.ofNullable(moduleInfoMap.get(moduleCode));
        } catch (IOException ex) {
            throw new NusmodsException(new IOException("Error reading module info from local file."));
        }
    }

    public String getFilePath() {
        return filePath;
    }
}
