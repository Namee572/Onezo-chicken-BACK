package com.green.onezo.storeImage;

import com.green.onezo.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StoreLmage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeLmageId")
    private Long id;


    private String image;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;


}
