package com.pds.core.service.log.add;

import com.pds.core.domain.Log;
import com.pds.core.repository.LogRepository;
import com.pds.core.service.log.LogRequest;
import com.pds.core.service.log.LogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddLogServiceImpl implements AddLogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    @Transactional
    public LogResponse addLog(LogRequest request) {
        Log log = new Log();

        log.setLogData(request.getLogData());
        log.setTimestamp(request.getTimestamp());
        log.setName(request.getName());

        logRepository.saveAndFlush(log);

        LogResponse response = new LogResponse();
        response.setId(log.getId());

        return response;
    }

}
