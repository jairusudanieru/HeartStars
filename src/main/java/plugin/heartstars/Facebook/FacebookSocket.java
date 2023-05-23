package plugin.heartstars.Facebook;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import plugin.heartstars.Configuration.CenterChatMessage;
import plugin.heartstars.Events.PlayerSentStars;

import java.net.URISyntaxException;

public class FacebookSocket {

    PlayerSentStars sentStars = new PlayerSentStars();

    public void onFacebookEvent(JavaPlugin plugin) {
        try {
            final String socketToken = plugin.getConfig().getString("socketToken");
            Socket socket = createSocket(socketToken);
            socket.on("event", args -> handleEvent((JSONObject) args[0], plugin));
            socket.connect();
            Bukkit.getLogger().info("Socket Connected!");
        } catch (Exception error) {
            Bukkit.getLogger().severe("Something went wrong on FacebookEvent!");
        }
    }

    private Socket createSocket(String socketToken) throws URISyntaxException {
        String socketUrl = "https://sockets.streamlabs.com?token=" + socketToken;
        return IO.socket(socketUrl);
    }

    private void handleEvent(JSONObject streamData, JavaPlugin plugin) {
        String playerName = plugin.getConfig().getString("streamerName");
        String starsAmount = plugin.getConfig().getString("starsAmount");
        if (!streamData.has("for") && streamData.getString("type").equals("donation")) {
            Bukkit.getLogger().info(streamData.getString("message"));
        }
        if (streamData.has("for") && streamData.getString("for").equals("facebook_account")) {
            JSONArray messageArray = streamData.getJSONArray("message");
            String name = "user", amount = "0", comment = "null";
            for (int i = 0; i < messageArray.length(); i++) {
                JSONObject messageObj = messageArray.getJSONObject(i);
                name = getStringOrDefault(messageObj, "name", name);
                //amount = getStringOrDefault(messageObj, "amount", amount);
                //comment = getStringOrDefault(messageObj, "comment", comment);
                amount = starsAmount;
                comment = "addhearts " + playerName;
            }
            switch (streamData.getString("type")) {
                case "follow":
                    //System.out.println(streamData);
                    //do something here
                    break;
                case "like":
                    //System.out.println(streamData);
                    //do something here
                    break;
                case "share":
                    //System.out.println(streamData);
                    //do something here
                    break;
                case "stars":
                    String message = "§c§l" + name + "§r has sent §e§l" + amount + " stars!§r";
                    sentStars.onPlayerSentStars(amount, comment, plugin);
                    CenterChatMessage.sendCenteredMessage(message);
                    break;
                case "support":
                    //System.out.println(streamData);
                    //do something here
                    break;
            }
        }
    }

    private String getStringOrDefault(JSONObject jsonObject, String key, String defaultValue) {
        try {
            return jsonObject.getString(key);
        } catch (Exception ignored) {
            return defaultValue;
        }
    }

}
