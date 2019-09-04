package com.mysql.demo;

import javax.persistence.*;
import java.io.Serializable;

public class UserTypeKey implements Serializable {

    @Column(name = "UserID")
    private Integer UserID;

    @Column(name = "TypeID")
    private Integer TypeID;

}
