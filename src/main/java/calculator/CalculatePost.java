package calculator;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CalculatePost {
    public static void main(String[] args) {
        try {
            // Beispiel für Multiplikation
            Numbers numbers = new Numbers(5.5, 3.2, "/"); // Zeichen für Operationen: "+", "-", "*", "/"

            // GSON Serialisierung
            Gson gson = new Gson();
            String json = gson.toJson(numbers);

            // HTTP-Verbindung
            URL url = new URL("http://127.0.0.1:8080/IMS/simpleSerialization.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // Daten senden
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.writeBytes("calculate=" + URLEncoder.encode(json, "UTF-8"));
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
            System.out.println(empfangenerString);

            // Deserialisieren
            Numbers responseNumbers = gson.fromJson(empfangenerString, Numbers.class);
            System.out.println("Ergebnis: " + responseNumbers.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}