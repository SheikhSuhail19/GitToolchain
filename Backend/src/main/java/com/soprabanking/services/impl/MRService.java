package com.soprabanking.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.services.ICardServiceMergeRequests;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.Map;

@Service
public class MRService implements ICardServiceMergeRequests {
    private List<Map<String, Object>> dataList;
    private String apiUrl="https://innersource.soprasteria.com/api/v4/projects/";
    @Override
    public List<Map<String, Object>> getMR(String project_id, String apiToken) throws JsonProcessingException {
        int MR_count=0;
        int opened_MR_count=0;
        int merged_MR_count=0;
        int closed_MR_count=0;
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("private-token", apiToken);
        String MR_str = "";
        String Merge=null;
        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            String MR_url=apiUrl+project_id+"/merge_requests";
            UriComponentsBuilder MR_builder = UriComponentsBuilder.fromHttpUrl(MR_url);
            ResponseEntity<String> MR_response = restTemplate.exchange(MR_builder.toUriString(), HttpMethod.GET, entity, String.class);
            MR_str=MR_response.getBody();
            String MR =MR_str;
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> projects = objectMapper.readValue(MR, new TypeReference<>() {});
            for (Map<String, Object> project : projects) {
                MR_count++;
                if(project.get("state").toString().equals("merged"))
                {merged_MR_count++;}
                else if (project.get("state").toString().equals("opened")){opened_MR_count++;}
                else if (project.get("state").toString().equals("closed")){closed_MR_count++;}
            }
            Merge="[{\"title\":"+"\"Merge Requests\""+",\"total_count\":"+MR_count+",\"merged\":"+merged_MR_count+",\"opened\":"+opened_MR_count+",\"closed\":"+closed_MR_count+"}]";
        } catch (Exception e) {
            e.printStackTrace();
            Merge="[{\"Error\":"+"\"Something Went Wrong\""+"}]";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        dataList = objectMapper.readValue(Merge, new TypeReference<List<Map<String, Object>>>() {});
        return dataList ;
    }
}