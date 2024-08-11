package hanium.dtc.community.domain;

import hanium.dtc.travel.domain.TravelRecord;
import hanium.dtc.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "post_like")
    private Integer postLike;

    @Column(name = "comment")
    private Integer comment;

    @Column(name = "scrap")
    private Integer scrap;

    @Column(name = "is_travel")
    private Boolean isTravel;

    @Column(name = "is_mine")
    private Boolean isMine;

    @Column(name = "post_time")
    private LocalDateTime postTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private TravelRecord travelRecord;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, TravelRecord travelRecord) {
        this.title = title;
        this.content = content;
        this.travelRecord = travelRecord;
        this.isTravel = (travelRecord != null); // travelRecord가 있으면 isTravel을 true로 설정
        this.postTime = LocalDateTime.now();
        this.postLike = 0;
        this.comment = 0;
        this.scrap = 0;
        this.isMine = true; // 기본값, true 설정
    }

    public void updatePost(String title, String content, TravelRecord travelRecord) {
        this.title = title;
        this.content = content;
        this.travelRecord = travelRecord;
        this.isTravel = (travelRecord != null);
    }
}
