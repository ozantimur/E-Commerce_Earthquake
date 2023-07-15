package com.example.ecommerce_earthquake.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock_count")
    private Integer stockCount;

    @Column(name = "price")
    private Integer price;

    @Column(name = "procuct_type")
    private String productType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
