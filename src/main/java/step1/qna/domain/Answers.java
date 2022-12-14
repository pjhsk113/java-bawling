package step1.qna.domain;

import org.hibernate.annotations.Where;
import step1.qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public void validateDelete (User loginUser) throws CannotDeleteException {
        boolean isMatch = answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
        if (isMatch) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void add (Answer answer) {
        answers.add(answer);
    }

    public Stream<DeleteHistory> mapToDeleteHistory() {
        return answers.stream()
                .map(Answer::delete);
    }
}
