package uk.co.tomfitton.taskmaster.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.co.tomfitton.taskmaster.backend.domain.board.Board;
import uk.co.tomfitton.taskmaster.backend.service.board.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {

	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Board> getBoards() {
		List<Board> boards = boardService.getBoards();
		return boards;
	}
	
}
