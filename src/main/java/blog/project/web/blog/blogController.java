package blog.project.web.blog;

import blog.project.domain.blog.BoardForm;
import blog.project.domain.blog.service.BoardService;
import blog.project.web.blog.form.BoardSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class blogController {

    private final BoardService boardService;

    @GetMapping("/add")
    public String BoardForm(@ModelAttribute("boardSaveForm") BoardSaveForm boardSaveForm , @SessionAttribute("loginId") String id, Model model) {
        model.addAttribute("memberId", id);
        return "board/boardForm";
    }

    @PostMapping("/add")
    public String BoardWrite(@Valid @ModelAttribute BoardSaveForm boardSaveForm, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes ,
                             @SessionAttribute("loginId") String id, Model model) {

        if(bindingResult.hasErrors()) {
            log.info("boardWriteError:{}",bindingResult);
            model.addAttribute("memberId", id);
            return "board/boardForm";
        }

        //정상적일때 로직
        BoardForm board = new BoardForm(boardSaveForm.getName(), boardSaveForm.getTitle(), boardSaveForm.getContext());
        BoardForm saveBoard = boardService.registration(board);

        redirectAttributes.addAttribute("boardId", saveBoard.getId());
        redirectAttributes.addAttribute("save", true); //쿼리 파라미터로 URL 에 붙어서 전달

        log.info("boardAdd success");
        return "redirect:/board/{boardId}";
    }


    @GetMapping("/{boardId}")
    public String Board(@PathVariable Long boardId, Model model) {
        BoardForm searchBoard = boardService.search(boardId);
        model.addAttribute("board", searchBoard);
        return "board/board";
    }

    @GetMapping("/boards")
    public String Boards(@SessionAttribute("loginId") String id , Model model) {
        List<BoardForm> boards = boardService.boards(id);
        model.addAttribute("boards", boards);

        return "board/board-list";
    }


    @GetMapping("/{boardId}/edit")
    public String BoardEditForm(@PathVariable Long boardId, @SessionAttribute("loginId") String id, Model model) { //boardId로 검색할껀지, session의 id로 검색할껀지 제대로 선택???!!!!
        BoardForm board = boardService.search(boardId);
        model.addAttribute("board", board);
        return "board/boardEditForm";
    }

    @PostMapping("/{boardId}/edit")
    public String BoardEdit(@PathVariable Long boardId ,@ModelAttribute BoardForm boardForm , @SessionAttribute("loginId") String id){

        log.info("edit commit(id= " + boardId +")");
        boardService.updateBoard(boardId, boardForm);
        return "redirect:/board/boards";
    }

    @GetMapping("/{boardId}/edit/delete")
    public String BoardDelete(@PathVariable Long boardId,@SessionAttribute("loginId") String id) {

        log.info("delete commit(id=" +boardId + ")");
        BoardForm boardForm = boardService.deleteBoard(boardId);
        return "redirect:/board/boards";
    }


}
