package com.pds.core.service.log.add;

import com.pds.core.service.log.LogRequest;
import com.pds.core.service.log.LogResponse;

public interface AddLogService {

    LogResponse addLog(LogRequest request);

}
