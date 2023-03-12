package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity // JPA가 관리하는 엔티티로 등록
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // @Id는 PK, @GeneratedValue(strategy = GenerationType.IDENTITY) identity 전략/auto_increment
    private Long id;
    @Column(name = "name")  // DB의 name컬럼이 username과 매핑
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
