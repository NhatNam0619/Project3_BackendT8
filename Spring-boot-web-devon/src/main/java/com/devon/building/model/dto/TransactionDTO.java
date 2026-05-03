package com.devon.building.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDTO extends AbstractDTO {
    String code;
    String note;
    Long customerId;
    Long staffid;
}
