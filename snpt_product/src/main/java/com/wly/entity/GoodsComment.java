package com.wly.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "comment")
public class GoodsComment {
    @Id
    private String commentid;
    private String userid;
    private String productid;
    private String review_content;
    private Date review_time;
}
