import geb.Page

class FinalTodoMvcAngularPage extends Page {
    static url = "http://localhost:3000/architecture-examples/angularjs/#/"
    static at = {
        waitFor(15) {
            title == "AngularJS • TodoMVC"
        }
    }
    static content = {
        newTodo { $("#todo-form input") }
        todos { $("#todo-list") }
        todoRow(wait: true) { i -> todos.find("li", i) }
        todoLabel { i -> todoRow(i).find("label") }
        todoCheckbox { i -> todoRow(i).find(type: "checkbox") }
        todoRemoveLink { i -> todoRow(i).find(".destroy") }

        activeElement { $(js."document.activeElement") }

        todoCount { $("#todo-count").find("strong").text() }
        footer { $("#footer") }
        filterLink { i -> $("#filters").find("a", text: i) }
    }
}