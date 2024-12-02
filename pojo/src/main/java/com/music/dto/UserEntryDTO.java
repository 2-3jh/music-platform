package com.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntryDTO {
    //名字
    private String name;
    //密码
    private String password;
}
