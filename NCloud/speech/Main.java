import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

	
	//Application Name: multicam27
    public static void main(String[] args) {
        String clientId = "upgw1ry1ag";             // Application Client ID";
        String clientSecret = "v090IllWVNDYNMkmIXpw6TVQFSQiQEHoc89Jf7PM";     // Application Client Secret";

        try {
            String imgFile = "1.mp3";
            File voiceFile = new File(imgFile);

            String language = "Kor";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;



            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            
            System.out.println("Start");
            
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[40969];
            int bytesRead = -1;
            
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if(br != null) {
                StringBuffer response = new StringBuffer();
                
                 while ((inputLine = br.readLine()) != null) {
                	
                    response.append(inputLine);
                }
                
                br.close();
                
                String res = response.toString();
                
                System.out.println(res);
                
                
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println("End");
        
    }
    
    
    
}

