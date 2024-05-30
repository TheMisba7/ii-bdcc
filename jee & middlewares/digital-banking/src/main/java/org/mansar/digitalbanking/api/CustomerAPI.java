package org.mansar.digitalbanking.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mansar.digitalbanking.dto.ChangePasswordDTO;
import org.mansar.digitalbanking.dto.CustomerDTO;
import org.mansar.digitalbanking.dto.CustomerDetailsDTO;
import org.mansar.digitalbanking.dto.NewCustomerDTO;
import org.mansar.digitalbanking.dto.PageContainer;
import org.mansar.digitalbanking.service.CustomerService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerAPI {
    private final CustomerService customerService;

    @GetMapping
    public PageContainer<CustomerDTO> getCustomerPage(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "100") int size,
                                                      @RequestParam(required = false , defaultValue = "") String keyword) {
        return customerService.getCustomers(keyword, page, size);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public CustomerDTO createCustomer(@RequestBody NewCustomerDTO customerDTO) {
        return customerService.newCustomer(customerDTO);
    }

    @GetMapping("/{customerId}/details")
    public CustomerDetailsDTO getDetails(@PathVariable long customerId) {
        return customerService.getCustomerDetails(customerId);
    }

    @GetMapping("/current")
    public CustomerDTO getCurrent() {
        return customerService.getCurrentCustomerDTO();
    }

    @PostMapping("/change-password")
    public void changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        customerService.changePassword(changePasswordDTO);
    }

    @DeleteMapping("/{customerId}")
    public void delete(@PathVariable long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
