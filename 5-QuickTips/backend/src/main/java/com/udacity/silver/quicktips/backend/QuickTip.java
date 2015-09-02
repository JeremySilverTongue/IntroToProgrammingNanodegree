package com.udacity.silver.quicktips.backend;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class QuickTip implements Serializable {
    @Id Long id;
    @Parent Key<TipContainer> container;
    String title = "No title provided";
    String content = "No tip provided";
    @Index
    Date date = null;

    static final Key<TipContainer> defaultContainerKey = Key.create(TipContainer.class,TipContainer.defaultTipContainer);

    public QuickTip(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




    public QuickTip(String title, String tip){
        if (title != null && title.length() != 0) {
//            this.title = HtmlUtils.htmlEscape(title);
            this.title = title;
        }
        if(tip != null && tip.length() != 0){
//            this.content = HtmlUtils.htmlEscape(tip);
            this.content = tip;
        }
        this.date = new Date();
        this.container = defaultContainerKey;
    }

    public static void StoreNew(String title, String tip){
        ofy().save().entity(new QuickTip(title,tip)).now();
    }

    public static ArrayList<QuickTip> loadAll(){
        ArrayList<QuickTip> output = new ArrayList<QuickTip>();
        output.addAll(ofy().load().type(QuickTip.class).ancestor(defaultContainerKey).order("-date").list());

        return output;
    }

    public static void deleteAll(){
        Iterable<Key<QuickTip>> allKeys = ofy().load().type(QuickTip.class).ancestor(defaultContainerKey).keys();
        ofy().delete().keys(allKeys);
    }

    public static void addDebugTip(){
        StoreNew("Debug tip", "This is a debug tip");
    }

    public static void addLesson5Notes(){

        StoreNew("Thanks for Grading","Thanks so much for checking out my janky site. Yeah I know a lot of stuff isn't styled very well. Just means there's lots for me to learn in FEND. And hey, did you see how the buttons didn't work until the API is ready for access? Pretty sweet, right?");

        StoreNew("Making a Custom Element","A custom element is composed of three things: a set of styles to apply to the Element's shadow DOM; a template, possibly using data binding; and a script block that registered the element with Polymer, and declares any properties or methods.");

        StoreNew("Paper and Iron","Polymer provides a bunch of collections of elements for you to use. The Iron elements are the core of Polymer, mostly used behind the scenes. The only iron I used was to grab the icon for the FAB. The Paper elements are the ones used for Material design, so the stuff like the fancy shadows, the ink effect on the buttons, and the FAB.");

        StoreNew("Setting up the Toolbar and FAB","I kinda followed the tutorial here: https://youtu.be/0E7EsFvTU2A. Yeah, I know it's in Spanish. Still managed to glean enough from the code to put it together.");

        StoreNew("What's the Upshot?","Well it makes front end web development look a lot like making UIs for stuff like Android or Spring. Instead of dropping in essentially blank elements and then stying from scratch, you can instead reuse fancy components.");

        StoreNew("How to Incorporate Polymer","Ultimately Polymer is just a ton of static files, which you can grab using Bower. Bower is basically a dependency manager for front end stuff. Yeah, using Gradle dependency management and bower dependency management in the same project is kinda silly, but I think unavoidable.");

        StoreNew("So What Does Polymer Do?","It allows you to create and use Web Components. Web Components are a fancy way of saying objects. A web component has it's own \"Shadow Dom\", where CSS can be applied without touching the rest of the document. It also means that you can make super fancy chunks of webpage using custom tags. For instance, this text is inside a <paper-card> tag.");

        StoreNew("On to Polymer","My primary source of information for this project was www.polymer-project.org, specifically, the element catalog, and the resulting links to the Polymer GitHub repo, where I found lots of useful sample code.");

        StoreNew("Toolchain","The backend for this site was written in Java, and the site was developed and deployed from Android Studio using Gradle and the AppEngine plugin for Intellij. To learn about Gradle, I referenced Udacity's ud867: Gradle for Android and Java.");

        StoreNew("Data Access", "The second hardest part of this project was setting up data access using Google Could Endpoints. Google Could Endpoints is a cool service/library that allows you to define an API via annotations, and then it'll automatically create client libraries for hitting those API endpoints from JavaScript or from Android! To get all this set up, I actually used Udacity's ud859: Developing Scalable Apps in Java.");

        StoreNew("Data Storage", "The tips are stored in Google Datastore, using the Objectify ORM to map between datastore objects and live Java objects.");

        StoreNew("Hosting","This site is hosted on Google AppEngine, which means I should get nice features like load balancing for free!");

        StoreNew("What This Site Does","This is a site for sharing quick programming tips. To add a new tip, simply click the floating action button at the bottom right. Yeah, I know the text area has no padding. I don't seem to be able to fix that. Might be a bug with Polymer, might be a bug with me.");

        StoreNew("Polymer!", "For my final project, I chose to investigate Polymer, a tool/framework for creating web components. In the process, I learned that JavaScript is terrifying, and that it's a bad plan to use bleeding edge tools with kinda crappy documentation when you don't know much about the underlying technologies. Regardless, let's dig in, and explore the monstrosity I've created here.");

        StoreNew("Topics Explored","In this project I explored JavaScript, relational databases, and built my own API. The site is also kinda responsive, but that was mostly by mistake. Polymer paper-cards are clearly smarter than I am.");
    }
}
