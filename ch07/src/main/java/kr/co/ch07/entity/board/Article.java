package kr.co.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="board_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    private int no;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // 연관엔티티가 로딩 될 때 필요한 시점에 로딩되는 지연전략
    @JoinColumn(name = "writer")        // 엔티티가 실행 테이블로 생성 될 때 컬럼명
    private User user;

    @OneToOne(mappedBy = "article")     // 양방향 연관관계에서 외래키를 갖는 엔티티의 속성을 mappedBy 속성으로 연관관계의 주체 설정
    private File file;

    @OneToMany(mappedBy = "article")
    private List<Comment> comment;

    @CreationTimestamp
    private LocalDateTime rdate;

}
