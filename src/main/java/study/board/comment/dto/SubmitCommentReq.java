package study.board.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class SubmitCommentReq {

    @NotEmpty(message = "댓글 내용은 필수 입력 항목입니다.")
    @Size(max = 200, message = "댓글은 최대 200자까지 가능합니다.")
    String content;
}
