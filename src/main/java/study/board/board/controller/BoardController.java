package study.board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/board")
@Controller
public class BoardController {

    @GetMapping
    public String getBoard1Page(Model model, @RequestParam("name") String boardName) {
        model.addAttribute("board", boardName);
        return "/board/board";
    }
}
