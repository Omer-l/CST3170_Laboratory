package lab14;

public class ChatBot {
    private String response;
    private final String query1 = "i'll have a beer";
    private final String query2 = "wine please";
    private final String query3 = "where's the loo";
    private final String query4 = "can i have a burger";

    public void processQuery(String query) {
        query = query.toLowerCase();

        if(query.contains(query1)) {
            response = "One beer coming right up.";
        } else if(query.contains(query2)) {
            response = "Here's your wine.";
        } else if(query.contains(query3)) {
            response = "Go straight down the corridor and to the first left.";
        } else if(query.contains(query4)) {
            response = "No beef burger for you.";
        } else {
            response = "Sorry, could you say that again.";
        }
        System.out.println(this);
    }
    @Override
    public String toString() {
        return "BOT > " + response;
    }
}
