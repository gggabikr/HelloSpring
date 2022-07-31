package JasonLee.HelloSpring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "Name")
    //해당 값이 들어가는 컬럼 명이 UserName이면 뒤쪽 따옴표안에 그렇게 쓰면 된다.
    //해당 데이터가 들어가는 컬럼 이름을 쓰는 것.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
