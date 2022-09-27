package capers;

import java.io.File;

/**
 * A repository for Capers
 *
 * @author Jetiame
 * The structure of a Capers Repository is as follows:
 * <p>
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 * - dogs/ -- folder containing all of the persistent data for dogs
 * - story -- file containing the current story
 * <p>
 */
public class CapersRepository {
    /**
     * Current Working Directory.
     */
    static final File CWD = new File(System.getProperty("user.dir"));
//    static final File CWD = new File("/Users/liu/Desktop/Study/CS61B/lab6");

    /**
     * Main metadata folder.
     */
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers");

    /**
     * Story stored folder.
     */
    static final File STORY_FOLDER = Utils.join(CAPERS_FOLDER, "story");

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     * <p>
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     * - dogs/ -- folder containing all of the persistent data for dogs
     * - story -- file containing the current story
     */
    public static void setupPersistence() {
        if (!Dog.DOG_FOLDER.exists()) {
            Dog.DOG_FOLDER.mkdirs();
        }
        if (!STORY_FOLDER.exists()) {
            STORY_FOLDER.mkdirs();
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     *
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        Utils.writeContents(STORY_FOLDER, text);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        new Dog(name, breed, age).saveDog();
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     *
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog.fromFile(name).haveBirthday();
    }
}