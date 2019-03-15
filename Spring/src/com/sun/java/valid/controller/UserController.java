package valid.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import valid.bean.User;

import javax.validation.Valid;

/**
 * 用户Controller
 *
 * @Author Sun
 * @date 2019-03-12
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String create(@Valid User user, BindingResult bindingResult) {

        System.out.println(user.getId());
        user.setId("1");

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return bindingResult.getFieldError().getDefaultMessage();
        }

        return "True";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "hello China";
    }

}
