package com.joaoalmeidait.bankit.DTO;
import lombok.Data;

@Data
public class ReturnObjectDTO {
    private Object data;
    private boolean error;
    private String message;

    public ReturnObjectDTO(){}

    public ReturnObjectDTO(Object data, boolean error, String message) {

        this.data = data;
        this.error = error;
        this.message = message;
    }
}
