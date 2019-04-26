'use strict';

var utils = {};

const displayError = (error) => {
            let message = '';
            if (error.message) {
                message = error.message + '<br/>';
            }
            if (error.response) {
                if (error.response.data) {
                    if (error.response.data.error && error.response.data.message) {
                        message += error.response.data.error + '<br/>';
                        message += error.response.data.message;
                    } else if (error.response.data.error) {
                        message += error.response.data.error;
                    } else if (error.response.data.message) {
                        message += error.response.data.message;
                    } else if (error.response.data.length > 0) {
                        error.response.data.forEach(x => { message += x + '<br/>'});
                    } else {
                        message += error.response.data;
                    }
                } else if (!error.message) {
                    message += error.response;
                }
            } else {
                message += error;
            }
            app.alerts.displayMessage('danger',message);
}



utils.displayError = displayError;
app.utils = utils;