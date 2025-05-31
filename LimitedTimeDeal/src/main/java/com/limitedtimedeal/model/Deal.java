package com.limitedtimedeal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    Long productId;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    LocalDateTime startTime;

    @Column(nullable = false,updatable = true)
    LocalDateTime endTime;

    @Column(nullable = false,updatable = true)
    double discount;

    boolean isValid = true;

    @Column(nullable = false, updatable = true)
    private Long productCount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "deal_users",
            joinColumns = @JoinColumn(name = "deal_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Users> users = new HashSet<>();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return Objects.equals(id, deal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
