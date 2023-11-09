package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ValidateNickname;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import br.com.crossgame.matchmaking.internal.utils.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class DefaultValidateNickname implements ValidateNickname {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Boolean execute(String nickname) {
        int indexReturned = this.searchbinary(nickname);
        if (indexReturned == -1){
            return false;
        }
        return true;
    }


    private Optional<ListaObj<User>> sortNicknames(String nickname){
        List<User> users = userRepository.findAll();
        users = DefaultOrderListByName.sortListUser(users);
        ListaObj<User> userListaObj = new ListaObj<>(users.size());
        for (User u: users) {
            userListaObj.adiciona(u);
        }
        return  Optional.of(userListaObj);
    }

    private int searchbinary( String value) {
        ListaObj<User> users = this.sortNicknames(value)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Usuário com o nickname %s não foi encontrado!",value)));
        int left = 0;
        int right = users.getTamanho() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int comparison = value.compareTo(users.buscaPorIndice(mid).get().getUsername());

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }


}






