package com.pds.core.service.user.add;

import com.pds.core.service.user.AddUserRequest;
import com.pds.core.service.user.AddUserResponse;


public interface AddUserService {

    AddUserResponse register(AddUserRequest request);

}
