package jlwcrews.flaggame;

//static container class for passing values between FXML controllers
public class GUI {

    private static String username;
    private static String difficulty;
    private static int score;
    private static int maxScore;
    private static String ipAddress;

    public static String getIpAddress() {
        return ipAddress;
    }

    public static void setIpAddress(String ipAddress) {
        GUI.ipAddress = ipAddress;
    }

    public static int getMaxScore() {
        return maxScore;
    }

    public static void setMaxScore(int maxScore) {
        GUI.maxScore = maxScore;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        GUI.difficulty = difficulty;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GUI.score = score;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        GUI.username = username;
    }

    public static void resetAll(){
        setScore(0);
        setMaxScore(0);
        setDifficulty(null);
        setUsername(null);
    }
}