package com.music.service;

import com.music.dto.UserEntryDTO;
import com.music.entity.User;
import com.music.vo.UserInfoVO;

public interface UserService {
    void register(UserEntryDTO userRegisterDTO);

    User login(UserEntryDTO userEntryDTO);

    UserInfoVO getUserInfo();
}
