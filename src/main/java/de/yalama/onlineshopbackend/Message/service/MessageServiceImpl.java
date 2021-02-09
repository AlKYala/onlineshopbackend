package de.yalama.onlineshopbackend.Message.service;

import de.yalama.onlineshopbackend.Message.model.Message;
import de.yalama.onlineshopbackend.Message.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl extends MessageService {

    public MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.messageRepository.findById(id).get();
    }

    @Override
    public List<Message> findAll() {
        return this.messageRepository.findAll();
    }

    @Override
    public Message save(Message instance) {
        //TODO Validator notExists, Exception notSaved
        return this.messageRepository.save(instance);
    }

    @Override
    public Message update(Message instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.messageRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
