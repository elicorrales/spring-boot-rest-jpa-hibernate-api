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
    axios.post('/customer', { fname, lname, email })
    .then(
        result => {
            app.alerts.displayMessage('success','New Customer ' + fname + ' ' + lname + ' added.');
            app.showCustomerListSection();
        }
    )
    .catch(
        error => {
            app.utils.displayError(error);
        }
    );

}

