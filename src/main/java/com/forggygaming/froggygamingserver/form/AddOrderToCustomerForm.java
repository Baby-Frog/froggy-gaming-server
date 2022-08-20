package com.forggygaming.froggygamingserver.form;

import lombok.Data;

@Data
public class AddOrderToCustomerForm {
    private String cusEmail;
    private Long orderId;
}
