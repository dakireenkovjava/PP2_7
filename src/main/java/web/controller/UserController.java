package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired //spring внедрил объект класса userservice c пом конструктора
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping() // возвращает людей и передает на представление
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "users/index";
    }

    @GetMapping("/{id}") // возвращает людей и передает на представление  или "/users/{id}"
    //вместо id в url поместить число, и оно войдет в параметры этого метода с помощью @PathVariable
    public String show(@PathVariable("id") int id, Model model) {
        // получим человека по id из DAo и отправим на представление
        model.addAttribute("user", userService.show(id));
        return "users/show";
    }
    // возвращает html форму для создания человека
    /*@GetMapping("/new") // по этому запросу вернется html форма для создания нового человека
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new"; // файл html
    }*/
    //или
    @GetMapping("/new") // по этому запросу вернется html форма для создания нового человека
    public String newUser(@ModelAttribute( "user") User user) { //сама создаст объект user и сама положит его в модель
        return "users/new"; // файл html
    }

    //принимает на вход post запрос, будет брать данные из этого запроса и будет добавлять этого человека в БД с помощью dao
    @PostMapping()
    public String create(@ModelAttribute( "user") User user) {
        //получаем данные из формы, создаем человека и присваиваем его поля значениям из формы
        userService.save(user); // метод save реализовать в userdao а затем в userservice
        return "redirect:/users"; //
    }
    //методы ообновления и удаления человека
    @GetMapping("/{id}/edit") // адрес метода, по этому адресу users/{id}/edit попадем в этот метод
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));  // вернется человек с указанным id (id из адреса)
        // человек будет положен в модель и к этой модели мы будем иметь доступ в нашем представлении
        return "users/edit"; // представление по этому адресу
    }
    //метод который принимает patch запрос на адрес /users/id
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        //с пом  @ModelAttribute принимаем user из формы,  @PathVariable("id") применяем id из адреса
        userService.update(id,user); // находим человека в БД с пришедшим id, и меняем значения его полей на те,
        // которые пришли из формы
        return "redirect:/users"; //после успешного обновления переходим на старницу юзеров
    }

    //метод для удаления
    @DeleteMapping("/{id}") // принимает в адресе id удаляемогоч человека
    public String delete(@PathVariable ("id") int id) { //считываем id
        userService.delete(id);
        return "redirect:/users"; //после успешного удаления переходим на старницу юзеров
    }

}
/*
был метод
    @GetMapping("/users") // возвращает людей
    public String userGet(@RequestParam(value = "count", required = false) String n, Model model) {
        if (n == null) {
        model.addAttribute("users", userService.index()); // передаем метод из сервиса (а он из dao)
        } else {
            model.addAttribute("users",userService.show(Integer.parseInt(n)));
        }
        return "users";
    }
*/


/*
11/06/2023 17:36
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired //spring внедрил объект класса userservice c пом конструктора
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping() // возвращает людей и передает на представление можно users убрать
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "users/index";
    }

    @GetMapping("/{id}") // возвращает людей и передает на представление  или "/users/{id}"
    //вместо id в url поместить число, и оно войдет в параметры этого метода с помощью @PathVariable
    public String show(@PathVariable("id") int id, Model model) {
        // получим человека по id из DAo и отправим на представление
        model.addAttribute("user", userService.show(id));
        return "users/show";
    }

    // возвращает html форму для создания человека
    @GetMapping("/new") // по этому запросу вернется html форма для создания нового человека
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new"; // файл html
    }

    //принимает на вход post запрос, будет брать данные из этого запроса и будет добавлять этого человека в БД с помощью dao
    @PostMapping()
    public String create(@ModelAttribute( "user") User user) {
        //получаем данные из формы, создаем человека и присваиваем его поля значениям из формы
        userService.save(user); // метод save реализовать в userdao а затем в userservice
        return "redirect:/users"; //
    }


}}*/