'use strict';
const alertAreaElem = document.getElementById('alertArea');

var alerts = {};

const hideMessage = () => {
    alertAreaElem.style.display = 'none';
}
const displayMessage = (type,message) => {
    alertAreaElem.style.display = 'block';
    alertAreaElem.className = 'alert alert-' + type;
    alertAreaElem.innerHTML = message;
}

alerts.hideMessage = hideMessage;
alerts.displayMessage = displayMessage;
app.alerts = alerts;