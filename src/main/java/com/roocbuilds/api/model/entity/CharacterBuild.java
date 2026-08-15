package com.roocbuilds.api.model.entity;

import com.roocbuilds.api.model.enums.BuildType;
import com.roocbuilds.api.model.enums.JobClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name="character_builds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_class", nullable = false)
    private JobClass jobClass;

    @Enumerated(EnumType.STRING)
    @Column(name="build_type", nullable = false)
    private BuildType buildType;

    @Column(nullable = false)
    private Integer votes = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(length = 255)
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

}
