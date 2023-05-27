package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.RetrieveRooms;
import br.com.crossgame.matchmaking.internal.entity.TeamRoom;
import br.com.crossgame.matchmaking.internal.repository.TeamRoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class DefaultRetrieveRooms implements RetrieveRooms {

    private TeamRoomRepository teamRoomRepository;

    @Override
    public List<TeamRoom> execute() {
        return teamRoomRepository.findAll();
    }

}
