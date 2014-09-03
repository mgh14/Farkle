package properties;

import java.io.File;
import java.util.HashMap;

import core.DieValueGenerator;

public class PropertiesManager {

  //--------------------------------------------------------------------------
  // begin member variables

  private static final String CONFIG_FILENAME_PROP_NAME = "configFile";
  private static final String NUM_PLAYERS_PROP_NAME = "NUM_PLAYERS";
  private static final String MIN_DIE_VAL_PROP_NAME = "MIN_DIE_VAL";
  private static final String MAX_DIE_VAL_PROP_NAME = "MAX_DIE_VAL";
  private static final String MIN_NUM_DICE_VAL_PROP_NAME = "MIN_NUM_DICE";
  private static final String NUM_DICE_PROP_NAME = "NUM_DICE";
  private static final String MIN_SCORE_PROP_NAME = "MIN_SCORE";
  private static final String REQ_POINTS_FOR_WIN_PROP_NAME = "REQ_POINTS_FOR_WIN";

  public static final int DEFAULT_NUM_PLAYERS = 2;
  public static final int DEFAULT_MIN_DIE_VALUE = 1;
  public static final int DEFAULT_MAX_DIE_VALUE = 6;
  public static final int DEFAULT_MIN_NUM_DICE = 1;
  public static final int DEFAULT_NUM_DICE = 6;
  public static final int DEFAULT_MIN_SCORE = 0;
  public static final int DEFAULT_REQ_POINTS_FOR_WIN = 10000;
  public static final int DEFAULT_REQ_POINTS_TO_GET_ANY_POINTS = 500;

  private static HashMap<String, String> properties;

  static {
    properties = new HashMap<String, String>();
    loadDefaultConfig();
  }

  private static DieValueGenerator generator = new DieValueGenerator();
  private static XMLHelper xmlHelper = new XMLHelper();

  public static final String FARKLE_ROOT_DIR = new File("").getAbsolutePath() + "/";

  //-------------------------------------------------------------------------
  // Begin public methods

  public static void setGenerator(DieValueGenerator generator) {
    PropertiesManager.generator = generator;
  }

  public static void setXMLHelper(XMLHelper xmlHelper) {
    PropertiesManager.xmlHelper = xmlHelper;
  }

  public static void loadDefaultConfig() {
    properties.put(NUM_PLAYERS_PROP_NAME, Integer.toString(DEFAULT_NUM_PLAYERS));
    properties.put(MIN_DIE_VAL_PROP_NAME, Integer.toString(DEFAULT_MIN_DIE_VALUE));
    properties.put(MAX_DIE_VAL_PROP_NAME, Integer.toString(DEFAULT_MAX_DIE_VALUE));
    properties.put(MIN_NUM_DICE_VAL_PROP_NAME, Integer.toString(DEFAULT_MIN_NUM_DICE));
    properties.put(NUM_DICE_PROP_NAME, Integer.toString(DEFAULT_NUM_DICE));
    properties.put(MIN_SCORE_PROP_NAME, Integer.toString(DEFAULT_MIN_SCORE));
    properties.put(REQ_POINTS_FOR_WIN_PROP_NAME, Integer.toString(DEFAULT_REQ_POINTS_FOR_WIN));
  }

  public static String translateFilepathToAbsoluteFilepath(String filename) {
    if (filename.contains(FARKLE_ROOT_DIR)) {
      return filename;
    }

    return FARKLE_ROOT_DIR + filename;
  }

  public static boolean loadSettingsProfile(String configFile) throws RuntimeException {
        /*String configFileError = "There was an error loading profile " + configFile;
        Document document = xmlHelper.parseProfile(translateFilepathToAbsoluteFilepath(configFile));
        if(document == null) {
            throw new RuntimeException(configFileError);
        }

        // if doc is parsed, then reset properties map (but not until)
        resetPropertiesMap();

        // iterate through xml doc
        properties = xmlHelper.getSettingsFromDocument(document);
        if(properties == null) {
            throw new RuntimeException(configFileError);
        }

        // verify min/max die values are valid
        generator.verifyMinAndMaxValid(getProperty(MIN_DIE_VAL_PROP_NAME), getProperty(MAX_DIE_VAL_PROP_NAME));

        // verify number of dice is valid
        generator.verifyNumDiceIsValid(getProperty(NUM_DICE_PROP_NAME));

        properties.put(CONFIG_FILENAME_PROP_NAME, configFile);*/

    return false;
  }

  public static int getProperty(String propertyName) {
    if (!properties.containsKey(propertyName)) {
      throw new IllegalArgumentException("Property name " + propertyName + " does not exist");
    }

    return Integer.parseInt(properties.get(propertyName));
  }

  public static int getNumPlayers() {
    return getProperty(NUM_PLAYERS_PROP_NAME);
  }

  public static int getMinDieValue() {
    return getProperty(MIN_DIE_VAL_PROP_NAME);
  }

  public static int getMaxDieValue() {
    return getProperty(MAX_DIE_VAL_PROP_NAME);
  }

  public static int getNumDice() {
    return getProperty(NUM_DICE_PROP_NAME);
  }

  public static int getMinScore() {
    return getProperty(MIN_SCORE_PROP_NAME);
  }

  public static int getPointsReqForWin() {
    return getProperty(REQ_POINTS_FOR_WIN_PROP_NAME);
  }

  public static void verifyDieValueIsValid(int value) {
    generator.verifyDieValueIsValid(getMinDieValue(), getMaxDieValue(), value);
  }

  //-------------------------------------------------------------------------------
  // Begin private methods

  private static void resetPropertiesMap() {
    properties = new HashMap<String, String>();
  }

}
