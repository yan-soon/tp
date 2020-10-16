package seedu.address.nusmods;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents jackson-friendly module summaries from NUSMods.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleSummary {
    private String moduleCode;
    private String title;
    private List<Integer> semesters;

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getSemesters() {
        return semesters;
    }

    @Override
    public String toString() {
        return getModuleCode() + " " + getTitle() + " " + getSemesters();
    }
}
