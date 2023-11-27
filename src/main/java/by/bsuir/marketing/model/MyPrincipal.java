package by.bsuir.marketing.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MyPrincipal implements BaseEntity, Serializable {

    private static final long serialVersionUID = 1l;

    public MyPrincipal(int idAccount, String roleName) {
        this.idAccount = idAccount;
        this.roleName = roleName;
    }

    private int idAccount;
    private String roleName;
}
