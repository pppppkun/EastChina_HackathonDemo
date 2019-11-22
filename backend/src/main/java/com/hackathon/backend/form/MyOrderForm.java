package com.hackathon.backend.form;

import lombok.Data;


@Data
public class MyOrderForm {

    private Integer id;
    private String url;
    private Double total_price;
    private String state;
    private String create_time;//2019.11.20

}
