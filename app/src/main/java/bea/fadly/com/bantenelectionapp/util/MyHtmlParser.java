package bea.fadly.com.bantenelectionapp.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bea.fadly.com.bantenelectionapp.domain.News;

/**
 * Created by DIGIKOM-EX4 on 11/23/2016.
 */

public class MyHtmlParser {

    News news;

    static MyHtmlParser myHtmlParser;

    public MyHtmlParser(String html) {
        this.news = parse(html);
    }

    public static MyHtmlParser newInstance(String html){
        myHtmlParser = new MyHtmlParser(html);
        return myHtmlParser;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public News parse(String html){
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("div");
        News news = new News();
        StringBuilder sb = new StringBuilder();
        for (Element e : elements) {
            String className = e.attr("class");
            switch (className){
                case "K2FeedImage":
                    Elements imagesTag = e.select("img");
                    Element imageTag = imagesTag.get(0);
                    String srcImage = imageTag.attr("src");
                    news.setImageUrl(srcImage);
                    break;
                case "K2FeedIntroText":
                    Elements pTags = e.select("p");
                    Element p = pTags.get(0);
                    String introText = p.text();
                    sb.append(introText).append("\n");
                    break;
                case "K2FeedFullText":
                    Elements pTags2 = e.select("p");
                    for (Element ex : pTags2){
                        String text = "<p> " + ex.text() + " </p>";
                        sb.append(text);
                    }
                    news.setDescription(sb.toString());
                    break;
                default:
                    break;
            }
        }
        return news;
    }


}
