package org.mansar.digitalbanking.api;

import lombok.RequiredArgsConstructor;
import org.mansar.digitalbanking.app.DashboardApp;
import org.mansar.digitalbanking.dto.DashboardDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardAPI {
    private final DashboardApp dashboardApp;

    @GetMapping
    public DashboardDTO getDashboard() {
        return dashboardApp.getDashboard();
    }
}
