package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonGradPadStorage;

public class RequiredCommand extends Command {
    public static final String COMMAND_WORD = "required";
    public static final String MESSAGE_FOUNDATION = "These are the Foundation Modules you have yet to take: ";
    public static final String MESSAGE_FAILURE_FOUNDATION = "There was an error loading the Foundation Modules :(";
    public static final String MESSAGE_FAILURE_SCI = "There was an error loading the Science Modules :(";
    public static final String MESSAGE_SCIENCE = "You have not completed your Science Module requirement,"
            + " use the 'science' command to view the available modules.";
    public static final String MESSAGE_FAILURE_INTERN = "There was an error loading the Internship Modules :(";
    public static final String MESSAGE_INTERN_1 = "You have yet to complete your 12MCs worth of Internship Modules.";
    public static final String MESSAGE_INTERN_2 = "These are the Internship Modules you can take:";
    public static final String MESSAGE_SUCCESS_FOUNDATION = "You have completed all your Foundation Modules!";
    public static final String MESSAGE_SUCCESS_SCIENCE = "You have completed your Science Module Requirement!";
    public static final String MESSAGE_SUCCESS_INTERN = "You have completed your Internship Module Requirement!";
    public static final String MESSAGE_ITPROF = "These are the IT Professionalism Modules you have yet to take:";
    public static final String MESSAGE_SUCCESS_ITPROF = "You have completed your "
            + "IT Professionalism Module Requirement!";
    public static final String MESSAGE_MATHANDSCI = "These are the Math and Science Modules you have yet to take:";
    public static final String MESSAGE_SUCCESS_MATHANDSCI = "You have completed your "
            + "Math and Science Module Requirement!";
    public static final String MESSAGE_FAILURE_ITPROF = "There was an error loading the IT Professionalism Modules :(";
    public static final String MESSAGE_FAILURE_MATHANDSCI = "There was an error loading"
            + " the Math and Science Modules :(";
    public static final Path FOUNDATION_PATH = Paths.get("src/main/data/foundationmodules.json");
    public static final Path INTERNSHIP_PATH = Paths.get("src/main/data/industrialexperience.json");
    public static final Path ITPROF_PATH = Paths.get("src/main/data/ITProfessionalism.json");
    public static final Path MATHANDSCI_PATH = Paths.get("src/main/data/mathandsciencemodules.json");
    public static final Path SCIENCE_PATH = Paths.get("src/main/data/sciencemodules.json");

    private ObservableList<Module> modules;
    private String leftOverModules = "";
    private Optional<ReadOnlyGradPad> foundationStorage;
    private ObservableList<Module> requiredFoundation;
    private Optional<ReadOnlyGradPad> scienceStorage;
    private ObservableList<Module> requiredScience;
    private Optional<ReadOnlyGradPad> internshipStorage;
    private ObservableList<Module> requiredInternship;
    private Optional<ReadOnlyGradPad> itProfStorage;
    private ObservableList<Module> requiredITprof;
    private Optional<ReadOnlyGradPad> mathAndScienceStorage;
    private ObservableList<Module> requiredMathAndScience;

    /**
     * Returns foundationStorage attribute of RequiredCommand object.
     * @return foundationStorage attribute of type Optional<ReadOnlyGradPad/>.
     */
    public Optional<ReadOnlyGradPad> getFoundationStorage() {
        return foundationStorage;
    }
    /**
     * Loads the foundationStorage attribute with Foundation Modules.
     * @throws IOException
     * @throws DataConversionException
     */
    public void setFoundationStorage(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        this.foundationStorage = storage.readGradPad();
    }

    /**
     * Cross references the user's current list of Modules and marks out
     * any undone Foundation Modules.
     */
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
                String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
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

    /**
     * Returns ITProfStorage attribute of RequiredCommand object.
     * @return ITProfStorage attribute of type Optional<ReadOnlyGradPad/>.
     */
    public Optional<ReadOnlyGradPad> getITprofStorage() {
        return itProfStorage;
    }
    /**
     * Loads the ITprofStorage attribute with IT Professionalism Modules.
     * @throws IOException
     * @throws DataConversionException
     */
    public void setITprofStorage(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        itProfStorage = storage.readGradPad();
    }

