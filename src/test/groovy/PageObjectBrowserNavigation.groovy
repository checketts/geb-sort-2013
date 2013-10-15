import geb.Browser
import org.openqa.selenium.Keys

Browser.drive {
    to WikipediaPage
    assert mainLink.text() == "Main page"

    searchField << "Groovy" << Keys.ENTER
    assert articleTitle == "Groovy"
}


