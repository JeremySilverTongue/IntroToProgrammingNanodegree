package com.example.silver.myapplication.backend;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.web.util.HtmlUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.mail.Store;

@Entity
public class QuickTip implements Serializable {
    @Id Long id;
    @Parent Key<TipContainer> container;
    String title = "No title provided";
    String tip = "No tip provided";
    @Index
    Date date = null;

    static final Key<TipContainer> defaultContainerKey = Key.create(TipContainer.class,TipContainer.defaultTipContainer);

    private QuickTip(){}

    public QuickTip(String title, String tip){
        if (title != null && title.length() != 0) {
            this.title = HtmlUtils.htmlEscape(title);
        }
        if(tip != null && tip.length() != 0){
            this.tip = HtmlUtils.htmlEscape(tip);
        }
        this.date = new Date();
        this.container = defaultContainerKey;
    }

    public static void StoreNew(String title, String tip){
        ofy().save().entity(new QuickTip(title,tip)).now();
    }

    public static List<QuickTip> loadAll(){
        return ofy().load().type(QuickTip.class).ancestor(defaultContainerKey).order("-date").list();
    }

    public static void deleteAll(){
        Iterable<Key<QuickTip>> allKeys = ofy().load().type(QuickTip.class).ancestor(defaultContainerKey).keys();
        ofy().delete().keys(allKeys);
    }

    public static void addLesson4Notes(){

        StoreNew("Templating and DRY", "Just like CSS allows you to change your whole page's styling in one place, templates allow you to keep your code terse and consistent by allowing you to write rolled up loops.");
        StoreNew("Templating Pitfalls", "Beware of mixing too much imperative code in your templates. Simple loops are fine, but take care to avoid mixing your controller and your view.");
        StoreNew("Advanced Templating","Templating languages may also allow more interesting control flow statements, such as loops and conditionals.");
        StoreNew("Templating","A templating language allows you to create plain text documents by filling in fields in a template. This helps isolate your view and controller layers.");
        StoreNew("Validation Example", "For instance, what would this do were it not for proper HTML escaping? </div> That tag should be invisible, and this text should be outside the nice little box.");
        StoreNew("Validation", "Whenever you allow input in your program, you must ensure that the input doesn't have more control over your program's execution than you were planning.");

        StoreNew("Servlets", "A servlet is a Java class that receives requests from particular URLs, and supplies a (usually HTML or JSON) response.");
        StoreNew("Google App Engine","In this case, the servers actually belong to Google, and the number of servers running this site will scale up and down based on load.");
        StoreNew("Servers","Servers are computers that hang out on the internet, waiting for requests (often HTTP requests), and spitting back responces.");

    }
}
