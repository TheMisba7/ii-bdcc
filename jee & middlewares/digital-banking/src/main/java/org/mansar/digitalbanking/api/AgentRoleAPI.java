package org.mansar.digitalbanking.api;

import lombok.RequiredArgsConstructor;
import org.mansar.digitalbanking.model.AgentRole;
import org.mansar.digitalbanking.service.AgentRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class AgentRoleAPI {

    private final AgentRoleService agentRoleService;

    @GetMapping
    public List<AgentRole> getRoles() {
        return agentRoleService.getRoles();
    }
}
