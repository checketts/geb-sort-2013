import geb.Page

class TodoMvcAngularPage extends Page {
    static url = "http://localhost:3000/architecture-examples/angularjs/#/"
    static at = { title == "AngularJS • TodoMVC" }
    static content = {}
}