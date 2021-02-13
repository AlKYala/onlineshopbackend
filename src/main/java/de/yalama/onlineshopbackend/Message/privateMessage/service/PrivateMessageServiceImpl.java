package de.yalama.onlineshopbackend.Message.privateMessage.service;

import de.yalama.onlineshopbackend.Message.privateMessage.model.PrivateMessage;
import de.yalama.onlineshopbackend.Message.privateMessage.repository.PrivateMessageRepository;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PrivateMessageServiceImpl extends PrivateMessageService {

    private PrivateMessageRepository privateMessageRepository;
    private Validator<PrivateMessage, PrivateMessageRepository> validator;
    private UserRepository userRepository;

    public PrivateMessageServiceImpl(PrivateMessageRepository privateMessageRepository,
                                     UserRepository userRepository) {
        this.privateMessageRepository = privateMessageRepository;
        this.validator =
                new Validator<PrivateMessage, PrivateMessageRepository>("Private Message", this.privateMessageRepository);
        this.userRepository = userRepository;
    }


    @Override
    public PrivateMessage findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.privateMessageRepository.findById(id).get();
    }

    @Override
    public List<PrivateMessage> findAll() {
        return this.privateMessageRepository.findAll();
    }

    @Override
    public PrivateMessage save(PrivateMessage instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.privateMessageRepository.save(instance);
    }

    @Override
    public PrivateMessage update(Long id, PrivateMessage instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.privateMessageRepository.save(instance);
    }

    @Override
    /**
     * NOTE: Messages are only deleted when both users are deleted
     */
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        PrivateMessage toDelete = this.findById(id);
        boolean senderExists = this.privateMessageRepository.existsById(toDelete.getSender().getId());
        boolean receiverExists = this.privateMessageRepository.existsById(toDelete.getReceiver().getId());

        if(!senderExists && !receiverExists) {
            this.privateMessageRepository.deleteById(id);
            return id;
        }
        return null;
    }

    public Long deleteIfBothUsersDeleted(Long id) {
        return this.deleteById(id);
    }
}
