package by.bsuir.marketing.model;

import lombok.Data;

import java.util.Date;

@Data
public class MyResponseEntity implements BaseEntity {

    public MyResponseEntity(String message) {
        this.message = message;
        this.dateTime = new Date();
    }

    private String message;
    private Date dateTime;
}
