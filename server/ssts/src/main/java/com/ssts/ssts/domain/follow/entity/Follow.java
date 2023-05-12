package com.ssts.ssts.domain.follow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long follower;
    // give_member_id;

    @Column(nullable = false)
    private long following;
    // receive_member_id;

    public static Follow of(long follower, long following) {
        Follow follow=new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        return follow;
    }
}
