package com.thc.realspr.domain;

import jakarta.persistence.*;
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
public class Tbpost {
    @Id private String id;
    @Setter @Column(nullable = false) private String deleted; // 삭제여부
    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length=2000000) @Lob private String content;
    @Setter @Column(nullable = false) private String boardname;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
    protected Tbpost(){}
    private Tbpost( String title, String content, String boardname) {
        this.title = title;
        this.content = content;
        this.boardname = boardname;
    }
    public static Tbpost of(String title, String content, String boardname){
        return new Tbpost(title, content, boardname);
    }

    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.deleted = "N";
    }
}
