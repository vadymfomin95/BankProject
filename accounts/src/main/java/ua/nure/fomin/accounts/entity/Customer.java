package ua.nure.fomin.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.nure.fomin.accounts.audit.AuditListener;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@EntityListeners(AuditListener.class)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String name;

    private String email;

    @Column(name="mobile_number")
    private String mobileNumber;
}
