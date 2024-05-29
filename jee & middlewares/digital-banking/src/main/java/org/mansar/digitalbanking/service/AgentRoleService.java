package org.mansar.digitalbanking.service;

import lombok.RequiredArgsConstructor;
import org.mansar.digitalbanking.dao.AgentRoleDao;
import org.mansar.digitalbanking.model.AgentRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentRoleService {
    private final AgentRoleDao agentRoleDao;


    public List<AgentRole> getRoles() {
        return agentRoleDao.findAll();
    }
}
