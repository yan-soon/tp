package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.GradPad;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.storage.JsonGradPadStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static java.util.Objects.compare;
import static java.util.Objects.requireNonNull;

public class RequiredCommand extends Command {
    public static final String COMMAND_WORD = "required";
    public static final String MESSAGE_FOUNDATION = "These are the Foundation Modules you have yet to take: ";
    public static final String MESSAGE_FAILURE_FOUNDATION = "There was an error loading the required modules :(";
    public static final String MESSAGE_FAILURE_SCI = "There was an error loading the Science modules :(";
    public static final String MESSAGE_SCIENCE = "You have not completed your Science Module requirement,"
            + " use the 'science' command to view the available modules.";
    public static final String MESSAGE_FAILURE_INTERN = "There was an error loading the Internship Modules :(";
    public static final String MESSAGE_INTERN_1 = "You have yet to complete your 12MCs worth of Internship Modules.";
    public static final String MESSAGE_INTERN_2 = "These are the Internship Modules you can take:";
    public static final String MESSAGE_SUCCESS_FOUNDATION = "You have completed all your Foundation Modules!";
    public static final String MESSAGE_SUCCESS_SCIENCE = "You have completed your Science Module Requirement!";
    public static final String MESSAGE_SUCCESS_INTERN = "You have completed your Internship Module Requirement!";

    private ObservableList<Module> modules;
    private String leftOverModules = "";
    private Optional<ReadOnlyGradPad> foundationStorage;
    private ObservableList<Module> requiredFoundation;
    private Optional<ReadOnlyGradPad> scienceStorage;
    private ObservableList<Module> requiredScience;
    private Optional<ReadOnlyGradPad> internshipStorage;
    private ObservableList<Module> requiredInternship;
    
    public void setStorage() throws IOException, DataConversionException {
        Path path = Paths.get("data", "requiredmodules.json");
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        this.foundationStorage = storage.readGradPad();
    }
    
    public void compareFoundation() {
        boolean foundationClear = true;
        String modulesToAdd = "";
        for (Module module : requiredFoundation) {
            boolean add = true;
            for (Module mod : modules) {
                if (module.isSameModule(mod)) {
                    add = false;
                    break;
                }
            } if (add) {
                String moduleToAdd = module.getModuleCode().toString();
                modulesToAdd += "\n" + moduleToAdd;
                foundationClear = false;
            }
        } if (foundationClear) {
            leftOverModules += MESSAGE_SUCCESS_FOUNDATION + "\n";
        } else {
            leftOverModules += MESSAGE_FOUNDATION + modulesToAdd + "\n";
        }
        leftOverModules += "\n";
    }

    public void setScienceStorage() throws IOException, DataConversionException {
        Path path = Paths.get("data", "sciencemodules.json");
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        scienceStorage = storage.readGradPad();
    }
    
    public void compareScience() {
        boolean add = true;
        for (Module module : requiredScience) {
            for (Module mod : modules) {
                if (module.isSameModule(mod)) {
                    add = false;
                    break;
                }
            } if (!add) {
                break;
            }
        } if (add) {
            leftOverModules += MESSAGE_SCIENCE + "\n";
        } else {
            leftOverModules += MESSAGE_SUCCESS_SCIENCE + "\n";
        }
        leftOverModules += "\n";
    }

    public void setInternshipStorage() throws IOException, DataConversionException {
        Path path = Paths.get("data", "industrialexperience.json");
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        internshipStorage = storage.readGradPad();
    }
    
    public void compareInternship() {
        int modularScore = 0;
        String leftOverInternship = "";
        for (Module module : requiredInternship) {
            boolean add = true;
            for (Module mod : modules) {
                if (module.isSameModule(mod)) {
                    int modularCredits = Integer.parseInt(mod.getModularCredits().toString());
                    modularScore += modularCredits;
                    add = false;
                    break;
                }
            } if (add) {
                String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + ")";
                leftOverInternship += "\n" + moduleToAdd;
            }
        } if (modularScore < 12) {
            String modScore = " You are currently at " + modularScore + " MCs. ";
            leftOverModules += "\n" + MESSAGE_INTERN_1 + modScore + MESSAGE_INTERN_2 + leftOverInternship;
        } else {
            leftOverModules += MESSAGE_SUCCESS_INTERN;
        }
    }
    
    @Override
    public CommandResult execute(Model model) throws IOException, DataConversionException {
        requireNonNull(model);
        modules = model.getGradPad().getModuleList();
        setStorage();
        setScienceStorage();
        setInternshipStorage();
        if (foundationStorage.isPresent()) {
            requiredFoundation = foundationStorage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE_FOUNDATION);
        }
        compareFoundation();
        if (scienceStorage.isPresent()) {
            requiredScience = scienceStorage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE_SCI);
        }
        compareScience();
        if (internshipStorage.isPresent()) {
            requiredInternship = internshipStorage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE_INTERN);
        }
        compareInternship();
        return new CommandResult(leftOverModules);
    }
}
