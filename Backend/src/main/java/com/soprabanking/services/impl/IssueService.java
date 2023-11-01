package com.soprabanking.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.soprabanking.services.IIssueService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IssueService implements IIssueService {
    private List<Map<String, Object>> dataList;
    private String apiUrl = "https://innersource.soprasteria.com/api/v4/projects/";

    @Override
    public List<Map<String, Object>> getData(String project_id, String accessToken) throws JsonProcessingException {
        int issue_count = 0;
        int opened_issue_count = 0;
        int closed_issue_count = 0;
        String Issue = null;
        HashMap<String, Integer> labels = new HashMap<>();
        ArrayList<String> labels_array = new ArrayList<>();
        ArrayList<String> Alabel = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("private-token", accessToken);
        String issue_str = "";
        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            String issues_url = apiUrl + project_id + "/issues";
            UriComponentsBuilder issue_builder = UriComponentsBuilder.fromHttpUrl(issues_url);
            ResponseEntity<String> issue_response = restTemplate.exchange(issue_builder.toUriString(), HttpMethod.GET, entity, String.class);
            issue_str = issue_response.getBody();
            String issues = issue_str;
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> projects = objectMapper.readValue(issues, new TypeReference<>() {
            });
            for (Map<String, Object> project : projects) {
                issue_count++;
                if (project.get("state").toString().equals("opened")) {
                    opened_issue_count++;
                } else {
                    closed_issue_count++;
                }
                labels_array = (ArrayList<String>) project.get("labels");
                for (String label : labels_array) {
                    Alabel.add(label);
                }
            }
            for (String l : Alabel) {
                labels.put(l, labels.getOrDefault(l, 0) + 1);
            }
            String innerjson = new Gson().toJson(labels);
            Issue = "[{\"title\":" + "\"Issues\"" + ",\"total_count\":" + issue_count + ",\"opened\":" + opened_issue_count + ",\"closed\":" + closed_issue_count + ",\"labels\":" + innerjson + "}]";
        } catch (Exception e) {
            e.printStackTrace();
            Issue = "[{\"Error\":" + "\"Something Went Wrong\"" + "}]";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        dataList = objectMapper.readValue(Issue, new TypeReference<List<Map<String, Object>>>() {
        });
        return dataList;

    }
}

