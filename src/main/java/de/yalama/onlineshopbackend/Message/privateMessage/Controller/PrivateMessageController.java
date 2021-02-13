package de.yalama.onlineshopbackend.Message.privateMessage.Controller;

import de.yalama.onlineshopbackend.Message.privateMessage.model.PrivateMessage;
import de.yalama.onlineshopbackend.Message.privateMessage.service.PrivateMessageService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pm")
public class PrivateMessageController implements BaseController<PrivateMessage, Long> {

    @Autowired
    private PrivateMessageService privateMessageService;

    @Override
    @GetMapping
    public List<PrivateMessage> findAll() {
        return this.privateMessageService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public PrivateMessage findById(@PathVariable Long id) {
        return this.privateMessageService.findById(id);
    }

    @Override
    @PostMapping
    public PrivateMessage create(@RequestBody PrivateMessage privateMessage) {
        return this.privateMessageService.save(privateMessage);
    }

    @Override
    @PutMapping("/{id}")
    public PrivateMessage update(@PathVariable Long id,@RequestBody PrivateMessage privateMessage) {
        return this.privateMessageService.update(id, privateMessage);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.privateMessageService.deleteById(id);
    }
}
