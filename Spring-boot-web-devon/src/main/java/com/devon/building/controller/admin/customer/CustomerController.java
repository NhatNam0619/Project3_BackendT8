package com.devon.building.controller.admin.customer;

import com.devon.building.converter.CustomerDTOsConverter;
import com.devon.building.converter.TransactionDTOsConverter;
import com.devon.building.entity.Customer;
import com.devon.building.entity.Transaction;
import com.devon.building.enums.StatusCode;
import com.devon.building.model.dto.CustomerResponseDTO;
import com.devon.building.model.dto.TransactionDTO;
import com.devon.building.model.request.CustomerSearchRequest;
import com.devon.building.service.CustomerService;
import com.devon.building.service.UserService;
import com.devon.building.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value="customerControllerOfAdmin")
@RequestMapping("/admin/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;
    private final CustomerDTOsConverter customerDTOsConverter;
    private final TransactionDTOsConverter transactionDTOsConverter;

    @GetMapping("/list")
    public ModelAndView getCustomer(@ModelAttribute("params") CustomerSearchRequest params, @RequestParam(value = "page", defaultValue = "1") String pageStr){
        ModelAndView mav = new ModelAndView("admin/customer/customerList");
        params.setUser(userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CustomerResponseDTO> customers = customerService.findAll(params);
        mav.addObject("customerResponseDTOs",customers);
        mav.addObject("staffs",userService.getAllRoleStaff());
        mav.addObject("status", StatusCode.getStatus());
        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView createCustomer(){
        ModelAndView mav = new ModelAndView("admin/customer/customerEdit");
        Customer customer = new Customer();
        mav.addObject("customer",customer);
        mav.addObject("statusId", StatusCode.getStatus());
        return mav;
    }

    @GetMapping("/edit/{Id}")
    public ModelAndView editCustomer(@PathVariable Long Id){
        ModelAndView mav = new ModelAndView("admin/customer/customerEdit");
        Customer customer = customerService.getCustomer(Id);
        if(!SecurityUtil.hasRole("MANAGER") && !customer.getUsers().contains(userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName())))
        {return new ModelAndView("403");}
        CustomerResponseDTO customerResponseDTO = customerDTOsConverter.convertCustomerResponseDTO(customer);
        List<Transaction> transactions = customerService.getCustomer(Id).getTransactions();
        List<TransactionDTO> CSKH = new ArrayList<>();
        List<TransactionDTO> DDX = new ArrayList<>();
        for (Transaction t : transactions)
        {
            TransactionDTO transactionDTO = transactionDTOsConverter.toTransactionDTO(t);
            transactionDTO.setNote(t.getNote());
            transactionDTO.setCreatedBy(userService.getUsername(t.getCreatedBy()).getFullName());
            if (StringUtils.hasText(t.getModifiedBy())) {
                transactionDTO.setModifiedBy(userService.getUsername(t.getModifiedBy()).getFullName());}
            if(t.getCode().equals("CSKH")) {CSKH.add(transactionDTO);}
            else {DDX.add(transactionDTO);}
        }
        mav.addObject("customer",customerResponseDTO);
        mav.addObject("statusId",StatusCode.getStatus());
        mav.addObject("CSKH",CSKH);
        mav.addObject("DDX",DDX);
        return mav;
    }
}
