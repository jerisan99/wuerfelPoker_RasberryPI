package post;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.Gson;

public class Post {
    public static void main(String[] args) {
        try {
            /* Person Beispiel */ 
            Person person = new Person();
            person.setId(1);
            person.setName("Max Mustermann");

            /*/ GSON */
            Gson gson = new Gson();
            String json = gson.toJson(person);

            /* Verbindung */

            // HTTP-Verbindung aufbauen
            URL url = new URL("http://127.0.0.1:8080/IMS/simpleSerialization.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // Daten senden
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.writeBytes("msg=" + URLEncoder.encode(json, "UTF-8"));
                out.flush();
            }

            // Antwort lesen
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
            con.disconnect();

            // Antwort ausgeben
            String empfangenerString = response.toString();
            System.out.println("empfangenerString: " + empfangenerString);

            // Deserialisieren
            Person responsePerson = gson.fromJson(empfangenerString, Person.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
