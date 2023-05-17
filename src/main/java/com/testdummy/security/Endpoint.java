package com.testdummy.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpMethod;

@Data
public class Endpoint {
    private String endPointUrl;
    private HttpMethod method;

    public Endpoint(String endPointUrl,String method)  throws Exception{
        this.endPointUrl = endPointUrl;
        this.setMethod(method);

    }
    public Endpoint(String endpoint) throws Exception {
        // Parse the JSON and set the endpoint and method fields
        // using a JSON processing library of your choice
        // Replace the code below with your JSON parsing implementation

        // Example: assuming endpoint is in JSON format
        // {"endPointUrl": "/api/endpoint", "method": "GET"}
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(endpoint);
        this.endPointUrl = jsonNode.get("endPointUrl").asText();
        this.setMethod(jsonNode.get("method").asText());

    }
    public void setMethod(String method) throws Exception {
        switch (method){
            case "GET":
                this.method = HttpMethod.GET;
                break;
            case "POST":
                this.method = HttpMethod.POST;
                break;
            case "PUT":
                this.method = HttpMethod.PUT;
                break;
            case "DELETE":
                this.method = HttpMethod.DELETE;
                break;
            default:
                throw new Exception("Please provide the proper method name, refrence GET,POST,PUT,DELETE");

        }
    }
}
