package com.hackathon.backend.entity;

import lombok.Data;

@Data
public class MyFormEntity {

    private Integer id;
    private String url;
    private Double total_price;
    private String state;
    private String create_time;//2019.11.20

    @Override
    public String toString()
    {
        return "order{"+
                "id=" + id +
                ", url='" + url + '\'' +
                ", total_price=" + total_price +
                ", state='" + state + '\'' +
                ", create_time" + create_time + '\'' +
                '}';
    }

}
