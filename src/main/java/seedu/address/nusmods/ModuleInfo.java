package seedu.address.nusmods;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents jackson-friendly module information from NUSMods.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleInfo {
    private String moduleCode;
    private String title;
    private String moduleCredit;
    private String description;
    private String preclusion;
    private String prerequisite;


    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public String getModuleCredit() {
        return moduleCredit;
    }

    public String getDescription() {
        return description;
    }

    public String getPreclusion() {
        return preclusion;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    @Override
    public String toString() {
        return getModuleCode() + " " + getTitle() + " " + getModuleCredit();
    }
}
