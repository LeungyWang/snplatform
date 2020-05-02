package com.wly.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "newscomment")
public class NewsComment {
    @Id
    private String commentid;
    private String userid;
    private String newsid;
    private String review_content;
    private Date review_time;
}
