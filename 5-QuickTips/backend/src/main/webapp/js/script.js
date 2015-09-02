var polymerReady = false;
var gapiReady    = false;


document.addEventListener('WebComponentsReady', function() {
    polymerReady = true;
    console.log("Loaded");
});


function api_init() {
    console.log("Tryig to load gapi");
    var protocol = window.location.host.search('localhost')==-1 ? 'https://' : 'http://'
    gapi.client.load(
        'quickTips',
        'v1',
        apiLoaded,
        '//' + window.location.host + '/_ah/api'
        );
}



//		// display a greeting card with a given text and avatar number
//		function display_new_card(id, text, avatarId) {
//			// in case the dom and polymer are not ready yet, retry in 300ms.
//			if (!polymerReady)
//				window.setTimeout(function(){display_new_card(id, text, avatarId)}, 300);
//			else {
//				// add it to the data model
//				var model = document.querySelector('greetings-list');
//				id = id + '/' + Date.now();
//				model.add(new Object({id: id, text: text, avatarSrc: "images/avatar-" + (avatarId + 1) + ".svg"}))
//			}
//		}

function apiLoaded(){
   gapiReady = true;
   console.log("gapi ready");
   document.getElementById("notes").disabled = false;
   document.getElementById("debug").disabled = false;
   document.getElementById("clear").disabled = false;
   document.getElementById("create").disabled = false;
   loadTips();
}


function loadTips(){
    if (gapiReady){
        console.log("Loading tips");
        gapi.client.quickTips.getTips().execute(displayTips);
    }
}

function displayTips(tips){
    var tipList = document.querySelector("tip-list")
    tipList.load(tips.items)
}

function getTips(){
    if (gapiReady){
        gapi.client.quickTips.getTips().execute(function(resp){
            console.log("Got a response");
            console.log(resp);
        });
    }
}

function fabClicked() {
    console.log("Stop hitting my button!")
    var dialog = document.getElementById("dialog");
    dialog.toggle();

//    if (gapiReady){
//        gapi.client.quickTips.addTip({"title":"derp","content":"herp"}).execute(function(resp){
//            loadTips();
//        });
//    }



}

function addDebugTip(){
    if (gapiReady){
        gapi.client.quickTips.addDebugTip().execute(function(resp){
            loadTips();
        });
    }
}

function addNotes(){
    if (gapiReady){
        console.log("Adding the notes")
        gapi.client.quickTips.addLesson5Notes().execute(function(resp){
             loadTips();
        });
    }
}

function clearTips(){
    if(gapiReady){
        console.log("clearing notes")
        gapi.client.quickTips.clearTips().execute(function(resp){
            loadTips();
        });
    }
}


function clearInputs(){
    var titleInput = document.getElementById("title-input")
    titleInput.value = ""
    var contentInput = document.getElementById("content-input")
    contentInput.value = ""


}

function addTipFromForm(){
    var titleInput = document.getElementById("title-input")
    var title = titleInput.value;
    var contentInput = document.getElementById("content-input")
    var content = contentInput.value;

    if (!content){
        content = "No content"
    }

    if (!title){
        title = "No title"
    }


    if (gapiReady){
        gapi.client.quickTips.addTip({"title":title,"content":content}).execute(function(resp){
            loadTips();
        });
    }
    clearInputs();

}
