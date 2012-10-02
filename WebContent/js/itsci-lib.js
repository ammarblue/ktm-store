//

function goTo(url) {
	window.location.href = url;
}

function doDelete(message, url) {
    if(confirm(message)) {
        goTo(url);
    }
}