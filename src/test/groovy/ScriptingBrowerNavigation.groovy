import geb.Browser
import org.openqa.selenium.Keys

Browser.drive {
    go "http://en.wikipedia.org/wiki/Main_Page"
     
    assert $("#n-mainpage-description").text() == "Main page"
     
    $("#searchInput").value("Groovy")
    $("#searchInput") << Keys.ENTER
     
    assert $("#firstHeading span").text() == "Groovy"
}



