package com.imyc.SBAP.Base.viewobject;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponse {

    private Map<String, String> message;

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        return json;
    }

}
