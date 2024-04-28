package com.green.onezo.review;

import com.green.onezo.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long id;
    private Member member;
    private String comment;
    private int star;
}
