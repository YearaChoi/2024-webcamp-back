package com.thc.realspr.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class) // For Audit
public class Tbuser {
    @Id private String id;
    @Setter @Column(nullable = false) private String deleted; // 삭제여부

    @Setter @Column(nullable = false) private String username; // 사용자아이디
    @Setter @Column(nullable = false) private String password; // 비번
    @Setter @Column(nullable = false) private String name;
    @Setter @Column(nullable = false) private String nick;
    @Setter @Column(nullable = false) private String phone;
    @Setter @Column(nullable = false) private String mpic; // 프로필 사진
    @Setter @Column(nullable = false, length=2000000) @Lob private String content; // 본문

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
    protected Tbuser(){}
    private Tbuser(String username, String password, String name, String nick, String phone, String mpic, String content) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nick = nick;
        this.phone = phone;
        this.mpic = mpic;
        this.content = content;
    }
    public static Tbuser of(String username, String password){
        return new Tbuser(username, password, "", "", "", "", "");
    }

    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.deleted = "N";
    }
}
