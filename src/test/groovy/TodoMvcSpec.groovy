import geb.driver.CachingDriverFactory
import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys

class TodoMvcSpec extends GebReportingSpec {

    def cleanup() {
        //Clean up the browser
        def cachedDriver = CachingDriverFactory.clearCache()
    }

    def "typing in a todo should create first entry"() {
        given:
        to FinalTodoMvcAngularPage

        when:
        at TodoMvcJqueryPage

        and:
        newTodo << "Todo #1" << Keys.RETURN
        newTodo << "Another task" << Keys.RETURN

        then:
        todoLabel(0).text() == "Todo #1"
        todoLabel(1).text() == "Another task"
    }

    def "double click to edit a task"() {
        when:
        to TodoMvcJqueryPage

        and:
        newTodo << "Todo #1" << Keys.RETURN
        interact {
            doubleClick(todoLabel(0))
        }

        js."document.activeElement.select()"
        activeElement << "New Text" << Keys.TAB;
        //driver.switchTo().activeElement().sendKeys("Boo", Keys.TAB);

        then:
        waitFor {
            todoLabel(0).text() == "New Text"
        }
    }

    def "check a todo as done marks it as completed"() {
        when:
        to TodoMvcJqueryPage

        and:
        newTodo << "Todo #1" << Keys.RETURN
        assert todoCount == "1"

        todoRow(0).find(type: "checkbox").value(true)
//        todoCheckbox(0).click()

        then:
        todoRow(0).classes().contains("completed")
        todoCount == "0"
    }

    def "changing filter updates url and todo list"() {
        given:
        to TodoMvcJqueryPage
        newTodo << "Todo #1" << Keys.RETURN
        todoCheckbox(0).value(true)

        when:
        filterLink("Active").click()

        then:
        driver.currentUrl.endsWith("#/active");
        todoRow(0).isEmpty()
    }

}