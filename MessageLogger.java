import java.util.ArrayList;
import java.util.List;

public class MessageLogger {
    private static List<String>messages = new ArrayList<>();

    public static void logMessage(String message){
        messages.add(message);
    }

    public static List<String>getMessages(){
        return messages;
    }
}