package step1.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step1.qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제시 질문의 작성자가 아닐 경우 CannotDeleteException 발생")
    @ParameterizedTest
    @MethodSource("provideQuestionAndNonWriter")
    public void 삭제_검증_테스트 (Question question, User writer) {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(writer));
    }

    private static Stream<Arguments> provideQuestionAndNonWriter () {
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }
}
