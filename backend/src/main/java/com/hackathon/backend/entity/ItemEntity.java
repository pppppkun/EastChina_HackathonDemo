package com.hackathon.backend.entity;

import lombok.Data;

@Data
public class ItemEntity {

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

    @Override
    public String toString()
    {
        return "item{" +
                "id=" + id +
                ", seller='" + seller +'\'' +
                ", buyer='" + buyer + '\'' +
                ", price=" + price +
                ", add_time='" + add_time + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", delivery='" + delivery + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
