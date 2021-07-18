package fr.quentin.cleanclaims;

public class Configuration {

    public static Long MIN_SECONDS_WITH_LAST_JOIN = Cleanclaims.getInstance().getConfig().getLong("criteria.min-seconds-with-last-join");
    public static Integer MAX_NUMBER_OF_CHUNKS = Cleanclaims.getInstance().getConfig().getInt("criteria.max-number-of-chunks");
    public static boolean SEND_BANK_BALANCE_AND_CASHBACK_TO_OWNER = Cleanclaims.getInstance().getConfig().getBoolean("criteria.send-bank-balance-and-cashback-to-owner-when-land-deleted");
    public static String LOG_FORMAT = Cleanclaims.getInstance().getConfig().getString("criteria.log-format");
    public static Boolean LOG_SENT_TO_PLAYER_THAT_STARTED_CLEANING = Cleanclaims.getInstance().getConfig().getBoolean("criteria.log-sent-to-player-that-started-cleaning");
    public static Boolean LOG_SENT_TO_CONSOLE = Cleanclaims.getInstance().getConfig().getBoolean("criteria.log-sent-to-console");

    public static String[] COMMAND_RETRIEVE_QUEUE = {"retrieve-queue", "Iterate over all lands to know which one should be removed and add them in a queue"};
    public static String[] COMMAND_CHECK_QUEUE = {"check-queue", String.format("Once %s has been done, display the list of all lands that are in the queue", COMMAND_RETRIEVE_QUEUE[0])};
    public static String[] COMMAND_CLEAN_QUEUE = {"clean-queue", String.format("Once %s has been done, delete all the lands in the queue", COMMAND_RETRIEVE_QUEUE[0])};
    public static String[] COMMAND_RETRIEVE_AND_CLEAN_QUEUE = {"retrieve-queue-and-clean", String.format("Command %s and %s in one", COMMAND_RETRIEVE_QUEUE[0], COMMAND_CLEAN_QUEUE[0])};

    public static String MESSAGE_START_RETRIEVING = "Retrieving claims that must be remove";
    public static String MESSAGE_NUMBER_OF_CLAIMS_TO_REMOVE_FOUND = String.format("%s has been found. Use %s to see the name of all lands that are in queue. Use %s to clean the queue.", "%s", COMMAND_CHECK_QUEUE[0], COMMAND_CLEAN_QUEUE[0]);
    public static String MESSAGE_QUEUE_EMPTY = String.format("Queue is empty. Use %s to fill the queue", COMMAND_RETRIEVE_QUEUE[0]);
    public static final String MESSAGE_QUEUE_CLEANED = "Queue has been cleaned.";

}
