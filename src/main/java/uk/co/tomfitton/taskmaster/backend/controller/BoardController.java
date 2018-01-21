package uk.co.tomfitton.taskmaster.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
		return boardService.getBoards();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Board> createBoard(@Valid @RequestBody Board board) {
		Board createdBoard = boardService.createBoard(board); 
		return new ResponseEntity<Board>(createdBoard, HttpStatus.CREATED);
	}
	
}
