package com.devon.building.model.dto;

import com.devon.building.entity.Transaction;
import com.devon.building.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDTO extends AbstractDTO{
    private Long id;
    private String fullName;
    private Long phone;
    private String email;
    private String demand;
    private String status;
    private String statusname;
    private String companyname;
    private Map<String, List<TransactionDTO>> transactionList;
}
