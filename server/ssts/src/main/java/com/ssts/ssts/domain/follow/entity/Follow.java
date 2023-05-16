package com.ssts.ssts.domain.follow.entity;

import com.ssts.ssts.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity(name="follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="follower_id")
    private Member follower;

    @ManyToOne
    @JoinColumn(name="following_id")
    private Member following;

    public static Follow of(Member follower, Member following) {
        Follow follow=new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        return follow;
    }

    public void setFollower(Member follower) {
        this.follower = follower;
    }

    public void setFollowing(Member following) {
        this.following = following;
    }

    /* long 타입 id 기준
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
    }*/
}
