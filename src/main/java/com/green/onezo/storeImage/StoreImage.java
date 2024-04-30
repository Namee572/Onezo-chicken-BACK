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
public class StoreImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_Image_Id")
    private Long id;

    private String image;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;


}
