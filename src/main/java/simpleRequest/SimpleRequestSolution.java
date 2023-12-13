package simpleRequest;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;*/

public class SimpleRequestSolution {
    public static void main(String[] args) {

        URL url;
        HttpURLConnection con;
        try {
            url = new URL("http://127.0.0.1:8080/IMS/simpleGetResponse.php");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            /*
             * send a parameter
             */

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            String message = "name=Andreas&age=46&city=Buchs";
            out.writeBytes(message);
            out.flush();
            out.close();

            int status = con.getResponseCode();
            System.out.println("Status:" + status);
            String contentType = con.getHeaderField("Content-Type");
            System.out.println("contentType:" + contentType);

            /*
             * read the return
             */

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }

                System.out.println(content);
            }

            con.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(SimpleRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimpleRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}