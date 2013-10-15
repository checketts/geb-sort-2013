import geb.*

class WikipediaPage extends Page {
    static url = "http://en.wikipedia.org/wiki/Main_Page"
    static at = { title =~ "Wikipedia, the free encyclopedia" }

    static content = {
        mainLink { $("#n-mainpage-description")}
        searchField {$("#searchInput")}
        articleTitle {$("#firstHeading span").text()}
    }
}


