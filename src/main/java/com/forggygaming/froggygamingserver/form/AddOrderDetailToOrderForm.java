package com.forggygaming.froggygamingserver.form;

import lombok.Data;

@Data
public class AddOrderDetailToOrderForm {
    private Long orderId;
    private Long orderDetailId;
}
