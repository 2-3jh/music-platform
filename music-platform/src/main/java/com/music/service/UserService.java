package com.music.service;

import com.music.dto.UserEntryDTO;
import com.music.entity.User;

public interface UserService {
    void register(UserEntryDTO userRegisterDTO);

    User logon(UserEntryDTO userEntryDTO);
}
