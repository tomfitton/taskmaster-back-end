package uk.co.tomfitton.taskmaster.backend.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.tomfitton.taskmaster.backend.domain.board.Board;
import uk.co.tomfitton.taskmaster.backend.domain.board.BoardRepository;

@Service
public class DefaultBoardService implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public DefaultBoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Override
	public List<Board> getBoards() {
		return boardRepository.findAll();
	}
	
	@Override
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}

}
