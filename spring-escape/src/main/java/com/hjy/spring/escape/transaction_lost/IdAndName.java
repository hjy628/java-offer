package com.hjy.spring.escape.transaction_lost;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @auther: hjy
 * @Date: 2020/12/27 21:52
 * @Description:
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "id_and_name")
public class IdAndName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "name",nullable = false)
    private String name;

    public IdAndName(String name) {
        this.name = name;
    }
}