    /**
     * Cross references the user's current list of Modules and marks out
     * any undone IT Professionalism Modules.
     */
    public void compareITprof() {
        boolean itProfClear = true;
        String modulesToAdd = "";
        for (Module module : requiredITprof) {
            boolean add = true;
            for (Module mod : modules) {
                if (module.isSameModule(mod)) {
                    add = false;
                    break;
                }
            } if (add) {
                String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
                modulesToAdd += "\n" + moduleToAdd;
                itProfClear = false;
            }
        } if (itProfClear) {
            leftOverModules += MESSAGE_SUCCESS_ITPROF + "\n";
        } else {
            leftOverModules += MESSAGE_ITPROF + modulesToAdd + "\n";
        }
        leftOverModules += "\n";
    }

    /**
     * Returns mathAndScienceStorage attribute of RequiredCommand object.
     * @return mathAndScienceStorage attribute of type Optional<ReadOnlyGradPad/>.
     */
    public Optional<ReadOnlyGradPad> getMathAndScienceStorage() {
        return mathAndScienceStorage;
    }

    /**
     * Loads the mathAndScienceStorage attribute with Math and Science Modules.
     * @throws IOException
     * @throws DataConversionException
     */
    public void setMathAndScienceStorage(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        this.mathAndScienceStorage = storage.readGradPad();
    }

    /**
     * Cross references the user's current list of Modules and marks out
     * any undone Math and Science Modules.
     */
    public void compareMathAndScience() {
        boolean mathAndScienceClear = true;
        String modulesToAdd = "";
        for (Module module : requiredMathAndScience) {
            boolean add = true;
            for (Module mod : modules) {
                if (module.isSameModule(mod)) {
                    add = false;
                    break;
                }
            } if (add) {
                String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
                modulesToAdd += "\n" + moduleToAdd;
                mathAndScienceClear = false;
            }
        } if (mathAndScienceClear) {
            leftOverModules += MESSAGE_SUCCESS_MATHANDSCI + "\n";
        } else {
            leftOverModules += MESSAGE_MATHANDSCI + modulesToAdd + "\n";
        }
        leftOverModules += "\n";
    }

    /**
     * Returns scienceStorage attribute of RequiredCommand object.
     * @return scienceStorage attribute of type Optional<ReadOnlyGradPad/>.
     */
    public Optional<ReadOnlyGradPad> getScienceStorage() {
        return scienceStorage;
    }

    /**
     * Loads the scienceStorage attribute with Science Modules.
     * @throws IOException
     * @throws DataConversionException
     */
    public void setScienceStorage(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        scienceStorage = storage.readGradPad();
    }

    /**
     * Cross references the user's current list of Modules and marks out
     * any undone Science Modules.
     */
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

    /**
     * Returns internshipStorage attribute of RequiredCommand object.
     * @return internshipStorage attribute of type Optional<ReadOnlyGradPad/>.
     */
    public Optional<ReadOnlyGradPad> getInternshipStorage() {
        return internshipStorage;
    }

    /**
     * Loads the internshipStorage attribute with Internship Modules.
     * @throws IOException
     * @throws DataConversionException
     */
    public void setInternshipStorage(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        internshipStorage = storage.readGradPad();
    }

    /**
     * Cross references the user's current list of Modules and marks out
     * any undone Internship Modules. Also calculates current MC score
     * achieved from Internship Modules.
     */
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
                String moduleToAdd = module.getModuleCode() + " (" + module.getModularCredits() + " MCs)";
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
        setFoundationStorage(FOUNDATION_PATH);
        setITprofStorage(ITPROF_PATH);
        setMathAndScienceStorage(MATHANDSCI_PATH);
        setScienceStorage(SCIENCE_PATH);
        setInternshipStorage(INTERNSHIP_PATH);
        if (foundationStorage.isPresent()) {
            requiredFoundation = foundationStorage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE_FOUNDATION);
        }
        compareFoundation();
        if (itProfStorage.isPresent()) {
            requiredITprof = itProfStorage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE_ITPROF);
        }
        compareITprof();
        if (mathAndScienceStorage.isPresent()) {
            requiredMathAndScience = mathAndScienceStorage.get().getModuleList();
        } else {
            return new CommandResult(MESSAGE_FAILURE_MATHANDSCI);
        }
        compareMathAndScience();
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
