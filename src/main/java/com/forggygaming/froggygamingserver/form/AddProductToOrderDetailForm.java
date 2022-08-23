package com.forggygaming.froggygamingserver.form;

import lombok.Data;

@Data
public class AddProductToOrderDetailForm {
    private Long orderDetailId;
    private String proName;
}
