import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys

class WikipediaNavigationSpec extends GebReportingSpec {
    def "searching should return a Groovy article"(){
        given:
        to WikipediaPage

        when:
        searchField << "Groovy" << Keys.ENTER

        then:
        articleTitle == "Groovy"
    }
}


