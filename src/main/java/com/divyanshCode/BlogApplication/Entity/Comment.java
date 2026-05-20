package com.divyanshCode.BlogApplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String comment;

    @ManyToOne
    @JoinColumn(name="postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name ="userId")
    private User user;

}
