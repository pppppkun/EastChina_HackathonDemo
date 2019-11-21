package com.hackathon.backend.form;

import lombok.Data;


@Data
public class ItemForm {

    private Integer id;
    private String url;//img url
    private String seller;
    private String buyer;
    private Double price;
    private String add_time;// "2019.11.20"
    private Integer longest_time;// day
    private String category;
    private String description;
    private String delivery;

}
