package boardProject.models.board;

import boardProject.entities.BoardData;
import boardProject.repositories.BoardDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDataInfoService {

    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long id) {

        BoardData boardData = boardDataRepository.findById(id).orElseThrow(BoardDataNotExistException::new);

        return null;
    }
}
