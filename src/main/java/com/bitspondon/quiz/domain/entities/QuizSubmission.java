//package com.bitspondon.quiz.domain.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Table(name = "quizsubmissions")
//public class QuizSubmission {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "livequiz_id")
//    private LiveQuiz liveQuiz;
//
//    @ManyToOne
//    @JoinColumn(name = "oldquiz_id")
//    private OldQuiz oldQuiz;
///**
// * {
// *   "question1Id": ["choice1", "choice2"],
// *   "question2Id": ["choice3"],
// *   "question3Id": ["choice2", "choice4"]
// * }
// * import com.fasterxml.jackson.core.JsonProcessingException;
// * import com.fasterxml.jackson.databind.ObjectMapper;
// *
// * // ...
// *
// * // Serialization
// * ObjectMapper objectMapper = new ObjectMapper();
// * Map<Long, List<String>> chosenAnswersMap = new HashMap<>();
// * chosenAnswersMap.put(question1Id, Arrays.asList("choice1", "choice2"));
// * chosenAnswersMap.put(question2Id, Collections.singletonList("choice3"));
// * String chosenAnswersJson = objectMapper.writeValueAsString(chosenAnswersMap);
// *
// * // Deserialization
// * Map<Long, List<String>> deserializedChosenAnswersMap =
// *         objectMapper.readValue(chosenAnswersJson, new TypeReference<Map<Long, List<String>>>() {});
// *
// * // Now you have the deserialized map to work with
// *
// *
// * */
//    @Column(columnDefinition = "TEXT")
//    private String chosenAnswersJson; // Store as JSON
//
//    private int marks;
//
//    @Column(name = "submission_time")
//    private LocalDateTime submissionTime;
//
//
//    private String comments;
//
//    // Other constructors, getters, setters, and methods as needed
//    /**
//     *
//     * Correct Status:
//     *
//     *
//     * If all 30 answers are correct, you can set the submission status to "CORRECT."
//     * If a certain threshold of correct answers (e.g., 80% or 90%) is met, you might also consider setting the submission status to "CORRECT."
//     * You might calculate the percentage of correct answers and use that to determine if the submission is "CORRECT."
//     *
//     *
//     *If more than a certain number or percentage of answers are incorrect, you can set the submission status to "INCORRECT."
//     *If the user didn't meet the criteria for a "correct" submission (e.g., they answered fewer than a certain percentage of questions correctly), you could set the status to "INCORRECT."
//     *If the user didn't answer a specific number of questions correctly (e.g., answered less than 70% of questions correctly), you could consider the submission as "INCORRECT."
//     *
//     * If your quiz has open-ended questions or requires human evaluation (e.g., essays, subjective answers), you might set the status to "PENDING" until a human reviewer can assess the answers.
//     * If the user's session times out or gets interrupted during submission, you might set the status to "PENDING" until the submission is reattempted or recovered.
//     *
//     *
//     * */
//
//
//    @Override
//    public String toString() {
//        return "QuizSubmission{" +
//                "id=" + id +
//                ", user=" + user +
//                ", liveQuiz=" + liveQuiz +
//                ", oldQuiz=" + oldQuiz +
//                ", marks=" + marks +
//                '}';
//    }
//}
