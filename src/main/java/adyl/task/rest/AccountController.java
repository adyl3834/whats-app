package adyl.task.rest;

import adyl.task.exception.ResourceNotFoundException;
import adyl.task.model.Account;
import adyl.task.model.Chat;
import adyl.task.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/whatsapp/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "login to what-app",
            notes = "Get Account by name",
            response = Account.class)

    public Account login(@RequestParam(value = "name") String name) {
        return accountService.getAccountByName(name);
    }

    @PostMapping(value = "createAccount", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create account by name and picture",
            notes = "Save new Account",
            response = Account.class)
    public Account save(@Validated @RequestBody final Account account) {
        return accountService.save(account);
    }

    @PutMapping(value = "updateAccount", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update account",
            notes = "Update account name, picture, chat",
            response = Account.class)
    public Account update(@Validated @RequestBody final Account account) {
        return accountService.update(account);
    }

    @GetMapping(value = "getAllAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Accounts",
            notes = "Get all Accounts and chats",
            response = Account.class)
    public List<Account> getAllTheAccounts() {
        return accountService.findAll();
    }

    @GetMapping(value = "getAllTheChats/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Account chats",
            notes = "Get all Account  chats",
            response = Account.class)
    public Set<Chat> getAllTheChats(@PathVariable("id") Long id) {
        Set<Chat> chats = accountService.findAllChats(id);
        if (chats != null) {
            return chats;
        } else throw new ResourceNotFoundException("Not found chats with id :" + id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get by Id",
            notes = "Get Account by Id",
            response = Account.class)
    public Account getAccountById(@PathVariable("id") Long id) {
        Account account = accountService.getByChatId(id);
        if (account != null) {
            return account;
        } else throw new ResourceNotFoundException("Account not found with id :" + id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete by Id",
            notes = "Delete Account by Id",
            response = Account.class)
    public void delete(@PathVariable("id") Long id) {
        accountService.deleteById(id);
    }

}
