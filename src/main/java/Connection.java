import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.*;


public class Connection {
    private static Connection instance = null;

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    // POST /start?problem=1
    public String start(String xAuthToken, int problemId) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        String response = null;
        String startParams = "?problem=" + problemId;

        try {
            URL url = new URL(GlobalData.BASE_URL + GlobalData.POST_START + startParams);

            // URL 연결
            conn = (HttpURLConnection) url.openConnection();

            // 요청 방식 선택 :  POST
            conn.setRequestMethod("POST");

            // Request Header 값 세팅 (key, value)
            conn.setRequestProperty("X-Auth-Token", xAuthToken);

            // 서버 Response Data 를 JSON 형식의 타입 으로 요청
            conn.setRequestProperty("Content-Type", "application/json");

            // 서버로 Request
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) { // 성공
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                // sb : {"auth_key":"e88ba78a-636a-44a1-90d3-86c35ccf0c94","problem":1,"time":0}
                responseJson = new JSONObject(sb.toString());

                // response : e88ba78a-636a-44a1-90d3-86c35ccf0c94
                response = responseJson.getString("auth_key");
            } else {
                response = String.valueOf(responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("not JSON Format response");
            e.printStackTrace();
        }
        return response;
    }

    // GET /locations
    public JSONObject locations(String authKey) {
        List<Location> locations = new ArrayList<Location>();

        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(GlobalData.BASE_URL + GlobalData.GET_LOCATIONS);

            // URL 연결
            conn = (HttpURLConnection) url.openConnection();
            // 요청 방식 선택 :  GET
            conn.setRequestMethod("GET");
            // Request Header 값 세팅 (key, value)
            conn.setRequestProperty("Authorization", authKey);
            // 서버 Response Data 를 JSON 형식의 타입 으로 요청
            conn.setRequestProperty("Content-Type", "application/json");

            // 서버로 Request
            int responseCode = conn.getResponseCode();
            if (responseCode == 401) {
                System.out.println("401:: Header가 잘못됨");
            } else if (responseCode == 500) {
                System.out.println("500:: 서버 에러");
            } else { // 성공
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("not JSON Format response");
            e.printStackTrace();
        }
        return responseJson;
    }


    // GET /trucks
    public JSONObject trucks(String authKey) {
        List<Truck> trucks = new ArrayList<Truck>();

        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(GlobalData.BASE_URL + GlobalData.GET_TRUCKS);

            // URL 연결
            conn = (HttpURLConnection) url.openConnection();
            // 요청 방식 선택 :  GET
            conn.setRequestMethod("GET");
            // Request Header 값 세팅 (key, value)
            conn.setRequestProperty("Authorization", authKey);
            // 서버 Response Data 를 JSON 형식의 타입 으로 요청
            conn.setRequestProperty("Content-Type", "application/json");

            // 서버로 Request
            int responseCode = conn.getResponseCode();
            if (responseCode == 401) {
                System.out.println("401:: Header가 잘못됨");
            } else if (responseCode == 500) {
                System.out.println("500:: 서버 에러");
            } else { // 성공
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("not JSON Format response");
            e.printStackTrace();
        }
        return responseJson;
    }

    // PUT /simulate
    public JSONObject simulate(String authKey, ArrayList<Command> commands) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        JSONObject obj = new JSONObject();

        try {
            URL url = new URL(GlobalData.BASE_URL + GlobalData.PUT_SIMULATE);

            // URL 연결
            conn = (HttpURLConnection) url.openConnection();
            // 요청 방식 선택 :  PUT
            conn.setRequestMethod("PUT");
            // Request Header 값 세팅 (key, value)
            conn.setRequestProperty("Authorization", authKey);

            // 서버 Response Data 를 JSON 형식의 타입 으로 요청
            conn.setRequestProperty("Content-Type", "application/json");

            JSONArray jsonArray = new JSONArray();  // commands
            for(int i=0; i<commands.size(); i++) {
                JSONObject command = new JSONObject();
                command.put("truck_id", commands.get(i).getTruckId());
                command.put("command", commands.get(i).getCommand());
            }
            obj.put("commands", jsonArray);

            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(obj.toString());
            bw.flush();
            bw.close();

            // 서버로 Request
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 성공
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("not JSON Format response");
            e.printStackTrace();
        }
        return responseJson;
    }

    // GET /score
    public float score(String authKey) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        float score = 0;

        try {
            URL url = new URL(GlobalData.BASE_URL + GlobalData.GET_SCORE);

            // URL 연결
            conn = (HttpURLConnection) url.openConnection();

            // 요청 방식 선택 :  GET
            conn.setRequestMethod("GET");

            // Request Header 값 세팅 (key, value)
            conn.setRequestProperty("Authorization", authKey);

            // 서버 Response Data 를 JSON 형식의 타입 으로 요청
            conn.setRequestProperty("Content-Type", "application/json");

            // 서버로 Request
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) { // 성공
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                responseJson = new JSONObject(sb.toString());
                score = responseJson.getFloat("score");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("not JSON Format response");
            e.printStackTrace();
        }
        return score;
    }
}
