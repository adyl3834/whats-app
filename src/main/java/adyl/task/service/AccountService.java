package adyl.task.service;

import adyl.task.exception.ResourceNotFoundException;
import adyl.task.model.Account;
import adyl.task.model.Chat;
import adyl.task.model.Image;
import adyl.task.repository.AccountRepository;
import adyl.task.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ImageRepository imageRepository;

    public Account save(Account account) {
        Image image = account.getAccountPicture();
        if (image != null && image.getId() == null) {
            image = imageRepository.save(image);
            account.setAccountPicture(image);
        }
        return accountRepository.save(account);
    }

    public Account getAccountByName(String name) {
        return accountRepository.getByName(name);
    }

    public Account update(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public Account getByChatId(Long id) {
        return accountRepository.getById(id);
    }

    public Set<Chat> findAllChats(Long id) {
        Account account = accountRepository.getById(id);
        if (account == null){
            throw  new ResourceNotFoundException("Not found chats with id :" + id);
        }
        return account.getChats();
    }
}
