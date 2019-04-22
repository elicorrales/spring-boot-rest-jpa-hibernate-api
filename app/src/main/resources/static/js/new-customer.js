'use strict';
//const submitNewCustomerBtnElem = document.getElementById('submitNewCustomerBtn');
const fnameInputElem = document.getElementById('fnameInput');
const lnameInputElem = document.getElementById('lnameInput');
const emailInputElem = document.getElementById('emailInput');

var newCustomer = {};

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
            let msg = error.response;
            msg = error.response.data;
            msg = error.response.data.message;
            let message = error.response && error.response.data && error.response.data.message ? error.response.data.message:error;
            app.alerts.displayMessage('danger',message);
        }
    );

}


app.newCustomer = newCustomer;