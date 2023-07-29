package study.board.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class SubmitPostReq {

    @NotEmpty(message = "제목은 필수 입력 항목입니다.")
    @Size(min = 5, max = 255, message = "제목은 5~255자 사이로 입력해주세요.")
    private String title;

    @NotEmpty(message = "본문 내용은 필수 입력 항목입니다.")
    @Size(max = 1000, message = "본문 내용은 최대 1000자까지 입력 가능합니다.")
    private String content;
}
