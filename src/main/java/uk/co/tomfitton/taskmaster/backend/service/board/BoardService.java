package uk.co.tomfitton.taskmaster.backend.service.board;

import java.util.List;

import uk.co.tomfitton.taskmaster.backend.domain.board.Board;

public interface BoardService {

	List<Board> getBoards();
	
	Board createBoard(Board board);
	
}
