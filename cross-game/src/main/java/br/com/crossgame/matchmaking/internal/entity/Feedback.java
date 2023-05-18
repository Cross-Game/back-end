package br.com.crossgame.matchmaking.internal.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "feedbacks")
@Data
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "user_given_feedback")
    private String userGivenFeedback;
    @NotNull
    @Min(value = 0)
    @Max(value = 5)
    @Column(name = "behavior")
    private Integer behavior;
    @NotNull
    @Min(value = 0)
    @Max(value = 5)
    @Column(name = "skill")
    private Integer skill;
    @Size(max = 255)
    @NotBlank
    @Column(name = "feedback_text")
    private String feedbackText;
    @Column(name = "feedback_given_date")
    private LocalDate feedbackGivenDate;
}