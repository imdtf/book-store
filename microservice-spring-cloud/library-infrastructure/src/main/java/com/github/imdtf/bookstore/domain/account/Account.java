package com.github.imdtf.bookstore.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.imdtf.bookstore.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/12 22:51
 * 4
 */
@Getter
@Setter
@Entity
public class Account extends BaseEntity {

    @NotEmpty
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(updatable = false)
    private String password;

    @NotEmpty
    private String name;

    private String avatar;

    @Pattern(regexp = "1\\d{10}")
    private String telephone;

    @Email
    private String email;

    private String location;
}
