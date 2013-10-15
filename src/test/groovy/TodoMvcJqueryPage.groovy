import geb.Page

class TodoMvcJqueryPage extends Page {
    static url = "http://localhost:3000/architecture-examples/jquery/"
    static at = { title == "jQuery • TodoMVC" }
    static content = {
        newTodo { $("#header input") }
        todos { $("#todo-list") }
        todoRow { i -> todos.find("li", i) }
        todoLabel { i -> todoRow(i).find("label") }
        todoCheckbox { i -> todoRow(i).find(type: "checkbox") }
        todoRemoveLink { i -> todoRow(i).find(".destroy") }

        activeElement { $(js."document.activeElement") }

        todoCount { $("#todo-count").find("strong").text() }
        footer { $("#footer") }
        filterLink { i -> $("#filters").find("a", text: i) }
    }
}