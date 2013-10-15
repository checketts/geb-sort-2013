import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import spock.lang.Stepwise

@Stepwise
class FinalStepwiseTodoMvcSpec extends GebReportingSpec {

    def "The page starts empty"() {
        given:
        to FinalTodoMvcAngularPage

        expect:
        !footer.isDisplayed()
    }

    def "I can create todos"() {
        when:
        newTodo << "Create a test" << Keys.ENTER

        then:
        todoLabel(0).text() == "Create a test"
        todoCount == "1"
        $("ng-pluralize").text() == "item left"
    }

    def "I can mark a todo as completed"() {
        when:
        todoCheckbox(0).click()

        then:
        todoRow(0).classes().contains("completed")
        todoCount == "0"
        $("ng-pluralize").text() == "items left"
    }

    def "I can filter by active or completed"() {
        given: "I add an extra active todo"
        newTodo << "Automate the test" << Keys.ENTER

        expect:
        waitFor {
            todos.children().size() == 2
        }


        when:
        filterLink("Active").click()

        then:
        todos.children().size() == 1


        when:
        filterLink("Completed").click()

        then:
        todos.children().size() == 1

        when:
        filterLink("All").click()

        then:
        todos.children().size() == 2;
    }

    def "I can refresh the page and the todos persist"() {
        when:
//        driver.navigate().refresh();
        interact {
            keyDown(Keys.CONTROL)
            sendKeys(Keys.F5)
        }

        then:
        todos.children().size() == 2
    }

    def "I can clear completed"() {
        when:
        $("#clear-completed").click()

        then:
        todos.children().size() == 1
        !$("#clear-completed").isDisplayed()
    }

    def "I can edit todos"() {
        when:
        interact {
            doubleClick(todoLabel(0))
        }
        js."document.activeElement.select()"
        activeElement << "Ask for questions" << Keys.ENTER

        then:
        todoLabel(0).text() == "Ask for questions"

    }

    def "I can delete todos"() {
        when:
        interact {
            moveToElement(todoRow(0))
        }

        todoRemoveLink(0).click()

        then:
        todos.children().size() == 0
        !footer.isDisplayed()

    }

}
