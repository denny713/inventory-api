package com.inventory.api.data.model.data;

import lombok.Data;

@Data
public class AksesData {

    public AksesData() {
        super();
    }

    public AksesData(Long idMenu, String parent, String menu, Boolean readOnly) {
        this.idMenu = idMenu;
        this.parent = parent;
        this.menu = menu;
        this.readOnly = readOnly;
    }

    private Long idMenu;
    private String parent;
    private String menu;
    private Boolean readOnly;
}
