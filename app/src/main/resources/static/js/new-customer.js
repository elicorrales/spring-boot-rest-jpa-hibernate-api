'use strict';
//const submitNewCustomerBtnElem = document.getElementById('submitNewCustomerBtn');
const fnameInputElem = document.getElementById('fnameInput');
const lnameInputElem = document.getElementById('lnameInput');
const emailInputElem = document.getElementById('emailInput');

const onSubmitNewCustomerBtnClickDoSubmitNewCustomer = (event) => {
    event.preventDefault();
    let fname = fnameInputElem.value;
    let lname = lnameInputElem.value;
    let email = emailInputElem.value;
    axios.post('/customers/customer', { fname, lname, email })
    .then(
        result => {
            app.alerts.displayMessage('success','New Customer ' + fname + ' ' + lname + ' added.');
            app.showCustomerListSection();
        }
    )
    .catch(
        error => {
            let message = '';
            if (error.response) {
                if (error.response.data) {
                    if (error.response.data.message) {
                        message = error.response.data.message;
                    } else if (error.response.data.length > 0) {
                        error.response.data.forEach(x => { message += x + '<br/>'});
                    } else {
                        message = error.response.data;
                    }

                } else {
                    message = error.response;
                }
            } else {
                message = error;
            }
            app.alerts.displayMessage('danger',message);
        }
    );

}

