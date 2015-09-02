<html>
<head>
    <title>J-Dawg's Quick Tips!</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1 id="title">J-Dawg's Quick Tips!</h1>
<div id="infoContainer">
    <div id="info">
        <p>This is Jeremy's project 4 submission for the Intro to Programming Nanodegree at Udacity. Please check out some quick programming tips, and add your own! To see my notes on course 4, please hit the "Lesson 4 Notes" button to the right.</p>
        <p>This site uses Java servlets running on Google App Engine. It stores data in the Google Cloud Datastore via the Objectify library, uses FreeMarker for templating, and Spring for HTML escaping. It was developed in Android Studio, and build and deployed using Gradle. It makes use of flexboxes to achieve a responsive design. Please enjoy, and keep it clean!</p>
    </div>
    <form name="newTip" action="" method="post" id="input">
        <div id="textInputs">
            Tip Title: <input type="text" name="title" tabindex=1>
            <input type="submit" value="Submit New Tip" tabindex=3> <br><br>
            Tip:<textarea name="tip" id="tipTextArea" tabindex=2></textarea>
        </div>
        <div id="buttons">
            <input type="submit" value="Add Debug tip" formaction="/?p=debug"><br><br>
            <input type="submit" value="Lesson 4 Notes" formaction="/?p=notes"><br><br>
            <input type="submit" value="Clear all tips" formaction="/?p=clear">

        </div>
    </form>
</div>

<div id="concepts-container">
    <#list tips as tip>
        <div class="concept">
            <h2 class="concept-title">
                ${tip.title}
            </h2>
            <div class="concept-description">
                ${tip.tip}
            </div>
        </div>
    </#list>
</div>



</body>
</html>