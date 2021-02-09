package de.yalama.onlineshopbackend.Message.privateMessage.service;

import de.yalama.onlineshopbackend.Message.privateMessage.model.PrivateMessage;
import de.yalama.onlineshopbackend.Message.privateMessage.repository.PrivateMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PrivateMessageServiceImpl extends PrivateMessageService {

    private PrivateMessageRepository privateMessageRepository;

    public PrivateMessageServiceImpl(PrivateMessageRepository privateMessageRepository) {
        this.privateMessageRepository = privateMessageRepository;
    }


    @Override
    public PrivateMessage findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.privateMessageRepository.findById(id).get();
    }

    @Override
    public List<PrivateMessage> findAll() {
        return this.privateMessageRepository.findAll();
    }

    @Override
    public PrivateMessage save(PrivateMessage instance) {
        //TODO Validator notExists, Exception notSaved
        return this.privateMessageRepository.save(instance);
    }

    @Override
    public PrivateMessage update(PrivateMessage instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.privateMessageRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
