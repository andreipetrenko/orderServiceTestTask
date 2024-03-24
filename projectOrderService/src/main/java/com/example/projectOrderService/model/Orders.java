package com.example.projectOrderService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_sq",sequenceName="order_sq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    private Users users;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "order_status")
    private String orderStatus;

    @Override
    public String toString() {
        return "Orders{" +
                "users=" + users +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
