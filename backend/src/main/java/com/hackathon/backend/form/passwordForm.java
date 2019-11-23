package com.hackathon.backend.form;

import lombok.Data;


@Data
public class passwordForm {
    int id;
    String OldPassword;
    String NewPassword;
}
